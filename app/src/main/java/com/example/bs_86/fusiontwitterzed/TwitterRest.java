package com.example.bs_86.fusiontwitterzed;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by BS-86 on 1/19/2016.
 */
public class TwitterRest {
    /*
    Its better practice to register your appname and get these credentials of your own app,but if
    you are absolutely new then dont chnage the appname and credentials*/
    public static final String CONSUMER_KEY="x7Xcspf6BJXuhxzz560EctdD6";
    public static final String CONSUMER_SECRET="BQm1nFWetgGSZ1XSuQE7IUOPhZpmyNV7TLtMqtNUKHjn3icxla";
    public static final String CONTENT_TYPE="application/x-www-form-urlencoded;charset=UTF-8";
    public TwitterClient twitterClient;
    public Retrofit retrofit;
    public final String BASE_URL ="https://api.twitter.com/";

    TwitterRest()
    {

/*
        Here OkhttpClient is being used to see the response from the api call in the Logcat
*/
        OkHttpClient okHttpClient=new OkHttpClient();
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.interceptors().add(httpLoggingInterceptor);

/*
        GsononverterFactory is being used to parse the json reply through retrofit
*/
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        twitterClient=retrofit.create(TwitterClient.class);
    }

}
