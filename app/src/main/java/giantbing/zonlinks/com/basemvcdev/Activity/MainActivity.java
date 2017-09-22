package giantbing.zonlinks.com.basemvcdev.Activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import giantbing.zonlinks.com.basemvcdev.Bean.WetherBean;
import giantbing.zonlinks.com.basemvcdev.Http.Base.ProgressSubscriber;
import giantbing.zonlinks.com.basemvcdev.Http.HttpCilent;
import giantbing.zonlinks.com.basemvcdev.R;
import giantbing.zonlinks.com.basemvcdev.Event.ActivityEvent;
import giantbing.zonlinks.com.basemvcdev.Util.AppUtils;
import giantbing.zonlinks.com.basemvcdev.Util.StartActivityHelper;
import giantbing.zonlinks.com.giantbaselibrary.Util.ActivityUtil;
import giantbing.zonlinks.com.giantbaselibrary.Util.AnimotionHelper;
import giantbing.zonlinks.com.giantbaselibrary.Util.LogUtil;
import giantbing.zonlinks.com.giantbaselibrary.Util.ToastHelper;
import giantbing.zonlinks.com.giantbaselibrary.View.BubbleDrawer;
import giantbing.zonlinks.com.giantbaselibrary.View.FloatBubbleView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import tyrantgit.explosionfield.ExplosionField;

public class MainActivity extends AppBaseActivity {
    @BindView(R.id.fbv_main)
    FloatBubbleView fbvMain;
    @BindView(R.id.event_text)
    TextView eventText;
    ExplosionField field;

    private boolean isDown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {
        ActivityUtil.setTranSlucent(MainActivity.this);
        field = ExplosionField.attach2Window(this);
    }

    @Override
    protected void loadData() {
        //设置气泡绘制者
        BubbleDrawer bubbleDrawer = new BubbleDrawer(this);
        //设置渐变背景 如果不需要渐变 设置相同颜色即可
        bubbleDrawer.setBackgroundGradient(new int[]{0xffffffff, 0xffffffff});
        //给SurfaceView设置一个绘制者
        fbvMain.setDrawer(bubbleDrawer);
    }

    @Override
    protected void destroyView() {
        fbvMain.onDrawDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void destroyData() {

    }

    @Override
    protected void initClick() {
        eventText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // StartActivityHelper.startActivityTraslate(MainActivity.this,WheatherActivity.class, StartActivityHelper.Anmotion.Slide);
                //AppUtils.uninstallDataAPPBySilent(getApplicationContext(), "com.zonlinks.giantbing.guangzhouboard");
                unstall("com.zonlinks.giantbing.guangzhouboard");
                field.explode(view);
                getWetherData();

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        fbvMain.onDrawResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        fbvMain.onDrawPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isDown) {
                this.finish();
                System.exit(0);
            } else {
                isDown = true;
                ToastHelper.info(getApplicationContext(), "再按一次退出");
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isDown = false;
                    }
                }, 2000);
            }
        }
        return false;
    }


    private void getWetherData() {
        HttpCilent.getInstance().getWether("chengdu", MainActivity.this, loadDialog);

    }

    private void unstall(String packageName) {
        AppUtils.unInstall(getApplicationContext(), packageName)
                .subscribe(new Consumer<AppUtils.uninstallEnum>() {
                               @Override
                               public void accept(AppUtils.uninstallEnum aBoolean) throws Exception {
                                  switch (aBoolean){
                                      case NOROOT:
                                          ToastHelper.warning(getApplicationContext(),"没有ROOT权限，尝试手动删除！");
                                          break;
                                      case SUCCESS:
                                          ToastHelper.success(getApplicationContext(),"卸载成功！");
                                          break;
                                      case UNKOWPACAGE:
                                          ToastHelper.warning(getApplicationContext(),"未安装！");
                                          break;
                                  }
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                ToastHelper.error(MainActivity.this, throwable.toString());
                                LogUtil.e(throwable.toString());
                            }
                        });
    }

}
