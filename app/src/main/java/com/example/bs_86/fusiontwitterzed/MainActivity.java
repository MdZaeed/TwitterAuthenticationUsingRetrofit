package com.example.bs_86.fusiontwitterzed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static volatile boolean value = false;
    public static String authToken = null;
    public TwitterRest twitterRest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void clickMe(View view) {
        twitterRest = new TwitterRest();
        getAuthenticationToken();
    }
/*
    Method to get the application_only authentication token
*/

    void getAuthenticationToken() {
/*
        Implementation of the required header information algorithm
        */
        String consumer = TwitterRest.CONSUMER_KEY + ":" + TwitterRest.CONSUMER_SECRET;
        String base64string = Base64.encodeToString(consumer.getBytes(), Base64.NO_WRAP);
        base64string="Basic " + base64string;

        RequestBody requestBody = new FormEncodingBuilder()
                .add("grant_type", "client_credentials")
                .build();


        Call<JsonToken> tokenCall = twitterRest.twitterClient.getAuthenticationToken(TwitterRest.CONTENT_TYPE,base64string, requestBody);
        tokenCall.enqueue(new Callback<JsonToken>() {
            @Override
            public void onResponse(Response<JsonToken> response, Retrofit retrofit) {
                Toast.makeText(getApplicationContext(), response.body().getTokenType(), Toast.LENGTH_LONG).show();

                authToken = response.body().getAccessToken();
                value = true;

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("Token", response.body().getAccessToken());
                startActivity(intent);

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("Error: " , "Cannot convert the response");
            }
        });
    }

}
