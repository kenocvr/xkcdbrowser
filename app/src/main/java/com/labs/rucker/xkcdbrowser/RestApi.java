package com.labs.rucker.xkcdbrowser;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Carlos on 3/15/2017.
 */

public interface RestApi {

             @GET("/{num}/info.0.json")

             Call<POJO> getJSON(@Path(value = "num", encoded = true) String artist);

    // @GET("/artists/{artist}/events/search.json?api_version=2.0&app_id=RUCKERLABS&location=use_geoip&radius=150")
       // Call<List<POJO>> getJSON(@Path(value = "artist", encoded = true) String artist);


}
