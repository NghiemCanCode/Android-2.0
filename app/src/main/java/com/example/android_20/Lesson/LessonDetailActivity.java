package com.example.android_20.Lesson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_20.R;

public class LessonDetailActivity extends AppCompatActivity {
    TextView tvDetailC, tvDetailNameC;
    ImageView ivBackC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_detail);

        tvDetailC = findViewById(R.id.tvLessonDetail);
        ivBackC = findViewById(R.id.ivLessonBackArrow);
        tvDetailNameC = findViewById(R.id.tvLessonNameDetail);

        String a = getIntent().getStringExtra("Content");
        String b = getIntent().getStringExtra("Name");
        tvDetailC.setText(a);
        tvDetailNameC.setText(b);

        ivBackC.setOnClickListener(view -> finish());

    }
}