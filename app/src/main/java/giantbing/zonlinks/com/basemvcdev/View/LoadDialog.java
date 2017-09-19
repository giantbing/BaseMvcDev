package giantbing.zonlinks.com.basemvcdev.View;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.pnikosis.materialishprogress.ProgressWheel;

import butterknife.BindView;
import butterknife.ButterKnife;
import giantbing.zonlinks.com.basemvcdev.R;

/**
 * Created by P on 2017/7/8.
 */

public class LoadDialog {
    private static Dialog dialog;
    private static ViewHolder holder;

    /**
     * 暴露给外界okListener实现隐藏对话框的方法
     */
    public static void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }


    public static void showLoadDialog(final Context context) {

        if (dialog == null)
            dialog = new Dialog(context, R.style.Loading);
        dialog.dismiss();
        View view = View.inflate(context, R.layout.load_dialog, null);
        dialog.setCancelable(false);
        dialog.setContentView(view);
        dialog.show();

    }


    static class ViewHolder {
        @BindView(R.id.progress_wheel)
        ProgressWheel progressWheel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
