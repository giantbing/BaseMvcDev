package giantbing.zonlinks.com.basemvcdev;

import android.app.Application;
import android.content.Context;

import giantbing.zonlinks.com.giantbaselibrary.Util.LogUtil;

/**@author giant
 * Created by giant on 2017/3/21.
 */

@SuppressWarnings("unused")
public  class BaseApp extends Application {
    private static BaseApp appContext;

    @Override public void onCreate() {
        super.onCreate();
        appContext = this;
        LogUtil.setDEBUG(BuildConfig.DEBUG);

    }

    @Override protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static BaseApp getAppContext() {
        return appContext;
    }



}
