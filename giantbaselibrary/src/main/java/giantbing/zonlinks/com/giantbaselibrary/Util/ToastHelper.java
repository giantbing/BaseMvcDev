package giantbing.zonlinks.com.giantbaselibrary.Util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

/**@author giant
 * Created by giant on 2017/3/27.
 */

public class ToastHelper {

    public static int LENGTH_SHORT;
    public static int LENGTH_LONG;
    static {
        LENGTH_SHORT = Toast.LENGTH_SHORT;
        LENGTH_LONG = Toast.LENGTH_LONG;
    }
    /**
     * @param withIcon 是否开启图标
     */
    public static void error(@NonNull Context context, @NonNull CharSequence message, int duration, boolean withIcon){
        Toasty.error(context, message, duration, withIcon).show();
    }
    public static void error(@NonNull Context context, @NonNull CharSequence message){
        Toasty.error(context, message, LENGTH_SHORT, true).show();
    }
    /**
     * @param withIcon 是否开启图标
     */
    public static void success(@NonNull Context context, @NonNull CharSequence msg, int Length, boolean withIcon){
        Toasty.success(context, msg, Length, withIcon).show();
    }
    public static void success(@NonNull Context context, @NonNull CharSequence msg){
        Toasty.success(context, msg, LENGTH_SHORT, true).show();
    }
    /**
     * @param withIcon 是否开启图标
     */
    public static void info(@NonNull Context context, @NonNull CharSequence msg, int Length, boolean withIcon){
        Toasty.info(context, msg, Length, withIcon).show();
    }
    public static void info(@NonNull Context context, @NonNull CharSequence msg){
        Toasty.info(context, msg, LENGTH_SHORT, true).show();
    }
    /**
     * @param withIcon 是否开启图标
     */
    public static void warning(@NonNull Context context, @NonNull CharSequence msg, int Length, boolean withIcon){
        Toasty.warning(context, msg, Length, withIcon).show();
    }
    public static void warning(@NonNull Context context, @NonNull CharSequence msg){
        Toasty.warning(context, msg, LENGTH_SHORT, true).show();
    }

    public static void normal(@NonNull Context mContext, @NonNull CharSequence msg){
        Toasty.normal(mContext, msg).show();
    }

    public static void normal(@NonNull Context mContext, @NonNull CharSequence msg, Drawable mdrawable){
        Toasty.normal(mContext,msg, mdrawable).show();
    }
    /**
     * @param withIcon 是否显示tint
     */
    public static void custom(@NonNull Context mContext, @NonNull CharSequence msg, @DrawableRes int iconRes, @ColorInt int textColor, @ColorInt int tintColor, int duration, boolean withIcon, boolean shouldTint){
        Toasty.custom(mContext, msg, iconRes, textColor, tintColor, duration, withIcon, shouldTint).show();
    }
}
