package giantbing.zonlinks.com.giantbaselibrary.Util.Location;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import giantbing.zonlinks.com.giantbaselibrary.Util.LogUtil;


/**
 * Created by P on 2017/9/11.
 */

public class Location_Util {

    private LocationOption locationOption;

    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    private AMapLocationListener mLocationListener;

    public Location_Util(LocationOption locationOption,Context context) {
        this.locationOption = locationOption;
        mLocationClient = new AMapLocationClient(context);
        mLocationClient.setLocationOption(setOption(locationOption));
    }

    private AMapLocationClientOption setOption(LocationOption locationOption) {
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(locationOption.getLocationMode());
        if (locationOption.isNeedAddress()){
            mLocationOption.setNeedAddress(true);
        }
        if (locationOption.isMockEnable()){
            //设置是否允许模拟位置,默认为true，允许模拟位置
            mLocationOption.setMockEnable(true);
        }
        if (!locationOption.isLocationCacheEnable()){
            mLocationOption.setLocationCacheEnable(false);
        }
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(locationOption.getHttpTimeOut());
        //关闭缓存机制

        if (locationOption.isSingle()) {
        //获取一次定位结果：
        //该方法默认为false。
            mLocationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
            mLocationOption.setOnceLocationLatest(true);

        } else {
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
            mLocationOption.setInterval(locationOption.getInterval());
        }
        return mLocationOption;
    }


    public void startLocation(final LocationExcute locationExcute){
        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //可在其中解析amapLocation获取相应内容。
                        locationExcute.onLocation(amapLocation.getLatitude(),amapLocation.getLongitude());
                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        locationExcute.onErro("定位失败, 错误信息:"
                                + amapLocation.getErrorInfo());
                        LogUtil.e( "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
            }
        };
        mLocationClient.setLocationListener(mLocationListener);
        mLocationClient.startLocation();
    }


    public void stopLocation() {
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
    }

    public void destoyLocation() {
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }


}
