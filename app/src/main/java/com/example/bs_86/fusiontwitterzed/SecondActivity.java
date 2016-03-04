package com.example.bs_86.fusiontwitterzed;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class SecondActivity extends AppCompatActivity {

    TwitterRest twitterRest;
    Intent intent;
    TweetListAdapter tweetListAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    String searchString="s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        twitterRest=new TwitterRest();
        intent=getIntent();

        swipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipeView);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String token = intent.getStringExtra("Token");
                if (!tweetListAdapter.data.get(0).getUser().getName().equals("Roger Federer")) {
                    getPopularTweetFilteredByText("BEARER " + token);
                } else {
                    getFederderTimelineFromTweeter("BEARER " + token);
                }
            }
        });
    }

    public void federerButtonAction(View view)
    {
        String token=intent.getStringExtra("Token");
        getFederderTimelineFromTweeter("BEARER " + token);
    }

    public void gettingPopularTweetButtonAction(View view)
    {
        EditText editText=(EditText) findViewById(R.id.et_search_filter);
        searchString=editText.getText().toString();
        String token=intent.getStringExtra("Token");
        getPopularTweetFilteredByText("BEARER " + token);
    }


    void getFederderTimelineFromTweeter(String token) {
        Call<List<Tweet>> tokenCall = twitterRest.twitterClient.getRogerFedererTimeLine(token);
        tokenCall.enqueue(new Callback<List<Tweet>>() {

            @Override
            public void onResponse(Response<List<Tweet>> response, Retrofit retrofit) {
                listing(response.body());
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("Error: ","Could not parse the api call response of federer tweets");
            }
        });
    }

    void getPopularTweetFilteredByText(String token) {

        Call<Tweets> tokenCall=null;
        if (searchString.equals(""))
        {
            tokenCall = twitterRest.twitterClient.getPopularTweets("s", token);
        }
        else
        {
            tokenCall = twitterRest.twitterClient.getPopularTweets(searchString, token);
        }
        tokenCall.enqueue(new Callback<Tweets>() {

            @Override
            public void onResponse(Response<Tweets> response, Retrofit retrofit) {
                listing(response.body().getStatuses());
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("Error: ","Could not parse api call response of popular tweets");
            }
        });
    }

    public void listing(List<Tweet> tweets)
    {
        ListView listView=(ListView) findViewById(R.id.tweetsList);
        tweetListAdapter=new TweetListAdapter(getApplicationContext(),new ArrayList<>(tweets));
        listView.setAdapter(tweetListAdapter);
    }
}
