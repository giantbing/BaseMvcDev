package giantbing.zonlinks.com.basemvcdev;

import android.os.Bundle;

import giantbing.zonlinks.com.basemvcdev.Activity.AppBaseActivity;
import giantbing.zonlinks.com.basemvcdev.View.LoadDialog;

public class MainActivity extends AppBaseActivity {

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {
        loadDialog.showLoadDialog(MainActivity.this);
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
