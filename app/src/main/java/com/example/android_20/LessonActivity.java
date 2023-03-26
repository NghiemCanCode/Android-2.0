package com.example.android_20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;

import com.example.android_20.Lesson.LessonDetailActivity;
import com.example.android_20.model.Lesson;
import java.util.ArrayList;

public class LessonActivity extends AppCompatActivity implements LessonAdapter.Listener{
    RecyclerView rvListC;
    ArrayList<Lesson> lstLesson;
    LessonAdapter lessonAdapter;
    ArcheryDB archeryDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        archeryDB = new ArcheryDB(this);
        archeryDB.copyDatabase();

        rvListC = findViewById(R.id.rvLesson);
        lstLesson = archeryDB.getLesson(1, 1);

        lessonAdapter = new LessonAdapter(lstLesson, LessonActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvListC.setAdapter(lessonAdapter);
        rvListC.setLayoutManager(linearLayoutManager);


    }

    @Override
    public void onItemClickListener(Lesson lesson) {
        Intent i = new Intent(LessonActivity.this, LessonDetailActivity.class);
        i.putExtra("Content", lesson.getContent());
        String a = "Bài " + lesson.getUnit() + ": " + lesson.getName();
        i.putExtra("Name", a);
        startActivity(i);
    }
}