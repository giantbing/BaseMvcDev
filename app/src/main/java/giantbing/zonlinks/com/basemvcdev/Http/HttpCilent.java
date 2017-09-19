package giantbing.zonlinks.com.basemvcdev.Http;

import android.util.Log;

import com.google.gson.Gson;

import giantbing.zonlinks.com.basemvcdev.Bean.WetherBean;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static giantbing.zonlinks.com.basemvcdev.C.BASEURL;

/**
 * Created by P on 2017/7/7.
 */

public class HttpCilent {
    private static final String key = "39xpx6tkhfjjomo4";

    public static Observable<WetherBean> getWether(String location) {

        Retrofit retrofit = new Retrofit.Builder()
                //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        HttpService apiStores = retrofit.create(HttpService.class);
        Gson gson = new Gson();

        Observable<WetherBean> wetherBeanObservable = apiStores.getStudent(key,location);
        return wetherBeanObservable;
    }

}
