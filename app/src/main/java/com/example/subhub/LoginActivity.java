package com.example.subhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG="LoginActivity";
    public static final int RC_SIGN_IN=420;
    //public static final int GOOG_SIGN_IN=421;
    public static final int GOOG_SIGN_UP=422;
    public static final int PARSE_SIGN_IN=423;

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignup;
    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton googleSignIn;
    private Button tempBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.googleWebId))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        googleSignIn = findViewById(R.id.googleSignIn);
        googleSignIn.setSize(SignInButton.SIZE_STANDARD);
        googleSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.googleSignIn:
                        signIn();
                        break;
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });
        btnSignup = findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if(username.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Username missing for signup", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Password missing for signup", Toast.LENGTH_SHORT).show();
                    return;
                }
                signUpUser(username, password);
            }
        });
        tempBtn = findViewById(R.id.tempSignin);
        tempBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                goMainActivity(0);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null)
        {
            signIn();
        }
    }

    private void goMainActivity(int resultCode)
    {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        switch(resultCode){
            case GOOG_SIGN_UP:  //first time sign up w/ google
                i.putExtra("signInCode", GOOG_SIGN_UP);
                Toast.makeText(LoginActivity.this, "sign up worked!??", Toast.LENGTH_SHORT).show();
                break;
            //case GOOG_SIGN_IN:
                //i.putExtra("signInCode", GOOG_SIGN_IN);
                //break;
            case PARSE_SIGN_IN:
                i.putExtra("signInCode", PARSE_SIGN_IN);
                break;
        }
        startActivity(i);
    }


    private void signIn()
    {
        //google sign in intent
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        //start activty with intent and sign in code
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String idToken = account.getIdToken();
            //check parse server for token
            //Toast.makeText(LoginActivity.this, "yo tryna sign up rn", Toast.LENGTH_SHORT).show();
            ParseQuery<ParseUser> query = ParseUser.getQuery();
            query.whereEqualTo("idToken", idToken);
            //Toast.makeText(LoginActivity.this, "yo tryna sign up rn", Toast.LENGTH_SHORT).show();
            query.findInBackground(new FindCallback<ParseUser>() {
                public void done(List<ParseUser> objects, ParseException e) {
                if(e == null)
                {
                    Toast.makeText(LoginActivity.this, "yo tryna login up rn", Toast.LENGTH_SHORT).show();
                    // The query was successful, returns the existing user
                    // There should only be 1 user with this unique idToken
                    if(objects.size() == 1)
                    {
                        ParseUser user = objects.get(0);
                        Log.d("User", (user.getUsername()));
                        loginUser(user.getUsername(), idToken);
                    }
                    else {
                        //parse user with this id token does not exist, create one
                        goMainActivity(GOOG_SIGN_UP);
                    }
                } else{
                    //error
                    //Toast.makeText(LoginActivity.this, "yo google sign in error", Toast.LENGTH_SHORT).show();
                }}
            });
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    //parse signup
    private void signUpUser(String username, String password)
    {
        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        //user.setEmail("email@example.com");
        // Set custom properties
        //user.put("phone", "650-253-0000");
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Toast.makeText(LoginActivity.this, "Signup successful, please re-enter to login", Toast.LENGTH_SHORT).show();
                    etUsername.setText("");
                    etPassword.setText("");
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Toast.makeText(LoginActivity.this, "Username taken, use another", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error signing up " + e);
                }
            }
        });
    }

    //parse signin
    private void loginUser(String username, String password)
    {
        Log.i(TAG, "Attempting to login user " + username);
        //navigate to main activity once signed in
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e!= null)
                {
                    //todo: better error handling please :)
                    Log.e(TAG, "Issue with login", e);
                    return;
                }
                etUsername.setText("");
                etPassword.setText("");
                //if no error signinto main
                goMainActivity(PARSE_SIGN_IN);
            }
        });

    }
}