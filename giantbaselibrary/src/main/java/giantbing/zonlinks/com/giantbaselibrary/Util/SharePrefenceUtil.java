package giantbing.zonlinks.com.giantbaselibrary.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by P on 2017/10/12.
 */

public class SharePrefenceUtil {

    public static final String CLASSID = "CLASSIDSS";

    private static SharedPreferences sharedPreferences;


    public static SharedPreferences getInstante(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public static void putClassId (Context context,@Nullable String id){
        getInstante(context).edit().putString(CLASSID,id).apply();
    }
    public static String getClassId (Context context){

        return getInstante(context).getString(CLASSID,"");

    }

}
