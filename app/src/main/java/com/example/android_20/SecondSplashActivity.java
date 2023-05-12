package com.example.android_20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SecondSplashActivity extends AppCompatActivity {
    Button btSecondSplashC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_splash);

        btSecondSplashC = findViewById(R.id.btSecondSplash);
        btSecondSplashC.setOnClickListener(view -> {
            Intent i = new Intent(SecondSplashActivity.this, ChoiceActivity.class);
            startActivity(i);
        });
    }
}