package com.example.user.seproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void crashButtonPressed(View view){
        Intent intent = new Intent(this,SendReportActivity.class);
        startActivity(intent);
    }

    public void profileButtonPressed(View view){
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
}
