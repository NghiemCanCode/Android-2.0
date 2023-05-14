package com.example.android_20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.telecom.Call;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android_20.Lesson.LessonDetailActivity;

import com.example.android_20.model.Lesson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LessonActivity extends AppCompatActivity implements LessonAdapter.Listener {
    RecyclerView rvListC;
    ArrayList<Lesson> lstLesson;
    LessonAdapter lessonAdapter;
    ArcheryDB archeryDB;
    Toolbar tb;
    int Grade, Subject;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        tb = findViewById(R.id.tbLessonMain);

        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tb.setTitle("Đây là môn học");

        archeryDB = new ArcheryDB(this);

        sharedPreferences = getSharedPreferences(Utils.filename, Context.MODE_PRIVATE);

        Grade = sharedPreferences.getInt("Class", -1);

        Subject = getIntent().getIntExtra("Subject", -1);

        tb = findViewById(R.id.tbLessonMenu);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvListC = findViewById(R.id.rvLesson);
        lstLesson = archeryDB.getLesson(Grade, Subject);

        lessonAdapter = new LessonAdapter(lstLesson, LessonActivity.this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvListC.setAdapter(lessonAdapter);
        rvListC.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void onItemClickListener(Lesson lesson) {
        Intent i = new Intent(LessonActivity.this, LessonDetailActivity.class);
        i.putExtra("Marked", lesson);
        startActivity(i);
    }

    @Override
    public void onMarked(Lesson lesson) {
        int mark = Math.abs(lesson.getMarked() - 1);
        archeryDB = new ArcheryDB(this);
        archeryDB.updateLessonMarked(lesson, mark);
        if (mark == 1) Toast.makeText(LessonActivity.this, "Đã lưu bài học",
                Toast.LENGTH_SHORT).show();
        else Toast.makeText(LessonActivity.this, "Đã xóa lưu bài học",
                Toast.LENGTH_SHORT).show();
        resetData();
    }

    public void resetData() {
        lstLesson.clear();
        lstLesson.addAll(archeryDB.getLesson(Grade, Subject));
        lessonAdapter.notifyDataSetChanged();
    }

//    @Override
//    protected void onPause() {
//        resetData();
//        super.onPause();
//    }

    @Override
    protected void onResume() {
        resetData();
        super.onResume();
    }

//    @Override
//    protected void onStop() {
//        resetData();
//        super.onStop();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.search_view, menu);
      SearchView searchView = (SearchView) menu.findItem(R.id.mnSearch).getActionView();
      searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
          @Override
          public boolean onQueryTextSubmit(String query) {
              Toast.makeText(LessonActivity.this, query, Toast.LENGTH_SHORT).show();
              return false;
          }

          @Override
          public boolean onQueryTextChange(String newText) {
              lessonAdapter.getFilter().filter(newText);
              //Log.d("AAAA", newText);
              return false;
          }
      });
            return super.onCreateOptionsMenu(menu);
    }

//sort
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case android.R.id.home:
                    finish();
                    return true;
                case R.id.mnSortID:
                    Collections.sort(lstLesson, new Comparator<Lesson>() {
                        @Override
                        public int compare(Lesson lesson1, Lesson lesson2) {
                            if(lesson1.getUnit() > lesson2.getUnit()){
                                return 1;
                            }else {
                                if(lesson1.getIDLesson()==lesson2.getIDLesson()){
                                    return 0;
                                }else return -1;
                            }
                        }
                    });
                    lessonAdapter.notifyDataSetChanged();
                    Toast.makeText(LessonActivity.this, "Sắp xếp thành công", Toast.LENGTH_SHORT).show();
                    break;
                case  R.id.mnSortName:
                    Collections.sort(lstLesson, new Comparator<Lesson>() {
                        @Override
                        public int compare(Lesson lesson1, Lesson lesson2) {
                            return lesson1.getName().compareToIgnoreCase(lesson2.getName());//compare sap xep theo chu cai alphabet
                        }
                    });
                    lessonAdapter.notifyDataSetChanged();
                    Toast.makeText(LessonActivity.this, "Sắp xếp thành công", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.mnSortMarked:
                    Collections.sort(lstLesson, new Comparator<Lesson>() {
                        @Override
                        public int compare(Lesson lesson1, Lesson lesson2) {
                                if(lesson1.getMarked() == 1)
                                    return -1;
                                else return 1;
                        }
                    });
                    lessonAdapter.notifyDataSetChanged();
                    Toast.makeText(LessonActivity.this, "Sắp xếp thành công", Toast.LENGTH_SHORT).show();
                    break;
            }
        return super.onOptionsItemSelected(item);
    }
}

