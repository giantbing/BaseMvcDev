package giantbing.zonlinks.com.basemvcdev.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import butterknife.BindView;
import butterknife.ButterKnife;
import giantbing.zonlinks.com.basemvcdev.Bean.WetherBean;
import giantbing.zonlinks.com.basemvcdev.Http.Base.ProgressSubscriber;
import giantbing.zonlinks.com.basemvcdev.Http.HttpCilent;
import giantbing.zonlinks.com.basemvcdev.R;
import giantbing.zonlinks.com.basemvcdev.Event.ActivityEvent;
import giantbing.zonlinks.com.giantbaselibrary.Util.ActivityUtil;
import giantbing.zonlinks.com.giantbaselibrary.Util.LogUtil;
import giantbing.zonlinks.com.giantbaselibrary.Util.ToastHelper;
import giantbing.zonlinks.com.giantbaselibrary.View.BubbleDrawer;
import giantbing.zonlinks.com.giantbaselibrary.View.FloatBubbleView;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import io.reactivex.subscribers.SafeSubscriber;

public class MainActivity extends AppBaseActivity {
    @BindView(R.id.fbv_main)
    FloatBubbleView fbvMain;
    @BindView(R.id.event_text)
    TextView eventText;

    @Override
    protected void initVariables() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        ActivityUtil.setTranSlucent(MainActivity.this);

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

    }

    @Override
    protected void destroyData() {

    }

    @Override
    protected void initClick() {
        eventText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new ActivityEvent() {
                    @Override
                    public CodeEnum getCode() {
                        return CodeEnum.toPage;
                    }
                });
                getWetherData();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ActivityEvent event) {
        LogUtil.d(event.getCode().getObjects().toString(),event);
        ToastHelper.error(MainActivity.this,event.getCode()+"");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);



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
        fbvMain.onDrawDestroy();
        EventBus.getDefault().unregister(this);
    }
    private void getWetherData(){
        HttpCilent.getInstance().getWether("chengdu", MainActivity.this, loadDialog, new ProgressSubscriber<WetherBean>(loadDialog) {
            @Override
            public void handlerSuccess(WetherBean wetherBean) {
                LogUtil.wtf("2333");
            }
        });

    }
}
