package com.labs.rucker.xkcdbrowser;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;



/**
 * Created by Carlos on 12/6/2017.
 */

public class ApiCall extends MainActivity{

    void apiCall(){
        //    //RETROFIT BUILDER////
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //   //API call/////
        final RestApi service = retrofit.create(RestApi.class);
                Call<POJO> call = service.getJSONcurrent();
                call.enqueue(new Callback<POJO>() {
                    @Override
                    public void onResponse(Response<POJO> response, Retrofit retrofit) {
                        //Response
                        String imgComic = response.body().getImg();
                        int numComic = response.body().getNum();
                        final String altComic = response.body().getAlt();
                        String numString = String.valueOf(numComic);
                        String titleComic = response.body().getTitle();
                        //Adapter
                        DataAdapter dataAdapter = new DataAdapter();
                        dataAdapter.setCurrentTitle(titleComic);
                        dataAdapter.setImageView(imgComic);
                        dataAdapter.setCurrentNum(numComic);
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });

        }



    }

