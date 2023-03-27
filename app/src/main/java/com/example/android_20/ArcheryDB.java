package com.example.android_20;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android_20.model.Lesson;
import com.example.android_20.model.Subject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class ArcheryDB {
    public String dbName = "ArcheryDB.db";
    public Context context;
    public SQLiteDatabase db;

    public ArcheryDB(Context context){
        this.context = context;
    }
    public SQLiteDatabase openDB(){
        return context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
    }

    public void copyDatabase(){
        File dbFile = context.getDatabasePath(dbName);
        if (!dbFile.exists()){
            try{
                InputStream is = context.getAssets().open(dbName);
                OutputStream os = new FileOutputStream(dbFile);
                byte[] buffer = new byte[1024];
                while (is.read(buffer) > 0){
                    os.write(buffer);
                }
                os.flush();
                os.close();
                is.close();
            }catch (IOException e){
                throw new RuntimeException("Error creating source data");
            }
        }
    }

    public ArrayList<Lesson> getLesson(int Class, int Subject){
        ArrayList<Lesson> tmp = new ArrayList<>();
        db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM tblLesson " +
                "WHERE IDClass == " + Class + " AND IDSubject == "+ Subject
                +" ORDER BY Unit ASC", null);

        while (cursor.moveToNext()){
            int idLesson = cursor.getInt(0);
            int idClass = cursor.getInt(1);
            int idSubject = cursor.getInt(2);
            String name = cursor.getString(3);
            int unit = cursor.getInt(4);
            String content = cursor.getString(5);
            int marked = cursor.getInt(6);
            int viewed = cursor.getInt(7);
            tmp.add(new Lesson(idLesson, idClass, idSubject, name, unit, content, marked, viewed));
        }
        db.close();
        return tmp;
    }

    public void updateLessonMarked(Lesson lesson, int Marked){
        db = openDB();
        ContentValues values = new ContentValues();
        values.put("IDClass", lesson.getIDClass());
        values.put("IDSubject", lesson.getIDSubject());
        values.put("Name", lesson.getName());
        values.put("Unit", lesson.getUnit());
        values.put("Content",lesson.getContent());
        values.put("Marked", Marked);
        values.put("Viewed", lesson.getView());

        db.update("tblLesson", values, "IDLesson="
                + String.valueOf(lesson.getIDLesson()), null);

        db.close();
    }

}
