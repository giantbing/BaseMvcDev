package giantbing.zonlinks.com.giantbaselibrary.Util;


import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import giantbing.zonlinks.com.giantbaselibrary.BuildConfig;

/**
 * Created by P on 2017/9/7.
 */

public class LogUtil {
    private static final String TAG = "2333+util\t";
    private static final boolean DEBUG = BuildConfig.DEBUG;
    private static boolean isInit = false;

    private static void initLog() {
        if (isInit) return;
        Logger.addLogAdapter(new AndroidLogAdapter());
        isInit = true;
    }

    public static void i(Object... msg) {
        if (DEBUG) {
            initLog();
            Logger.i(TAG, msg);
            //android.util.Log.i(TAG, msg);
        }

    }

    public static void i(String msg, Object... data) {
        if (DEBUG) {
            initLog();
            Logger.i(msg, data);
            //android.util.Log.i(TAG, msg);
        }

    }

    public static void e(Object... msg) {
        if (DEBUG) {
            initLog();
            Logger.e(TAG, msg);
        }
    }

    public static void e(String message, Object... args) {
        if (DEBUG) {
            initLog();
            Logger.e(message, args);
        }
    }

    public static void e(Throwable throwable, String message, Object... args) {
        if (DEBUG) {
            initLog();
            Logger.e(throwable, TAG, args);
        }
    }

    public static void d(Object... msg) {
        if (DEBUG) {
            initLog();
            Logger.d(TAG, msg);
        }

    }

    public static void d(String msg, Object... args) {
        if (DEBUG) {
            initLog();
            Logger.d(msg, args);
        }

    }

    public static void v(Object msg) {
        if (DEBUG) {
            initLog();
            Logger.v(TAG, msg);
        }

    }

    public static void w(Object msg) {
        if (DEBUG) {
            initLog();
            Logger.w(TAG, msg);
        }

    }

}
