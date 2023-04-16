package com.example.android_20.Lesson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_20.ArcheryDB;
import com.example.android_20.LessonActivity;
import com.example.android_20.R;
import com.example.android_20.model.Lesson;

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

        Lesson ls = getIntent().getSerializableExtra("Marked", Lesson.class);

        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tb.setTitle("Bài "+ ls.getUnit());

        String a = ls.getContent();
        String b = ls.getName();
        tvDetailC.setText(a);
        tvDetailNameC.setText(b);
        btQuestionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LessonDetailActivity.this, LessonQuizActivity.class);
                i.putExtra("LessonID", ls.getIDLesson());
                i.putExtra("LessonName", "Bài " + ls.getUnit() + ": " + ls.getName());
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lesson_option_menu, menu);
        menu.getItem(0).setIcon(R.drawable.baseline_star_border_24);

        Lesson ls = getIntent().getSerializableExtra("Marked", Lesson.class);

        flag = ls.getMarked();
        if (flag == 1){
            menu.getItem(0).setIcon(R.drawable.baseline_star_24);
        }
        else if (flag == 0){
            menu.getItem(0).setIcon(R.drawable.baseline_star_border_24);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        db = new ArcheryDB(this);
        switch (item.getItemId()){
            case android.R.id.home:
                Lesson ls = getIntent().getSerializableExtra("Marked", Lesson.class);
                db.updateLessonMarked(ls, flag);
                finish();
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