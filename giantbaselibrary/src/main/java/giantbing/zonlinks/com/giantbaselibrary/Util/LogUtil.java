package giantbing.zonlinks.com.giantbaselibrary.Util;


import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import giantbing.zonlinks.com.giantbaselibrary.BuildConfig;

/**
 * Created by P on 2017/9/7.
 */

public class LogUtil {
    private static  boolean DEBUG = true ;
    private static boolean isInit = false;
    public static void setDEBUG(boolean DEBUG) {
        LogUtil.DEBUG = DEBUG;
    }

    private static void initLog() {
        if (isInit) return;
        Logger.addLogAdapter(new AndroidLogAdapter());
        isInit = true;
    }

    public static void v(Object msg) {
        if (DEBUG) {
            initLog();
            Logger.v(msg.toString(), msg);
        }

    }

    public static void w(String msg) {
        if (DEBUG) {
            initLog();
            Logger.w(msg);
        }

    }
    public static void d(String message, Object... args) {
        if (DEBUG) {
            initLog();
            Logger.d(message, args);
        }
    }

    public static void d(Object object) {
        if (DEBUG) {
            initLog();
            Logger.d(object);
        }

    }

    public static void e(String message, Object... args) {
        if (DEBUG) {
            initLog();
            Logger.e(null, message, args);
        }
    }

    public static void e(Throwable throwable, String message, Object... args) {
        if (DEBUG) {
            initLog();
            Logger.e(throwable, message, args);
        }
    }

    public static void i(String message, Object... args) {
        if (DEBUG) {
            initLog();
            Logger.i(message, args);
        }
    }

    public static void v(String message, Object... args) {
        if (DEBUG) {
            initLog();
            Logger.v(message, args);
        }
    }

    public static void w(String message, Object... args) {
        if (DEBUG) {
            initLog();
            Logger.w(message, args);
        }
    }
    /**
     * Tip: Use this for exceptional situations to log
     * ie: Unexpected errors etc
     */
    public static void wtf(String message, Object... args) {
        if (DEBUG) {
            initLog();
            Logger.wtf(message, args);
        }

    }

    /**
     * Formats the given json content and print it
     */
    public static void json(String json) {
        if (DEBUG) {
            initLog();
            Logger.json(json);
        }

    }

    /**
     * Formats the given xml content and print it
     */
    public static void xml(String xml) {
        if (DEBUG) {
            initLog();
            Logger.xml(xml);
        }
    }
}
