package com.example.android_20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_20.Lesson.LessonDetailActivity;
import com.example.android_20.model.Lesson;
import com.example.android_20.model.Notes;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity {
    EditText edtNoteContent;
    ArcheryDB db;
    ArrayList<Notes> notes;

    Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        db = new ArcheryDB(this);
        db.openDB();

        tb = findViewById(R.id.tbLessonContent);
        edtNoteContent = findViewById(R.id.edtNoteContent);

        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        notes= new ArrayList<>();
        notes=db.getNotes(getIntent().getIntExtra("IdLesson",0));
        if(notes.size()!=0){
            Notes note = notes.get(0);
            edtNoteContent.setText(note.getContent());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_option_menu, menu);
        menu.getItem(0).setIcon(R.drawable.baseline_delete_24);
        menu.getItem(1).setIcon(R.drawable.baseline_save_alt_24);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.itSave:
                if(notes.size()==0){
                    db.InsertNote(getIntent().getIntExtra("IdLesson",0),edtNoteContent.getText().toString());
                }
                else {
                    db.UpdateNote(getIntent().getIntExtra("IdLesson",0),edtNoteContent.getText().toString());
                }
//                Notes notes=db.getNotes(getIntent().getIntExtra("IdLesson",0));
//                db.UpdateNote(note.getIdLesson(),edtNoteContent.getText().toString());
//                if(!note.getContent().equals("")){
//                    db.InsertNote(note.getIdLesson(),edtNoteContent.getText().toString());
//                }
//                else {
//                    db.UpdateNote(note.getIdLesson(),edtNoteContent.getText().toString());
//                }
                finish();
                return true;
            case R.id.itDelete:
                db.DeleteNote(getIntent().getIntExtra("IdLesson",0));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}