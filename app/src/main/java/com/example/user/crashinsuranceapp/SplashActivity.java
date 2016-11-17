package com.example.user.crashinsuranceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_id)
    public void userLogin(){
        //Intent intent = new Intent(this,LoginActivity.class);


    }

    @OnClick(R.id.logout_id)
    public void userRegister() {
        Intent intent = new Intent( this,RegisterActivity.class);
        startActivity(intent);
    }
}
