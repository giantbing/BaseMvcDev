package giantbing.zonlinks.com.basemvcdev.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

import giantbing.zonlinks.com.basemvcdev.BuildConfig;
import giantbing.zonlinks.com.basemvcdev.View.LoadDialog;
import giantbing.zonlinks.com.giantbaselibrary.Activity.BaseActivity;
import giantbing.zonlinks.com.giantbaselibrary.Util.LogUtil;

/**
 * Created by P on 2017/9/15.
 */

public abstract class AppBaseActivity extends BaseActivity {

    protected LoadDialog loadDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.setDEBUG(BuildConfig.DEBUG);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        loadDialog = null;
        super.onDestroy();
    }


}
