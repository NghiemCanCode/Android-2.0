package com.example.android_20.model;

public class Lesson  {
    private int IDLesson;
    private int IDClass;
    private int IDSubject;
    private String Name;
    private int Unit;
    private String Content;
    private int Marked;
    private int View;

    public Lesson(int IDLesson, int IDClass, int IDSubject, String name, int unit, String content, int marked, int view) {
        this.IDLesson = IDLesson;
        this.IDClass = IDClass;
        this.IDSubject = IDSubject;
        Name = name;
        Unit = unit;
        Content = content;
        Marked = marked;
        View = view;
    }

    public int getIDLesson() {
        return IDLesson;
    }

    public void setIDLesson(int IDLesson) {
        this.IDLesson = IDLesson;
    }

    public int getIDClass() {
        return IDClass;
    }

    public void setIDClass(int IDClass) {
        this.IDClass = IDClass;
    }

    public int getIDSubject() {
        return IDSubject;
    }

    public void setIDSubject(int IDSubject) {
        this.IDSubject = IDSubject;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getUnit() {
        return Unit;
    }

    public void setUnit(int unit) {
        Unit = unit;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getMarked() {
        return Marked;
    }

    public void setMarked(int marked) {
        Marked = marked;
    }

    public int getView() {
        return View;
    }

    public void setView(int view) {
        View = view;
    }
}
