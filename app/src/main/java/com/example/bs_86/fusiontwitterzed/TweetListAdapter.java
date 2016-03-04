package com.example.bs_86.fusiontwitterzed;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BS-86 on 2/12/2016.
 */
public class TweetListAdapter extends BaseAdapter {

    public List<Tweet> data=new ArrayList<Tweet>();
    private Context context;
    private static LayoutInflater inflater=null;

    TweetListAdapter(Context context,ArrayList<Tweet> tweets)
    {
        this.context=context;
        data=tweets;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View newView=LayoutInflater.from(context).inflate(R.layout.tweet_list_item, parent, false);

        TextView tweetText=(TextView) newView.findViewById(R.id.tweetText);
        TextView timeText=(TextView) newView.findViewById(R.id.dateAndTime);
        TextView userName=(TextView) newView.findViewById(R.id.userName);

        tweetText.setText(data.get(position).getText());
        tweetText.setMovementMethod(LinkMovementMethod.getInstance());
        timeText.setText(data.get(position).getCreated_at());
        userName.setText(data.get(position).getUser().getName());
        return newView;
    }
}
