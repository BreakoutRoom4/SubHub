package com.example.subhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import okhttp3.Headers;

public class ChannelDetail extends AppCompatActivity {
    Context context;
    ImageView ivBanner;
    ImageView ivChannelImage;
    TextView tvChannelName;
    TextView tvSubscriberCount;
    TextView tvDescription;
    String bannerurl;
    String channelname;
    String subscribers;
    String profileimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String YOUTUBE_CHANNEL_ID = getIntent().getStringExtra("YOUTUBE_CHANNEL_ID");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_detail);
        ivBanner = findViewById(R.id.ivBanner);
        ivChannelImage = findViewById(R.id.ivChannelImage);
        tvChannelName = findViewById(R.id.tvChannelName);
        tvSubscriberCount = findViewById(R.id.tvSubscriberCount);
        tvDescription = findViewById(R.id.tvDescription);
        bannerurl = "";
        profileimage = "";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("part", "brandingSettings,snippet,contentDetails,statistics");
        //params.put("forUsername", "RamenWarlord");
        params.put("id", "YOUTUBE_CHANNEL_ID");
        params.put("id", "UC5iC25Jaeo6OGZB60Xsr7sQ");


        params.put("key", "AIzaSyCw6VKEis4dqqOWfXE0SwDEcE9vqp6aTTA");
        client.get("https://www.googleapis.com/youtube/v3/channels", params, new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, String response) {
                        Log.d("bruh", "sheesh3..");
                        // called when response HTTP status is "200 OK"
                        //Log.d("bruh",response);
                        try {
                            JSONObject mainObject = new JSONObject(response);
                            Log.d("bruh",mainObject.toString(4));
                            bannerurl = mainObject.getJSONArray("items").getJSONObject(0).getJSONObject("brandingSettings").getJSONObject("image").getString("bannerExternalUrl");

                            //Log.d("bruh", bannerurl);
                            profileimage = mainObject.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("default").getString("url");

                            Log.d("bruh", profileimage);
                            subscribers = (mainObject.getJSONArray("items").getJSONObject(0).getJSONObject("statistics").getString("viewCount"));
                            channelname = mainObject.getJSONArray("items").getJSONObject(0).getJSONObject("brandingSettings").getJSONObject("channel").getString("title");
                            tvDescription.setText(mainObject.getJSONArray("items").getJSONObject(0).getJSONObject("brandingSettings").getJSONObject("channel").getString("description"));
                            tvChannelName.setText(channelname);
                            tvSubscriberCount.setText("Number of views: " + subscribers);

                            bannerurl = bannerurl.replace("\\", "");
                            profileimage=profileimage.replace("\\", "");
                            Log.d("bruh", profileimage);
                            Glide.with(getApplicationContext())
                                    .load(bannerurl)
                                    .into(ivBanner);

                            Glide.with(getApplicationContext())
                                    .load(profileimage)
                                    .override(400,400)
                                    .into(ivChannelImage);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String errorResponse, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        Log.d("bruh", errorResponse);
                    }
                }
        );


/*

        Glide.with(this)
                .load(bannerurl)
                .into(ivBanner);



*/


    }
}