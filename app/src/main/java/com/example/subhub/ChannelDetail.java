package com.example.subhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler;

import org.json.JSONException;
import org.w3c.dom.Text;

import okhttp3.Headers;

public class ChannelDetail extends AppCompatActivity {

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

        Log.d("bruh", "sheesh1");
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("part", "brandingSettings");
        //params.put("forUsername", "RamenWarlord");
        params.put("id", "UC5iC25Jaeo6OGZB60Xsr7sQ");
        params.put("key", "AIzaSyCw6VKEis4dqqOWfXE0SwDEcE9vqp6aTTA");
        Log.d("bruh", "sheesh2");
        client.get("https://www.googleapis.com/youtube/v3/channels", params, new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, String response) {
                        Log.d("bruh", "sheesh3..");
                        // called when response HTTP status is "200 OK"
                        Log.d("bruh",response);
                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String errorResponse, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        Log.d("bruh", errorResponse);
                    }
                }
        );


    }
}