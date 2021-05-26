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
    TextView tvSubHubCount;
    Button btnSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_detail);
        ivBanner = findViewById(R.id.ivBanner);
        ivChannelImage = findViewById(R.id.ivChannelImage);
        tvChannelName = findViewById(R.id.tvChannelName);
        tvSubscriberCount = findViewById(R.id.tvSubscriberCount);
        tvSubHubCount = findViewById(R.id.tvSubHubCount);
        btnSub = findViewById(R.id.btnSub);


        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("part", "brandingSettings");
        //params.put("forUsername", "RamenWarlord");
        params.put("id", "UC5iC25Jaeo6OGZB60Xsr7sQ");
        params.put("key", "AIzaSyCw6VKEis4dqqOWfXE0SwDEcE9vqp6aTTA");
        client.get("https://www.googleapis.com/youtube/v3/channels", params, new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, String response) {
                        Log.d("bruh", "sheesh3..");
                        // called when response HTTP status is "200 OK"
                        //Log.d("bruh",response);
                        try {
                            Log.d("bruh", "sheesh4..");
                            JSONObject mainObject = new JSONObject(response);
                            Log.d("bruh",mainObject.toString(4));

                            //JSONObject json = mainObject.getJSONObject("items");

                            //Log.d("bruh",json.toString(3));
                            //tvChannelName.setText(();

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


        Glide.with(this)
                .load("https://yt3.ggpht.com/Su_HQMxOIcp--VzfW7CRV0_XkvnXjYrXV1U9CZy6WCmyPwGm_jQO-bHbyqGmk1PJaqhxwVqT8w")
                .into(ivBanner);


    }
}