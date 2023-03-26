package com.example.android_20.model;

import java.io.Serializable;

public class Question implements Serializable {
    private int IDQuestion;
    private int IDSubject;
    private int IDClass;
    private int IDLesson;
    private int Viewed;
    private String QuestionContent;
    private int WrongCount;

    public Question(int IDQuestion, int IDSubject, int IDClass, int IDLesson, int viewed, String questionContent, int wrongCount) {
        this.IDQuestion = IDQuestion;
        this.IDSubject = IDSubject;
        this.IDClass = IDClass;
        this.IDLesson = IDLesson;
        Viewed = viewed;
        QuestionContent = questionContent;
        WrongCount = wrongCount;
    }

    public int getIDQuestion() {
        return IDQuestion;
    }

    public void setIDQuestion(int IDQuestion) {
        this.IDQuestion = IDQuestion;
    }

    public int getIDSubject() {
        return IDSubject;
    }

    public void setIDSubject(int IDSubject) {
        this.IDSubject = IDSubject;
    }

    public int getIDClass() {
        return IDClass;
    }

    public void setIDClass(int IDClass) {
        this.IDClass = IDClass;
    }

    public int getIDLesson() {
        return IDLesson;
    }

    public void setIDLesson(int IDLesson) {
        this.IDLesson = IDLesson;
    }

    public int getViewed() {
        return Viewed;
    }

    public void setViewed(int viewed) {
        Viewed = viewed;
    }

    public String getQuestionContent() {
        return QuestionContent;
    }

    public void setQuestionContent(String questionContent) {
        QuestionContent = questionContent;
    }

    public int getWrongCount() {
        return WrongCount;
    }

    public void setWrongCount(int wrongCount) {
        WrongCount = wrongCount;
    }
}
