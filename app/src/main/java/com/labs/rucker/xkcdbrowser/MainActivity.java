package com.labs.rucker.xkcdbrowser;


/**
 * Created by Carlos on 3/19/2017.
 */

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    public WebView mWebView;
    private Button mbtnRandom;
    private Button mbtnCurrent;

    public final String URL = "https://xkcd.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews(){
        DataAdapter dataView = new DataAdapter();
        dataView.getImageView();
        getApi();
    }

    // Auto load current comic on start
     void getApi(){
         ApiCall initialCall = new ApiCall();
         initialCall.apiCall();
        //RETROFIT BUILDER
       final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
          //API call
       final RestApi service = retrofit.create(RestApi.class);
         mbtnCurrent = (Button) findViewById(R.id.button_current);
         mbtnCurrent.setPressed(true);
         mbtnCurrent.invalidate();
         mbtnCurrent.performClick();
         Handler handler = new Handler();
         Runnable r = new Runnable() {
             public void run() {
                 mbtnCurrent.setPressed(true);
                 mbtnCurrent.invalidate();
                 mbtnCurrent.performClick();
                 Handler handler1 = new Handler();
                 Runnable r1 = new Runnable() {
                     public void run() {
                         mbtnCurrent.setPressed(false);
                         mbtnCurrent.invalidate();
                     }
                 };
                 handler1.postDelayed(r1, 500);

             }
         };
         handler.postDelayed(r, 500);
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
                         final String altComic = response.body().getAlt();
                         String numString = String.valueOf(numComic);
                         String titleComic = response.body().getTitle();
                         Button altTextBtn = (Button) findViewById(R.id.alt_button);
                         altTextBtn.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View v) {
                                 Toast toast = Toast.makeText(getApplicationContext(), altComic, Toast.LENGTH_LONG);
                                 toast.show();
                             }
                         });
                         //Adapter
                         DataAdapter dataAdapter = new DataAdapter();
                         dataAdapter.setCurrentTitle(titleComic);
                         dataAdapter.setImageView(imgComic);
                         dataAdapter.setCurrentNum(numComic);
                         //VIEW
                         TextView title = (TextView) findViewById(R.id.title_view);
                         mWebView = (WebView) findViewById(R.id.web_view);
                         mWebView.loadUrl(imgComic);
                         TextView numView = (TextView) findViewById(R.id.comic_number);
                         title.setText(titleComic);
                         numView.setText(numString);
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


                 int n = rand.nextInt(615) + 1;
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
                         final String altComic = response.body().getAlt();
                         String numString = String.valueOf(numComic);
                         String titleComic = response.body().getTitle();

                         //Adapter
                         POJO pojo = new POJO();
                         pojo.setAlt(altComic);
                         DataAdapter dataAdapter = new DataAdapter();
                         dataAdapter.setAltText(altComic);
                         dataAdapter.setCurrentTitle(titleComic);
                         dataAdapter.setImageView(imgComic);
                         dataAdapter.setCurrentNum(numComic);
                         //VIEW
                         TextView title = (TextView) findViewById(R.id.title_view);
                         mWebView = (WebView) findViewById(R.id.web_view);
                         mWebView.loadUrl(imgComic);
                         Button altTextBtn = (Button) findViewById(R.id.alt_button);
                         altTextBtn.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View v) {
                                 Toast toast = Toast.makeText(getApplicationContext(), altComic, Toast.LENGTH_LONG);
                                 toast.show();
                             }
                         });

                         String altText = dataAdapter.getAltText();
                         Toast toast = Toast.makeText(getApplicationContext(), altText, Toast.LENGTH_LONG);
                         toast.show();
                         TextView numView = (TextView) findViewById(R.id.comic_number);
                         title.setText(titleComic);
                         numView.setText(numString);
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
