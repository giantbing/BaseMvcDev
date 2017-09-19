package giantbing.zonlinks.com.basemvcdev.Http.Base;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by giant on 2017/9/19.
 */

public abstract class ProgressSubscriber<T> extends BaseSubscriber<T> {
    private Dialog dialog;

    protected ProgressSubscriber(Dialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void onError(Throwable t) {
        super.onError(t);
        if (dialog!=null)
            dialog.dismiss();
    }

    @Override
    public void onComplete() {
        super.onComplete();
        if (dialog!=null)
            dialog.dismiss();
    }
}
