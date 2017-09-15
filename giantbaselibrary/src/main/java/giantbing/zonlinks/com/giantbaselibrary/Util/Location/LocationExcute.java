package giantbing.zonlinks.com.giantbaselibrary.Util.Location;

/**
 * Created by P on 2017/9/11.
 */

public interface LocationExcute {
    void onLocation(double lat, double lng);
    void onErro(String msg);
}
