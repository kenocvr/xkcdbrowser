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

//                        //Button altTextBtn = (Button) findViewById(R.id.alt_button);
//                        altTextBtn.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Toast toast = Toast.makeText(getApplicationContext(), altComic, Toast.LENGTH_LONG);
//                                toast.show();
//
//                            }
//                        });

                        //Adapter
                        DataAdapter dataAdapter = new DataAdapter();
                        dataAdapter.setCurrentTitle(titleComic);
                        dataAdapter.setImageView(imgComic);
                        dataAdapter.setCurrentNum(numComic);

//                        //VIEW
//                        TextView title = (TextView) findViewById(R.id.title_view);
//                        mWebView = (WebView) findViewById(R.id.web_view);
//                        mWebView.loadUrl(imgComic);

//                        //ImageView imageView = (ImageView) findViewById(R.id.imageView);
//                        TextView numView = (TextView) findViewById(R.id.comic_number);
//                        title.setText(titleComic);
//                        numView.setText(numString);
//                        // Glide.with(getApplicationContext()).load(imgComic).into(imageView);

                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });

        }



    }

