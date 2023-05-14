package com.example.android_20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class ChoiceActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    ImageView ivChoice1C, ivChoice2C, ivChoice3C;
    Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        sharedPreferences = getSharedPreferences(Utils.filename, MODE_PRIVATE);
        ivChoice1C = findViewById(R.id.tv1Choice);
        ivChoice2C = findViewById(R.id.tv2Choice);
        ivChoice3C = findViewById(R.id.tv3Choice);
        tb = findViewById(R.id.tbChoice);

        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ivChoice1C.setOnClickListener(listener(1));
        ivChoice2C.setOnClickListener(listener(2));
        ivChoice3C.setOnClickListener(listener(3));

    }

    @NonNull
    private View.OnClickListener listener(int Class) {
        return view -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("Class", Class);
            editor.apply();
            Intent intent = new Intent(ChoiceActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.choice_munu, menu);

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