package com.example.subhub;

import android.app.Application;

import com.parse.Parse;


public class ParseApplication extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("fs9pcKdyy3ImkU67fxBpXPerD52wqxeVARZtdjgv")
                .clientKey("JU0OhnpOqAXbj44TesiUySlx3xQKQdQAj4AxEIRS")
                .server("https://parseapi.back4app.com/%22")
                .build()
        );
    }
}
