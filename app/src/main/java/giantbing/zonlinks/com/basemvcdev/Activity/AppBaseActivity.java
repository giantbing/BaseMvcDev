package giantbing.zonlinks.com.basemvcdev.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import giantbing.zonlinks.com.basemvcdev.View.ProgressDialog;
import giantbing.zonlinks.com.giantbaselibrary.Activity.BaseActivity;

/**
 * Created by P on 2017/9/15.
 */

public abstract class AppBaseActivity extends BaseActivity {

    protected ProgressDialog loadDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        loadDialog = new ProgressDialog(AppBaseActivity.this,false);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        loadDialog = new ProgressDialog(AppBaseActivity.this,false);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        loadDialog = null;
        super.onDestroy();
    }
}
