package giantbing.zonlinks.com.basemvcdev.Util;

/**
 * Created by P on 2017/9/22.
 */

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import giantbing.zonlinks.com.giantbaselibrary.Util.LogUtil;
import giantbing.zonlinks.com.giantbaselibrary.Util.ToastHelper;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public final class AppUtils {

    /**
     * APP信息封装类
     *
     * @author xiaxueliang
     */
    public static class AppData {

        public String appName;
        public String appPackageName;
        public Drawable appIcon;

        public AppData(String appName, String packageName, Drawable icon) {
            this.appName = appName;
            this.appPackageName = packageName;
            this.appIcon = icon;
        }

    }

    /**
     * 查询手机内非系统应用
     *
     * @param context
     * @return
     */
    public static List<AppData> getAllDataApps(Context context) {
        //
        List<AppData> appDataList = new ArrayList<AppData>();
        if (context == null) {
            return appDataList;
        }
        //
        PackageManager pm = context.getPackageManager();
        // 获取手机内所有应用
        List<PackageInfo> paklist = pm.getInstalledPackages(0);
        for (int i = 0; i < paklist.size(); i++) {
            PackageInfo pi = (PackageInfo) paklist.get(i);
            // 判断是否为非系统预装的应用程序
            if ((pi.applicationInfo.flags & pi.applicationInfo.FLAG_SYSTEM) <= 0) {

                AppData appData = new AppData(pi.applicationInfo.loadLabel(pm)
                        + "", pi.applicationInfo.packageName,
                        pi.applicationInfo.loadIcon(pm));
                appDataList.add(appData);

            }
        }
        return appDataList;
    }

    /**
     * 静默卸载apk到Data/app目录
     *
     * @param packageName
     * @return 卸载成功为true
     */
    public static boolean uninstallDataAPPBySilent(Context context, String packageName) {
        LogUtil.d("-------uninstallDataAPPBySilent------");
        if (checkApplication(context, packageName)) {
            StringBuilder cmd = new StringBuilder();
            cmd.append("pm uninstall " + packageName).append("\n");
            // 部分手机Root之后Library path 丢失，导入library path可解决该问题
            // cmd.append("export LD_LIBRARY_PATH=/vendor/lib:/system/lib");
            if (execRootCmd(cmd.toString()) == 0) {
                LogUtil.e("uninstall: " + packageName + "success");
                return true;
            }
        } else {
            LogUtil.e("uninstall: " + "包名不存在！");
            return false;
        }


        LogUtil.e("uninstall: " + packageName + " failed");

        return false;
    }

    /**
     * root权限下执行命令
     *
     * @param cmd 多条命令需用换行分隔
     * @return 执行结果码 0代表成功
     */
    private static int execRootCmd(String cmd) {

        Process process = null;
        DataOutputStream dos = null;
        try {
            process = Runtime.getRuntime().exec("su");
            dos = new DataOutputStream(process.getOutputStream());
            dos.writeBytes(cmd + "\n");
            dos.flush();
            dos.writeBytes("exit\n");
            dos.flush();
            process.waitFor();

            return process.exitValue();
        } catch (Exception e) {
            return -1;
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
                process.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 卸载指定包名的应用
     *
     * @param packageName 包名
     */
    private static void Uninstall(Context context, String packageName) {
        Uri packageURI = Uri.parse("package:" + packageName);
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(packageURI);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    /**
     * 判断该包名的应用是否安装
     *
     * @param packageName
     * @return
     */
    public static boolean checkApplication(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static Observable<uninstallEnum> unInstall(final Context context, final String packageName) {

        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                e.onNext(checkApplication(context, packageName));
            }
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .flatMap(new Function<Boolean, ObservableSource<uninstallEnum>>() {
                    @Override
                    public ObservableSource<uninstallEnum> apply(Boolean aBoolean) throws Exception {
                        ObservableSource<uninstallEnum> source;
                        if (aBoolean) {
                            source = new ObservableSource<uninstallEnum>() {
                                @Override
                                public void subscribe(Observer<? super uninstallEnum> observer) {
                                    if (uninstallDataAPPBySilent(context, packageName)) {
                                        observer.onNext(uninstallEnum.SUCCESS);
                                    } else {
                                        observer.onNext(uninstallEnum.NOROOT);
                                    }

                                }
                            };
                            return source;
                        } else {
                            source = new ObservableSource<uninstallEnum>() {

                                @Override
                                public void subscribe(Observer<? super uninstallEnum> observer) {
                                    observer.onNext(uninstallEnum.UNKOWPACAGE);
                                }
                            };
                            return source;
                        }

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<uninstallEnum>() {
                    @Override
                    public void accept(uninstallEnum aBoolean) throws Exception {
                        if (aBoolean.equals(uninstallEnum.NOROOT)) {
                            ToastHelper.error(context, "没有Root权限尝试手动删除！");
                            Uninstall(context, packageName);
                        }

                    }
                });
    }

    public enum uninstallEnum {
        SUCCESS,
        UNKOWPACAGE,
        NOROOT
    }

}
