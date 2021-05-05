package com.example.subhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.subhub.fragments.DiscoverFragment;
import com.example.subhub.fragments.HomeFragment;
import com.example.subhub.fragments.ProfileFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import static com.example.subhub.LoginActivity.GOOG_SIGN_UP;
import static com.example.subhub.LoginActivity.PARSE_SIGN_IN;
import static com.example.subhub.LoginActivity.RC_SIGN_IN;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onCreateUserSetup();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        // do something here
                        fragment = new HomeFragment();
                        break;
                    case R.id.action_discover:
                        // do something here
                        fragment = new DiscoverFragment();
                        break;
                    case R.id.action_profile:
                    default:
                        fragment = new ProfileFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }

    private void parseSignUp(String idToken, String email)
    {
        ParseUser user = ParseObject.create(ParseUser.class);
        user.setEmail(email);
        user.put("idToken", idToken);
        user.setUsername(email);
        user.setPassword(idToken);
        //TODO: add more info to be initialized in the future when need be

        user.signUpInBackground(e->{
           if(e == null) {
               // Sign up successful, google account now has a Parse user assigned to it
           } else {
               // Sign up didn't succeed. Look at the ParseException
               // to figure out what went wrong
               Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
           }
        });
    }

    private void initializeUI()
    {
            //TODO: figure out what data, structures (recyclerview, displays), etc we need to update ui and update


    }

    private void onCreateUserSetup()
    {
        Intent i = getIntent();
        int resultCode = i.getIntExtra("signInCode", -1);
        switch(resultCode)
        {
            case -1:
                break;
            case PARSE_SIGN_IN:
                //already signed in through parse
                //initialize ui/data for that user
                initializeUI();
                break;
            case GOOG_SIGN_UP:
                //sign up
                GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(this);
                //create a basic parse user based on google acc info
                parseSignUp(acc.getIdToken(), acc.getEmail());
                //initialize ui/data for that users
                initializeUI();
                break;
        }
    }
}