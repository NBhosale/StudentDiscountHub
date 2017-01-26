package com.foodies.nero.studentbenefitshub;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashScreen.this, LoginActivityPage.class));
                finish();
            }
        }, secondsDelayed * 3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        finish();
    }
}
