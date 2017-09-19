package giantbing.zonlinks.com.basemvcdev.Http;

import giantbing.zonlinks.com.basemvcdev.Bean.WetherBean;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by P on 2017/9/19.
 */

public interface HttpService {
    @GET("now.json")
    Observable<WetherBean> getStudent(@Query("key") String key,
                                      @Query("location") String location);
}
