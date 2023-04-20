package com.example.android_20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;

import android.widget.Switch;

public class SettingActivity extends AppCompatActivity {

    Switch switch1;
    boolean nightMODE;
    SharedPreferences.Editor editor;
    ImageView ivBack;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ivBack = findViewById(R.id.ivBack);
        switch1 = findViewById(R.id.switch1);
        //we use sharePreferences to save mode if exit and go back again
        sharedPreferences = getSharedPreferences(Utils.filename, Context.MODE_PRIVATE);
        nightMODE = sharedPreferences.getBoolean("night", false);//light mode is the Default mode

        if (nightMODE) {
            switch1.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nightMODE) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", false);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", true);
                }
                editor.apply();
            }
        });
        //set event cho nut back
        ivBack.setOnClickListener(view -> finish());
    }
}