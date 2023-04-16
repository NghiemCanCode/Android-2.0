package com.example.android_20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android_20.Lesson.LessonDetailActivity;
import com.example.android_20.model.Lesson;
import java.util.ArrayList;

public class LessonActivity extends AppCompatActivity implements LessonAdapter.Listener{
    RecyclerView rvListC;
    ArrayList<Lesson> lstLesson;
    LessonAdapter lessonAdapter;
    ArcheryDB archeryDB;
    Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        tb = findViewById(R.id.tbLessonMain);

        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tb.setTitle("Đây là môn học");

        archeryDB = new ArcheryDB(this);
        archeryDB.copyDatabase();

        rvListC = findViewById(R.id.rvLesson);
        lstLesson = archeryDB.getLesson(Utils.Grade, Utils.Subject);

        lessonAdapter = new LessonAdapter(lstLesson, LessonActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvListC.setAdapter(lessonAdapter);
        rvListC.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onItemClickListener(Lesson lesson) {
        Intent i = new Intent(LessonActivity.this, LessonDetailActivity.class);
        archeryDB = new ArcheryDB(this);
        archeryDB.updateLessonCheck(lesson, 1);
        i.putExtra("Marked", lesson);
        startActivity(i);
        resetData();
    }

    @Override
    public void onMarked(Lesson lesson) {
        int mark = Math.abs(lesson.getMarked() -  1);
        archeryDB = new ArcheryDB(this);
        archeryDB.updateLessonMarked(lesson, mark);
        if(mark == 1) Toast.makeText(LessonActivity.this, "Đã lưu bài học",
                Toast.LENGTH_SHORT).show();
        else Toast.makeText(LessonActivity.this, "Đã xóa lưu bài học",
                Toast.LENGTH_SHORT).show();
        resetData();
    }

    public void resetData(){
        lstLesson.clear();
        lstLesson.addAll(archeryDB.getLesson(Utils.Grade, Utils.Subject));
        lessonAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        resetData();
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_lesson_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}