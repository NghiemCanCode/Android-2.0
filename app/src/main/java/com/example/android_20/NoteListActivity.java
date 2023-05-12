package com.example.android_20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.android_20.Lesson.ExamQuizAtivity;
import com.example.android_20.Lesson.LessonDetailActivity;
import com.example.android_20.Lesson.QuizzResultActivity;
import com.example.android_20.model.Lesson;
import com.example.android_20.model.Notes;


import java.io.Serializable;
import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity implements NoteAdapter.Listener {
    RecyclerView rvNote;
    NoteAdapter noteAdapter;
    ArrayList<Notes> listNotes;
    ArcheryDB db;
    Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        db = new ArcheryDB(this);
        db.openDB();
        tb=findViewById(R.id.tbNoteList);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rvNote=findViewById(R.id.rvNote);
        listNotes=db.getNotes(getIntent().getIntExtra("IdLesson",-1));
        noteAdapter= new NoteAdapter(NoteListActivity.this,listNotes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvNote.setAdapter(noteAdapter);
        rvNote.setLayoutManager(linearLayoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_list_menu, menu);
        menu.getItem(0).setIcon(R.drawable.baseline_add_24);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.itAdd:
                int idLesson=getIntent().getIntExtra("IdLesson",-1);
                Intent intent= new Intent(NoteListActivity.this,NoteActivity.class);
                intent.putExtra("idLesson",idLesson);
                intent.putExtra("Way",1);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnClickItemListener(Notes notes) {
        Intent i= new Intent(NoteListActivity.this,NoteActivity.class);
        i.putExtra("Note",notes);
        i.putExtra("Way",2);
        startActivity(i);
    }

    @Override
    public void onDelete(Notes notes) {
        db.DeleteNote(notes.getIdNote());
        resetData();
    }
    public void resetData(){
        listNotes.clear();
        listNotes.addAll(db.getNotes(getIntent().getIntExtra("IdLesson",-1)));
        noteAdapter.notifyDataSetChanged();
    }
    protected void onResume() {
        resetData();
        super.onResume();
    }
}