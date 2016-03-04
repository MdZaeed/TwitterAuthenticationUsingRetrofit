package com.example.bs_86.fusiontwitterzed;

import com.squareup.okhttp.RequestBody;

import java.lang.reflect.Method;
import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by BS-86 on 1/19/2016.
 */
public interface TwitterClient {
/*
    More information about this api call on https://dev.twitter.com/oauth/reference/post/oauth2/token
*/
    @POST("oauth2/token")
    Call<JsonToken> getAuthenticationToken(
            @Header("Content-Type") String con_type,
            @Header("Authorization") String author,
            @Body RequestBody requestBody);
/*
    More information on https://dev.twitter.com/rest/reference/get/statuses/user_timeline
 */
    @GET("/1.1/statuses/user_timeline.json?count=100&screen_name=rogerfederer&exclude_replies=true&contributor_details=false&include_rts=false")
    Call<List<Tweet>> getRogerFedererTimeLine(
            @Header("Authorization") String author
    );
/*
    More information on https://dev.twitter.com/rest/reference/get/search/tweets
    */
    @GET("/1.1/search/tweets.json?count=1000&result_type=popular")
    Call<Tweets> getPopularTweets(
            @Query("q") String q,
            @Header("Authorization") String author
    );

}
