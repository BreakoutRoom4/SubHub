package com.example.subhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG="LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignup;

    private Button tempBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
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
                goMainActivity();
            }
        });
    }

    private void signUpUser(String username, String password)
    {
        //to be implemented
    }

    private void loginUser(String username, String password)
    {
        //to be implemented
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}