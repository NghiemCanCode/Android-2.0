package com.example.android_20.Lesson;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.example.android_20.ArcheryDB;

import com.example.android_20.NoteActivity;
import com.example.android_20.NoteListActivity;
import com.example.android_20.R;
import com.example.android_20.model.Lesson;

import java.util.Objects;

public class LessonDetailActivity extends AppCompatActivity {
    TextView tvDetailC, tvDetailNameC;
    ArcheryDB db;
    Toolbar tb;
    Button btQuestionC;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_detail);
        tb = findViewById(R.id.tbLessonContent);
        tvDetailC = findViewById(R.id.tvLessonDetail);
        tvDetailNameC = findViewById(R.id.tvLessonNameDetail);

        btQuestionC = findViewById(R.id.btQuestion);
        db = new ArcheryDB(this);
        Lesson ls = getIntent().getSerializableExtra("Marked", Lesson.class);
        setSupportActionBar(tb);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        btQuestionC.setOnClickListener(view -> {
            Intent intent = new Intent(LessonDetailActivity.this, LessonQuizActivity.class);
            intent.putExtra("LessonID", ls.getIDLesson());
            startActivity(intent);
        });

        String a = ls.getContent();
        String b = ls.getName();
        tvDetailC.setText(a);
        tvDetailNameC.setText(b);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lesson_option_menu, menu);

        menu.getItem(1).setIcon(R.drawable.baseline_star_border_24);
        menu.getItem(0).setIcon(R.drawable.baseline_notes_24);
        Lesson ls = getIntent().getSerializableExtra("Marked", Lesson.class);

        flag = ls.getMarked();
        if (flag == 1){
            menu.getItem(1).setIcon(R.drawable.baseline_star_24);
        }
        else if (flag == 0){
            menu.getItem(1).setIcon(R.drawable.baseline_star_border_24);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Lesson ls = getIntent().getSerializableExtra("Marked", Lesson.class);
                db.updateLessonMarked(ls, flag);
                ls.setMarked(flag);
                db.updateLessonCheck(ls);
                finish();
                return true;
            case R.id.itNote:
                Lesson lss = getIntent().getSerializableExtra("Marked", Lesson.class);
                Intent intent= new Intent(LessonDetailActivity.this, NoteListActivity.class);
                intent.putExtra("IdLesson",lss.getIDLesson());
                startActivity(intent);
                return true;
            case R.id.itLsMnMarked:
                int mark = Math.abs(flag -  1);
                if (mark == 1){
                    item.setIcon(R.drawable.baseline_star_24);
                    Toast.makeText(LessonDetailActivity.this, "Đã lưu bài học",
                            Toast.LENGTH_SHORT).show();
                    flag = 1;
                }
                else if (mark == 0){
                    item.setIcon(R.drawable.baseline_star_border_24);
                    Toast.makeText(LessonDetailActivity.this, "Đã xóa lưu bài học",
                            Toast.LENGTH_SHORT).show();
                    flag = 0;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}