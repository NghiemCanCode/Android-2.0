package com.example.android_20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    SwitchCompat switch1;
    boolean nightMODE;
    SharedPreferences.Editor editor;
    public static final String KEY_FONT_SIZE = "FontSize";
    public static final String SHARED_PREF_NAME = "MyPref";
    Toolbar tb;
    SharedPreferences sharedPreferences;
    SeekBar seekBar;
    TextView tvFontSize,tvFS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        switch1 = findViewById(R.id.switch1);

        tb = findViewById(R.id.tbSetting);
        seekBar = findViewById(R.id.sbFontSize);
        tvFontSize = findViewById(R.id.tvFontSize);
        tvFS = findViewById(R.id.tvFS);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
 //       sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
//        changeFontSize();
        //we use sharePreferences to save mode if exit and go back again
        sharedPreferences = getSharedPreferences(Utils.filename, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
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
    }

//    private void changeFontSize() {
//        int intFontSize=sharedPreferences.getInt(KEY_FONT_SIZE,0);
//        float a=tvFS.getTextSize();
//        float b=switch1.getTextSize();
//        if(intFontSize!=0){
//            tvFontSize.setText(String.valueOf(intFontSize));
//            tvFS.setTextSize(a*intFontSize/100);
//            switch1.setTextSize(b*intFontSize/100);
//            seekBar.setProgress(seekBar.getProgress()*intFontSize/100);
//        }
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                tvFontSize.setText(String.valueOf(progress));
//                tvFS.setTextSize(tvFS.getTextSize()*progress/100);
//                switch1.setTextSize(switch1.getTextSize()*progress/100);
//                editor.putInt(KEY_FONT_SIZE,progress);
//                editor.apply();
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setting_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}