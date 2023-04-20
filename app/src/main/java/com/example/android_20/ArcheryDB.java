package com.example.android_20;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android_20.model.Answer;
import com.example.android_20.model.Lesson;
import com.example.android_20.model.Notes;
import com.example.android_20.model.Question;
import com.example.android_20.model.Quizz;
import com.example.android_20.model.QuizzList;
import com.example.android_20.model.Subject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
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
                OutputStream os = Files.newOutputStream(dbFile.toPath());
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
        cursor.close();
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
                + lesson.getIDLesson(), null);

        db.close();
    }
    public void updateLessonCheck(Lesson lesson, int Check){
        db = openDB();
        ContentValues values = new ContentValues();

        values.put("IDClass", lesson.getIDClass());
        values.put("IDSubject", lesson.getIDSubject());
        values.put("Name", lesson.getName());
        values.put("Unit", lesson.getUnit());
        values.put("Content",lesson.getContent());
        values.put("Marked", lesson.getMarked());
        values.put("Viewed", Check);
        db.update("tblLesson", values, "IDLesson="
                + lesson.getIDLesson(), null);
        db.close();
    }

    public QuizzList quizzList(int Class, int Subject, int Lesson){
        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<ArrayList<Answer>> answers = new ArrayList<>();

        db = openDB();

        Cursor cursorQuestion = db.rawQuery("SELECT * FROM tblQuestion WHERE IDSubject == "
                + Subject +" AND IDClass == " + Class + " AND IDLesson == " + Lesson, null);

        while (cursorQuestion.moveToNext()){
            int idQuestion = cursorQuestion.getInt(0);
            int idSubject = cursorQuestion.getInt(1);
            int idClass = cursorQuestion.getInt(2);
            int idLesson = cursorQuestion.getInt(3);
            int viewed = cursorQuestion.getInt(4);
            String content = cursorQuestion.getString(5);
            int wrong = cursorQuestion.getInt(6);

            questions.add(new Question(idQuestion, idSubject, idClass, idLesson,
                    viewed, content, wrong));
        }
        cursorQuestion.close();

        for (Question qsId: questions
             ) {
            answers.add(getAnswerF(qsId.getIDQuestion()));
        }

        return new QuizzList(questions, answers);
    }

    public ArrayList<Answer> getAnswerF (int Question){
        ArrayList<Answer> tmp = new ArrayList<>();

        db = openDB();

        String sql = "SELECT * FROM tblAnswer WHERE IDQuestion == " + Question;

        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()){
            int idAnswer = cursor.getInt(0);
            int idQuestion = cursor.getInt(1);
            String Answer = cursor.getString(2);
            int tRue = cursor.getInt(3);
            tmp.add(new Answer(idAnswer,idQuestion, Answer, tRue));
        }
        cursor.close();
        return tmp;
    }

    public ArrayList<Quizz> quizz (int Lesson ){
        ArrayList<Quizz> quizz = new ArrayList<>();
        Question tmpQ;
        ArrayList<Answer> tmpAS;
        db = openDB();
        Cursor cursorQuestion = db.rawQuery("SELECT * FROM tblQuestion WHERE IDLesson == "
                + Lesson, null);

        while (cursorQuestion.moveToNext()){
            int idQuestion = cursorQuestion.getInt(0);
            int idSubject = cursorQuestion.getInt(1);
            int idClass = cursorQuestion.getInt(2);
            int idLesson = cursorQuestion.getInt(3);
            int viewed = cursorQuestion.getInt(4);
            String content = cursorQuestion.getString(5);
            int wrong = cursorQuestion.getInt(6);

            tmpQ = new Question(idQuestion, idSubject, idClass, idLesson,
                    viewed, content, wrong);
            tmpAS = getAnswerF(idQuestion);
            quizz.add(new Quizz(tmpQ, tmpAS));
        }
        cursorQuestion.close();

        return quizz;
    }
    public ArrayList<Quizz> quizzListExam(int Class, int Subject){
        ArrayList<Quizz> quizz = new ArrayList<>();
        Question tmpQ;
        ArrayList<Answer> tmpAS;
        db = openDB();
        Cursor cursorQuestion = db.rawQuery("SELECT * FROM tblQuestion WHERE IDSubject == "
                + Subject +" AND IDClass == " + Class, null);

        while (cursorQuestion.moveToNext()){
            int idQuestion = cursorQuestion.getInt(0);
            int idSubject = cursorQuestion.getInt(1);
            int idClass = cursorQuestion.getInt(2);
            int idLesson = cursorQuestion.getInt(3);
            int viewed = cursorQuestion.getInt(4);
            String content = cursorQuestion.getString(5);
            int wrong = cursorQuestion.getInt(6);

            tmpQ = new Question(idQuestion, idSubject, idClass, idLesson,
                    viewed, content, wrong);
            tmpAS = getAnswerF(idQuestion);
            quizz.add(new Quizz(tmpQ, tmpAS));
        }
        cursorQuestion.close();

        return quizz;
    }

    public void InsertNote(int IdLesson,String Content){
        db = openDB();
        ContentValues values = new ContentValues();
        values.put("IdLesson",IdLesson);
        values.put("Content", Content);
        db.insert("tblNotes",null,values);
        db.close();
    }
    public void UpdateNote(int IdLesson, String newContent){
        db=openDB();
        ContentValues values = new ContentValues();
        values.put("Content",newContent);
        db.update("tblNotes",values,"IdLesson=" + IdLesson,null);
        db.close();
    }
    public ArrayList<Notes> getNotes(int Lesson){
        ArrayList<Notes> notes=new ArrayList<>();
        db=openDB();
        Cursor cursor=db.rawQuery("SELECT * FROM tblNotes " +
                "WHERE IdLesson == "+ Lesson,null);
        while (cursor.moveToNext()){
            int idNote=cursor.getInt(0);
            int idLesson=cursor.getInt(1);
            String content=cursor.getString(2);
            notes.add(new Notes(idNote,idLesson,content));
        }
        cursor.close();
        db.close();
        return notes;
    }
    public int getViewed(int Class, int Subject ){//nhan tong so cau tra loi
        int viewed= 0;
        db=openDB();
       String sql = "SELECT SUM(Viewed) FROM tblQuestion WHERE IDSubject == " + Subject
               +"AND IDClass= " + Class;
       Cursor cursor= db.rawQuery(sql, null);
       viewed=cursor.getInt(0);
       cursor.close();
       db.close();
       return viewed;
    }
    public int getWrong(int Class, int Subject){//nhan so cau sai
        int Wrongs =0;
        db= openDB();
        Cursor cursor=db.rawQuery("SELECT SUM(WrongCount)  FROM tblQuestion WHERE IDSubject == " + Subject
                +"AND IDClass= " + Class, null);//lay tong so cau sai tu bang question
        Wrongs= cursor.getInt(0);
        cursor.close();
        db.close();
        return Wrongs;
    }
    public ArrayList<Question> get5Wrong(int Class, int Subject){// nhan 5 cau sai nhieu nhat
        ArrayList<Question> get5wrongs = new ArrayList<>();
        db = openDB();
        Cursor cursor = db.rawQuery("SELECT TOP 5 * FROM tblQuestion WHERE IDSubject == " + Subject
                +"AND IDClass= " + Class + "ORDER BY WrongCount DES", null);

        while (cursor.moveToNext()){
            int idQuestion = cursor.getInt(0);
            int idSubject = cursor.getInt(1);
            int idClass = cursor.getInt(2);
            int idLesson = cursor.getInt(3);
            int viewed = cursor.getInt(4);
            String content = cursor.getString(5);
            int wrong = cursor.getInt(6);
            get5wrongs.add(new Question(idQuestion, idSubject, idClass, idLesson, viewed, content, wrong));
        }
        cursor.close();
        db.close();
        return get5wrongs;
    }

}

