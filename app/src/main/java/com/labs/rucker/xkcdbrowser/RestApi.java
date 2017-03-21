package com.labs.rucker.xkcdbrowser;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Carlos on 3/15/2017.
 */

public interface RestApi {


            @GET("/info.0.json")
            Call<POJO> getJSONcurrent();

            @GET("/{num}/info.0.json")
            Call<POJO> getJSON(@Path(value = "num", encoded = true) String comic);


}
