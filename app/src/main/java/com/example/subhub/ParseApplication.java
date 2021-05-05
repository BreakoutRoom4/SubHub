package com.example.subhub;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

//NOTE: Parse initialization needs an APPID and CLIENTKEY which will not be uploaded to github for obvious security reasons
//IF YOU'RE ON THE TEAM @ ME AND ILL GIVE YOU IT
//ALSO: Google sign in requires API Client id to function, I will need to figure out how to deal with sharing use of that as well.
public class ParseApplication extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getResources().getString(R.string.applicationId))
                .clientKey(getResources().getString(R.string.clientKey))
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
