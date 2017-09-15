package giantbing.zonlinks.com.giantbaselibrary.Util;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;

import java.io.File;
import java.io.IOException;

/**
 * Created by P on 2017/7/10.
 */

public class DeviceUtil  {
    public static  final int TAKE_PHOTO = 0x13;

    public static void powerOff(Context context){
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(
                "com.android.settings.action.REQUEST_POWER_OFF");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);
        am = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, 10000, pendingIntent);

    }
    public static void hideSystemNavigationBar(Activity activity) {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View view = activity.getWindow().getDecorView();
            view.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
    public static void showSystemNavigationBar(Activity activity) {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View view = activity.getWindow().getDecorView();
            view.setSystemUiVisibility(View.VISIBLE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    public static void lockOn(Activity mActivity){
         DevicePolicyManager mDPM;
         ComponentName mDeviceAdminSample;
         mDeviceAdminSample = new ComponentName(mActivity, LockReceiver.class);

        //设备政策管理器
        mDPM = (DevicePolicyManager)mActivity. getSystemService(Context.DEVICE_POLICY_SERVICE);

                if (mDPM.isAdminActive(mDeviceAdminSample)) {
                    //锁屏
                    mDPM.lockNow();
                    //重置密码
                   // mDPM.resetPassword("123456", 0);
                } else {
                    Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                    intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mDeviceAdminSample);
                    intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                            "我可以帮你更好地学习！");
                    mActivity.startActivity(intent);
                }


    }
    public static String tackPhoto(String ImgPath,Fragment context){
        //图片名称 时间命名
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//        Date date = new Date(System.currentTimeMillis());
//        String filename = format.format(date);
        //创建File对象用于存储拍照的图片 SD卡根目录
        //File outputImage = new File(Environment.getExternalStorageDirectory(),"test.jpg");
        //存储至DCIM文件夹
        File file = new File(ImgPath);
         if (!file.exists()) {
                 file.mkdir();
             }
        File outputImage = new File(ImgPath,System.currentTimeMillis()+".png");
        try {
            if(outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch(IOException e) {
            e.printStackTrace();
        }
        //将File对象转换为Uri并启动照相程序
        Uri imageUri = Uri.fromFile(outputImage);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE"); //照相
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); //指定图片输出地址
        context.startActivityForResult(intent,TAKE_PHOTO); //启动照相
        //拍完照startActivityForResult() 结果返回onActivityResult()函数
        return outputImage.getPath();
    }
}

