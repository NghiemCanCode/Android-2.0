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

import com.example.android_20.Lesson.ExamQuizAtivity;
import com.example.android_20.Lesson.LessonDetailActivity;
import com.example.android_20.Lesson.QuizzResultActivity;
import com.example.android_20.model.Lesson;
import com.example.android_20.model.Notes;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity {
    EditText edtNoteContent;
    ArcheryDB db;
    ArrayList<Notes> notes;
    Notes note;
    Toolbar tb;
    int way;
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
        way=getIntent().getIntExtra("Way",-1);
        if(way==2){
            note=getIntent().getSerializableExtra("Note",Notes.class);
            edtNoteContent.setText(note.getContent());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_option_menu, menu);
        menu.getItem(0).setIcon(R.drawable.baseline_save_alt_24);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.itSave:
                if(way==1){
                    db.InsertNote(getIntent().getIntExtra("idLesson",-1),edtNoteContent.getText().toString());
                } else if (way==2) {
                    db.UpdateNote(note.getIdNote(),edtNoteContent.getText().toString());
                }
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}