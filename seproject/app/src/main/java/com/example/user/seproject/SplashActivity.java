package com.example.user.seproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }


    public void loginClicked(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        Log.d("Login","User wants to login");
        startActivity(intent);
    }

    public void registerClicked(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        Log.d("Register","User wants to register");
        startActivity(intent);
    }
}
