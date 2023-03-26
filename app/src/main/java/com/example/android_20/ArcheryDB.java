package com.example.android_20;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    public ArrayList<Subject> getSubjects(){
        ArrayList<Subject> tmp = new ArrayList<>();
        db = openDB();
        Cursor cursor = db.rawQuery("SELECT * FROM tblSubject", null);

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            tmp.add(new Subject(id, name));
        }

        db.close();
        return tmp;
    }

    public void insertSubject(String name){
        db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("SubjectName", name);
        db.insert("tblSubject", null, cv);
        db.close();
    }
}
