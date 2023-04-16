package com.example.android_20.Lesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android_20.ArcheryDB;
import com.example.android_20.MainActivity;
import com.example.android_20.R;
import com.example.android_20.Utils;
import com.example.android_20.model.Lesson;

import java.util.ArrayList;

public class LessonExamActivity extends AppCompatActivity {

    private int Class=1;
    private String Subject="";
    private int IDSubject;
    CardView cardNV, cardLS, cardDL;
    ArcheryDB db;
    ArrayList<Lesson> lstLesson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_exam);

        cardNV=findViewById(R.id.cardNV);
        cardLS=findViewById(R.id.cardLS);
        cardDL=findViewById(R.id.cardDL);
        db = new ArcheryDB(this);
        db.copyDatabase();


        cardNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IDSubject=1;
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
                IDSubject=3;
                Subject="Địa lí";
                Intent intent= new Intent(LessonExamActivity.this, ExamQuizAtivity.class);
                intent.putExtra("Class",Class);
                intent.putExtra("Subject", Subject);
                intent.putExtra("IDSubject", IDSubject);
                startActivity(intent);
            }
        });
    }
}