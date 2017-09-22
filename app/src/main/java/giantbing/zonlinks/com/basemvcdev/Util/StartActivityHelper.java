package giantbing.zonlinks.com.basemvcdev.Util;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import giantbing.zonlinks.com.basemvcdev.Activity.WheatherActivity;
import giantbing.zonlinks.com.giantbaselibrary.Util.AnimotionHelper;


/*
 * Created by giant on 2017/3/17.
 */

public class StartActivityHelper {
    public StartActivityHelper() {
    }

    public static void startActivityTraslate(Context context, Class<?> cls, Anmotion anmotionEnum) {
        Intent intent = new Intent(context, cls);
        switch (anmotionEnum) {
            case FADE:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    context.startActivity(intent, AnimotionHelper.fadebundle(context));
                else
                    context.startActivity(intent);
                break;
            case EXPLODE:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    context.startActivity(intent, AnimotionHelper.explodebundle(context));
                else
                    context.startActivity(intent);
                break;
            case Slide:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    context.startActivity(intent, AnimotionHelper.slidebundle(context));
                else
                    context.startActivity(intent);
                break;
            case NONE:
                context.startActivity(intent);
                break;
        }

    }

    /**
     *共享视图跳转
     *@param shareView 需要共享的视图
     *@param shareName 设置的android:transitionName="shareName"
     * */

    public static void startActivityWithShare(Context context, Class<?> cls, View shareView, String shareName) {
        Intent intent = new Intent(context, cls);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            context.startActivity(intent, AnimotionHelper.sharedelementsbundle(context, shareView, shareName));
        else
            context.startActivity(intent);
    }

    public enum Anmotion {
        FADE,
        EXPLODE,
        Slide,
        NONE
    }

}
