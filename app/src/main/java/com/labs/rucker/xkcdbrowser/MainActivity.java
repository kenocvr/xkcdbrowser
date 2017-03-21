package com.labs.rucker.xkcdbrowser;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Random;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private POJO comic;
    private ImageView mImageView;
    private Button mbtnRandom;
    private Button mbtnCurrent;
    private Matrix matrix = new Matrix();
    private ScaleGestureDetector scaleGestureDetector;
    private String num = "0";

    String url = "https://xkcd.com";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }



    private void initViews(){

        DataAdapter dataView = new DataAdapter();
//        ComicManager comicAdapter = new ComicManager();
        dataView.getImageView();

        getApi();

    }


     void getApi(){
    //    //RETROFIT BUILDER////
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


     //   //API call/////
       final RestApi service = retrofit.create(RestApi.class);
         mbtnCurrent = (Button) findViewById(R.id.button_current);
         mbtnCurrent.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Call<POJO> call = service.getJSONcurrent();
                 call.enqueue(new Callback<POJO>() {
                     @Override
                     public void onResponse(Response<POJO> response, Retrofit retrofit) {
                         //Response
                         String imgComic = response.body().getImg();
                         int numComic = response.body().getNum();
                         String numString = String.valueOf(numComic);
                         String titleComic = response.body().getTitle();
                         String altComic = response.body().getAlt(); //alternate text
                         //Adapter
                         DataAdapter dataAdapter = new DataAdapter();
                         dataAdapter.setCurrentTitle(titleComic);
                         dataAdapter.setImageView(imgComic);
                         dataAdapter.setCurrentNum(numComic);
                         //VIEW
                         TextView title = (TextView) findViewById(R.id.title_view);
                         ImageView imageView = (ImageView) findViewById(R.id.imageView);
                         TextView numView = (TextView) findViewById(R.id.comic_number);
                         title.setText(titleComic);
                         numView.setText(numString);
                         Glide.with(getApplicationContext().getApplicationContext()).load(imgComic).into(imageView);

                     }

                     @Override
                     public void onFailure(Throwable t) {

                     }
                 });
             }
         });
         mbtnRandom = (Button) findViewById(R.id.button_random);
         mbtnRandom.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Random rand = new Random();

                 int  n = rand.nextInt(615) + 1;
                 String nString = String.valueOf(n);
                 ComicManager randAdapter = new ComicManager();
                 randAdapter.setRandNum(nString);
                    String num = randAdapter.getRandNum();
                 Call<POJO> call = service.getJSON(num);

                 call.enqueue(new Callback<POJO>() {
                     @Override
                     public void onResponse(Response<POJO> response, Retrofit retrofit) {

                         //Response
                         String imgComic = response.body().getImg();
                         int numComic = response.body().getNum();
                         String numString = String.valueOf(numComic);
                         String titleComic = response.body().getTitle();
                         String altComic = response.body().getAlt(); //alternate text
                         //Adapter
                         DataAdapter dataAdapter = new DataAdapter();
                         dataAdapter.setCurrentTitle(titleComic);
                         dataAdapter.setImageView(imgComic);
                         dataAdapter.setCurrentNum(numComic);
                         //VIEW
                         TextView title = (TextView) findViewById(R.id.title_view);
                         ImageView imageView = (ImageView) findViewById(R.id.imageView);
                         TextView numView = (TextView) findViewById(R.id.comic_number);
                         title.setText(titleComic);
                         numView.setText(numString);
                         Glide.with(getApplicationContext().getApplicationContext()).load(imgComic).into(imageView);

                     }

                     @Override
                     public void onFailure(Throwable t) {
                         Log.d("Error",t.getMessage());
                     }


                 });
             }
         });







    }

}
