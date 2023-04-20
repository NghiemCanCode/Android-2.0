package com.example.android_20.Lesson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android_20.ArcheryDB;
import com.example.android_20.R;
import com.example.android_20.Utils;
import com.example.android_20.model.Lesson;

import java.util.ArrayList;

public class LessonExamActivity extends AppCompatActivity {
    private int Class = 1;
    private String Subject = "";
    private int IDSubject;
    CardView cardNV, cardLS, cardDL;
    ArcheryDB db;
    ArrayList<Lesson> lstLesson;
    Toolbar tb;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_exam);

        sharedPreferences = getSharedPreferences(Utils.filename, MODE_PRIVATE);
        Class = sharedPreferences.getInt("Class",-1);

        cardNV = findViewById(R.id.cardNV);
        cardLS = findViewById(R.id.cardLS);
        cardDL = findViewById(R.id.cardDL);
        db = new ArcheryDB(this);
        db.copyDatabase();
        tb = findViewById(R.id.tbLessonExam);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cardNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IDSubject = 1;
                Subject="Ngữ Văn";
                Intent intent= new Intent(LessonExamActivity.this, ExamQuizAtivity.class);
                intent.putExtra("Class",Class);
                intent.putExtra("Subject", Subject);
                intent.putExtra("IDSubject", IDSubject);
                startActivity(intent);
            }
        });
        cardLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IDSubject=2;
                Subject="Lịch Sử";
                Intent intent= new Intent(LessonExamActivity.this, ExamQuizAtivity.class);
                intent.putExtra("Class",Class);
                intent.putExtra("Subject", Subject);
                intent.putExtra("IDSubject", IDSubject);
                startActivity(intent);
            }
        });
        cardDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IDSubject = 3;
                Subject="Địa lí";
                Intent intent= new Intent(LessonExamActivity.this, ExamQuizAtivity.class);
                intent.putExtra("Class",Class);
                intent.putExtra("Subject", Subject);
                intent.putExtra("IDSubject", IDSubject);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}