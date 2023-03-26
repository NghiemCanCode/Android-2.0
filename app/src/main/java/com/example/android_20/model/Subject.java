package com.example.android_20.model;

import java.io.Serializable;

public class Subject implements Serializable {
    private int IDSubject;
    private String SubjectName;

    public Subject(int IDSubject, String subjectName) {
        this.IDSubject = IDSubject;
        SubjectName = subjectName;
    }

    public int getIDSubject() {
        return IDSubject;
    }

    public void setIDSubject(int IDSubject) {
        this.IDSubject = IDSubject;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }
}
