package giantbing.zonlinks.com.giantbaselibrary.Util;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;

/**
 * Created by giant on 2017/3/17.
 */

public class AnimotionHelper {

    private static int TRANSITION_DURATION = 500;

    /**
     * Created by giant on 2017/3/17.
     * 注意需在SETCONTEX之前调用！
     */


    public static int getTransitionDuration() {
        return TRANSITION_DURATION;
    }

    public static void setTransitionDuration(int TransitionDuration) {
        TRANSITION_DURATION = TransitionDuration;
    }

    public static void enableAtcivitytrans(Activity activity) {
        activity.getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static void initExplodeTransition(Context context) {
        // 设置一个 enter transition
        ((Activity) context).getWindow().setEnterTransition(new Explode().setDuration(TRANSITION_DURATION));
        // 设置一个 exit transition
        ((Activity) context).getWindow().setExitTransition(new Explode().setDuration(TRANSITION_DURATION));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static void initSlideTransition(Context context) {
        // 设置一个 enter transition
        ((Activity) context).getWindow().setEnterTransition(new Slide().setDuration(TRANSITION_DURATION));
        // 设置一个 exit transition
        ((Activity) context).getWindow().setExitTransition(new Slide().setDuration(TRANSITION_DURATION));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static void initFadeTransition(Context context) {
        // 设置一个 enter transition
        ((Activity) context).getWindow().setEnterTransition(new Fade().setDuration(TRANSITION_DURATION));
        // 设置一个 exit transition
        ((Activity) context).getWindow().setExitTransition(new Fade().setDuration(TRANSITION_DURATION));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static void initShareElementTransition(Context context) {
        // 共享元素 Transition 的 enter 效果
        ((Activity) context).getWindow().setSharedElementEnterTransition(new Slide().setDuration(TRANSITION_DURATION));
        // 共享元素 Transition 的 exit 效果
        ((Activity) context).getWindow().setSharedElementExitTransition(new Slide().setDuration(TRANSITION_DURATION));
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Bundle explodebundle(Context context) {
        initExplodeTransition(context);
        return ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Bundle slidebundle(Context context) {
        initSlideTransition(context);
        return ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Bundle fadebundle(Context context) {
        initFadeTransition(context);
        return ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Bundle sharedelementsbundle(Context context, View view, String shareName) {
        initShareElementTransition(context);
        // shareView: 需要共享的视图
        // "shareName": 设置的android:transitionName="shareName"
        ActivityOptions options = ActivityOptions
                .makeSceneTransitionAnimation((Activity) context, view, shareName);
        return options.toBundle();
    }


}
