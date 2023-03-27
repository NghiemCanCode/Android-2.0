package com.example.android_20.model;

public class Notes {
    private int IdNote;
    private int IdLesson;
    private String content;

    public Notes(int idNote, int idLesson, String content) {
        IdNote = idNote;
        IdLesson = idLesson;
        this.content = content;
    }

    public int getIdNote() {
        return IdNote;
    }

    public void setIdNote(int idNote) {
        IdNote = idNote;
    }

    public int getIdLesson() {
        return IdLesson;
    }

    public void setIdLesson(int idLesson) {
        IdLesson = idLesson;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
