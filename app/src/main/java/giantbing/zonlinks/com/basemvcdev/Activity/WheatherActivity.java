package giantbing.zonlinks.com.basemvcdev.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import giantbing.zonlinks.com.basemvcdev.Bean.WetherBean;
import giantbing.zonlinks.com.basemvcdev.R;
import giantbing.zonlinks.com.giantbaselibrary.Util.LogUtil;
import giantbing.zonlinks.com.giantbaselibrary.Util.ToastHelper;

public class WheatherActivity extends AppBaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheather);
    }
/**--------------------------------------------------------------基类------------------------------------------------------------------------------------*/
    @Override
    protected void initVariables() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

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
/**---------------------------------------------------------------EventBus订阅-------------------------------------------------------------------------------------------*/
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(WetherBean event) {
//        LogUtil.d("Nonestick");
//        ToastHelper.error(getApplicationContext(), event.getResults().get(0).getLast_update());
//    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void stickyEvent(WetherBean event) {
        LogUtil.d("stick");
        ToastHelper.error(getApplicationContext(), event.getResults().get(0).getLast_update());
    }

}
