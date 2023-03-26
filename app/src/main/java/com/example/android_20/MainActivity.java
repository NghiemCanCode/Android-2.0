package com.example.android_20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import com.example.android_20.model.Subject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArcheryDB archeryDB;
    ArrayList<Subject> subjects;

    private TextView tvTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        archeryDB = new ArcheryDB(MainActivity.this);
        archeryDB.copyDatabase();
        subjects = archeryDB.getSubjects();
        tvTest = findViewById(R.id.test);
        String a = String.valueOf(subjects.get(0));
        tvTest.setText(a);
    }
}