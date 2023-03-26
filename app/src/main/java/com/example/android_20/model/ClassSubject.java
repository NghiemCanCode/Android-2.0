package com.example.android_20.model;

import java.io.Serializable;

public class ClassSubject implements Serializable {
    private int IDClass;
    private String ClassSubject;
    private int Check;

    public ClassSubject(int IDClass, String classSubject, int check) {
        this.IDClass = IDClass;
        ClassSubject = classSubject;
        Check = check;
    }

    public int getIDClass() {
        return IDClass;
    }

    public void setIDClass(int IDClass) {
        this.IDClass = IDClass;
    }

    public String getClassSubject() {
        return ClassSubject;
    }

    public void setClassSubject(String classSubject) {
        ClassSubject = classSubject;
    }

    public int getCheck() {
        return Check;
    }

    public void setCheck(int check) {
        Check = check;
    }
}
