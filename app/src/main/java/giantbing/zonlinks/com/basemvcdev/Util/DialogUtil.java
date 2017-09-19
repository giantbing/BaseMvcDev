package giantbing.zonlinks.com.basemvcdev.Util;

import android.content.Context;

import giantbing.zonlinks.com.basemvcdev.Http.HttpCilent;
import giantbing.zonlinks.com.basemvcdev.View.ProgressDialog;

/**
 * Created by giant on 2017/9/19.
 */

public class DialogUtil {
    private static ProgressDialog progressDialog;
    private static ProgressDialog initProgessDialog(Context context){
        if (progressDialog == null)
        progressDialog = new ProgressDialog(context,false);
        return progressDialog ;
    }

    public  static ProgressDialog getProgressInstance(Context context){
        return initProgessDialog(context);
    }
}
