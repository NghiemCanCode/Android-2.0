package com.example.android_20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class ChoiceActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    ImageView ivChoice1C, ivChoice2C, ivChoice3C;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        sharedPreferences = getSharedPreferences(Utils.filename, MODE_PRIVATE);
        ivChoice1C = findViewById(R.id.tv1Choice);
        ivChoice2C = findViewById(R.id.tv2Choice);
        ivChoice3C = findViewById(R.id.tv3Choice);

        ivChoice1C.setOnClickListener(listener(1));
        ivChoice2C.setOnClickListener(listener(2));
        ivChoice3C.setOnClickListener(listener(3));

    }

    @NonNull
    private View.OnClickListener listener(int Class) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("Class", Class);
                editor.apply();
                Intent intent = new Intent(ChoiceActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }
}