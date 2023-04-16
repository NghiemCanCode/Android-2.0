package com.example.android_20.Lesson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_20.ArcheryDB;
import com.example.android_20.QuizzFragment.QuizzAdapter;
import com.example.android_20.QuizzFragment.QuizzFragment;
import com.example.android_20.R;
import com.example.android_20.model.Quizz;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import java.util.ArrayList;

public class LessonQuizActivity extends AppCompatActivity implements QuizzAdapter.Listener{
    ArcheryDB archeryDB;
    Toolbar tb;
    ArrayList<Quizz> quizzes;
    QuizzAdapter quizzAdapter;
    RecyclerView recyclerView;
    public Quizz currentQuizz;
    public LessonQuizActivity(){
        super(R.layout.activity_lesson_quiz);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_quiz);

        int lessonId = getIntent().getIntExtra("LessonID", 1);
        String nameLesson = getIntent().getStringExtra("LessonName");

        archeryDB = new ArcheryDB(this);

        quizzes= archeryDB.quizz(lessonId);
        currentQuizz = quizzes.get(0);
        tb = findViewById(R.id.tbLessonQuizz);

        tb.setTitle(nameLesson);

        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.FM, new QuizzFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


        recyclerView = findViewById(R.id.rvQuizz);

        quizzAdapter = new QuizzAdapter(quizzes, LessonQuizActivity.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,quizzes.size(),
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(quizzAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lesson_quizz_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onTextClick(Quizz data) {

        currentQuizz = data;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.FM, new QuizzFragment(), null);
        fragmentTransaction.commit();

        quizzAdapter.notifyDataSetChanged();
    }
}