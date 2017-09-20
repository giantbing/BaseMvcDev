package giantbing.zonlinks.com.basemvcdev.Http;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import giantbing.zonlinks.com.basemvcdev.Bean.WetherBean;
import giantbing.zonlinks.com.basemvcdev.View.ProgressDialog;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
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
    private static final String BASEURL = "https://api.seniverse.com/v3/weather/";


    private static final int TIME_OUT=4;
    private Retrofit retrofit;
    private HttpService apiService;

    private HttpCilent() {
        /**
         * 构造函数私有化
         * 并在构造函数中进行retrofit的初始化
         */
        OkHttpClient client=new OkHttpClient();
        client.newBuilder().connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        /**
         * 由于retrofit底层的实现是通过okhttp实现的，所以可以通过okHttp来设置一些连接参数
         * 如超时等
         */
        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService=retrofit.create(HttpService.class);
    }


    private static class sinalInstance {
        static final HttpCilent instance = new HttpCilent();
    }

    public  static HttpCilent getInstance(){
        return sinalInstance.instance;
    }

    public Flowable<WetherBean> getWether(String location, Context context, ProgressDialog dialog) {
       return apiService.getStudent(key,location)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .compose(RxSchedulers.<WetherBean>io_main(context, dialog));

    }

}
