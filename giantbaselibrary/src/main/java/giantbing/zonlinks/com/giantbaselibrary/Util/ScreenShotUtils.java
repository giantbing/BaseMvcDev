package giantbing.zonlinks.com.giantbaselibrary.Util;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by P on 2017/7/8.
 */

public class ScreenShotUtils {
    public static String IMGSAVEPATH = Environment.getExternalStorageDirectory().toString() + File.separator+"zonglinks"+File.separator+"img"+File.separator;
    /**
     * 进行截取屏幕
     * @param pActivity
     * @return bitmap
     */
    public static Bitmap takeScreenShot(View pActivity) {
        Bitmap bitmap=null;
//        View view=pActivity.getWindow().getDecorView();
//        // 设置是否可以进行绘图缓存
        pActivity.setDrawingCacheEnabled(true);
        // 如果绘图缓存无法，强制构建绘图缓存
        pActivity.destroyDrawingCache();
        pActivity.buildDrawingCache();
        // 返回这个缓存视图
        bitmap=pActivity.getDrawingCache();

        // 获取状态栏高度
        Rect frame=new Rect();
        // 测量屏幕宽和高
        pActivity.getWindowVisibleDisplayFrame(frame);
        int stautsHeight=frame.top;


        // 根据坐标点和需要的宽和高创建bitmap
       // bitmap=Bitmap.createBitmap(bitmap, 0, stautsHeight, pActivity.getWidth(), pActivity.getHeight());
        return bitmap;
    }


    /**
     * 保存图片到sdcard中
     * @param pBitmap
     */
    private static boolean savePic(Bitmap pBitmap,String strName)
    {
        FileOutputStream fos=null;
        try {
            fos=new FileOutputStream(strName);
            if(null!=fos)
            {
                pBitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.flush();
                fos.close();
                return true;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 截图
     * @param pActivity
     * @return 截图并且保存sdcard成功返回true，否则返回false
     */
    public static boolean shotBitmap(View pActivity)
    {

        return  ScreenShotUtils.savePic(takeScreenShot(pActivity), IMGSAVEPATH+System.currentTimeMillis()+".png");
    }

    public static String  shotBitmap(View pActivity,boolean isneedPath) {

        String Path =IMGSAVEPATH+System.currentTimeMillis()+".png";
        ScreenShotUtils.savePic(takeScreenShot(pActivity), Path);

        return  Path;
    }
}
