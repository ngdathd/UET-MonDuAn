package com.nghiatv.musicapp.online.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.nghiatv.musicapp.R;
import com.nghiatv.musicapp.activities.MainActivity;


public class SplashScreenActivity extends AppCompatActivity {
    private static final String TAG = "SplashScreenActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                SplashScreenActivity.this.finish();
            }
        },2000);
    }


}
