package giantbing.zonlinks.com.giantbaselibrary.Util.Location;

import com.amap.api.location.AMapLocationClientOption;

/**
 * Created by P on 2017/9/11.
 */

public class LocationOption {

    private AMapLocationClientOption.AMapLocationMode locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy; //定位模式
    private boolean IsSingle = false;
    private boolean NeedAddress = false;
    private boolean MockEnable = true;
    private boolean LocationCacheEnable = true;
    private long Interval = 2000;
    private long HttpTimeOut = 30000;

    public LocationOption(Buider buider) {
        if (buider != null) {
            initClass(buider);
        }
    }

    private void initClass(Buider buider) {
        this.IsSingle = buider.IsSingle;
        this.NeedAddress = buider.NeedAddress;
        this.MockEnable = buider.MockEnable;
        this.LocationCacheEnable = buider.LocationCacheEnable;
        this.Interval = buider.Interval;
        this.HttpTimeOut = buider.HttpTimeOut;
        this.locationMode = buider.locationMode;
    }

    public AMapLocationClientOption.AMapLocationMode getLocationMode() {
        return locationMode;
    }

    public boolean isSingle() {
        return IsSingle;
    }

    public boolean isNeedAddress() {
        return NeedAddress;
    }

    public boolean isMockEnable() {
        return MockEnable;
    }

    public boolean isLocationCacheEnable() {
        return LocationCacheEnable;
    }

    public long getInterval() {
        return Interval;
    }

    public long getHttpTimeOut() {
        return HttpTimeOut;
    }
    public static Buider Builder() {

        return new Buider();

    }
    public final static class Buider {
        private AMapLocationClientOption.AMapLocationMode locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy; //定位模式
        private boolean IsSingle = false;
        private boolean NeedAddress = false;
        private boolean MockEnable = true;
        private boolean LocationCacheEnable = true;
        private long Interval = 2000;
        private long HttpTimeOut = 30000;

        public Buider setLocationMode(AMapLocationClientOption.AMapLocationMode locationMode) {
            this.locationMode = locationMode;
            return this;
        }

        public Buider setSingle(boolean single) {
            IsSingle = single;
            return this;
        }

        public Buider setNeedAddress(boolean needAddress) {
            NeedAddress = needAddress;
            return this;
        }

        public Buider setMockEnable(boolean mockEnable) {
            MockEnable = mockEnable;
            return this;
        }

        public Buider setLocationCacheEnable(boolean locationCacheEnable) {
            LocationCacheEnable = locationCacheEnable;
            return this;
        }

        public Buider setInterval(long interval) {
            Interval = interval;
            return this;
        }

        public Buider setHttpTimeOut(long httpTimeOut) {
            HttpTimeOut = httpTimeOut;
            return this;
        }

        public LocationOption buld() {
            return new LocationOption(this);
        }
    }
}
