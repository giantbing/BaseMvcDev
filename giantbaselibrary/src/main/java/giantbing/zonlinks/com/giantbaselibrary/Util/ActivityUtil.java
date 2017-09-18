package giantbing.zonlinks.com.giantbaselibrary.Util;

import android.app.Activity;
import android.view.WindowManager;

/**
 * Created by P on 2017/9/18.
 */

public class ActivityUtil {

    /**
     * @param mActivity 需要沉浸式的Activity
     *
     * */
    public static void setTranSlucent(Activity mActivity) {
        //透明状态栏
        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }
}
