package com.example.android_20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.android_20.model.Lesson;
import com.example.android_20.model.Subject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArcheryDB archeryDB;
    ArrayList<Lesson> lessons;

    private TextView tvTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        archeryDB = new ArcheryDB(MainActivity.this);
        archeryDB.copyDatabase();
        lessons = archeryDB.getLesson(1, 1);
        tvTest = findViewById(R.id.test);
        String a = String.valueOf(lessons.get(0));
        tvTest.setText(a);
    }
}