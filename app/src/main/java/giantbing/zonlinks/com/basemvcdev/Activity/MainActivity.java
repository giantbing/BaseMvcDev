package giantbing.zonlinks.com.basemvcdev.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import giantbing.zonlinks.com.basemvcdev.BuildConfig;
import giantbing.zonlinks.com.basemvcdev.R;
import giantbing.zonlinks.com.basemvcdev.Event.ActivityEvent;
import giantbing.zonlinks.com.giantbaselibrary.Util.ActivityUtil;
import giantbing.zonlinks.com.giantbaselibrary.Util.LogUtil;
import giantbing.zonlinks.com.giantbaselibrary.Util.ToastHelper;
import giantbing.zonlinks.com.giantbaselibrary.View.BubbleDrawer;
import giantbing.zonlinks.com.giantbaselibrary.View.FloatBubbleView;

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
        loadDialog.showLoadDialog(MainActivity.this);
        //设置气泡绘制者
        BubbleDrawer bubbleDrawer = new BubbleDrawer(this);
        //设置渐变背景 如果不需要渐变 设置相同颜色即可
        bubbleDrawer.setBackgroundGradient(new int[]{0xffffffff, 0xffffffff});
        //给SurfaceView设置一个绘制者
        fbvMain.setDrawer(bubbleDrawer);

        loadDialog.dismiss();
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
}
