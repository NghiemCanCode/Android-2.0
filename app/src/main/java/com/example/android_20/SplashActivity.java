package com.example.android_20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashActivity extends AppCompatActivity {
    String filename = "config";
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferences = getSharedPreferences(filename, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sharedPreferences == null){
                    Intent intent = new Intent(SplashActivity.this, ChoiceActivity.class);
                }
            }
        },2500);
    }

   // @Override
//    protected void onResume() {
//        super.onResume();
//        if(sharedPreferences == null){
//
//        }
//    }
}