package com.example.android_20;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferences = getSharedPreferences(Utils.filename, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        new Handler(Looper.myLooper()).postDelayed(() -> {
            Intent intent;
            int Class = sharedPreferences.getInt("Class", -1);
            if(Class != -1){
                intent = new Intent(SplashActivity.this, MainActivity.class);
                finish();
            }
            else{
                intent = new Intent(SplashActivity.this, SecondSplashActivity.class);
                finish();
            }
            startActivity(intent);
        },2000);
    }
}