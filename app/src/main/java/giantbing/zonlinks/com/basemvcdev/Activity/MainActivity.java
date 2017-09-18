package giantbing.zonlinks.com.basemvcdev.Activity;

import android.app.Activity;
import android.os.Bundle;

import giantbing.zonlinks.com.basemvcdev.R;
import giantbing.zonlinks.com.giantbaselibrary.Util.ActivityUtil;
import giantbing.zonlinks.com.giantbaselibrary.View.BubbleDrawer;
import giantbing.zonlinks.com.giantbaselibrary.View.FloatBubbleView;

public class MainActivity extends AppBaseActivity {
    private FloatBubbleView mDWView;
    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {
        mDWView = (FloatBubbleView) findViewById(R.id.fbv_main);
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
        mDWView.setDrawer(bubbleDrawer);
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

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mDWView.onDrawResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDWView.onDrawPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDWView.onDrawDestroy();
    }
}
