package giantbing.zonlinks.com.basemvcdev.View;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;

import giantbing.zonlinks.com.basemvcdev.R;

/**
 * Created by giant on 2017/9/19.
 */

public class ProgressDialog extends Dialog {
    private Context context;
    private boolean clickAble = true;
    public ProgressDialog(@NonNull Context context,boolean clickAble) {
        super(context,R.style.Loading);
        this.context =context;
        this.clickAble = clickAble;
        initDialog();
    }

    public ProgressDialog(@NonNull Context context) {
        super(context,R.style.Loading);
        this.context =context;
        initDialog();
    }



    private void initDialog(){
        View view = View.inflate(context, R.layout.load_dialog, null);
        this.setCancelable(clickAble);
        this.setContentView(view);
    }

    @Override
    public void onBackPressed() {
        if (clickAble){
            super.onBackPressed();
        }

    }
}
