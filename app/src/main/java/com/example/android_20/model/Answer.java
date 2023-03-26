package com.example.android_20.model;

import java.io.Serializable;

public class Answer implements Serializable {
    private int IDAnswer;
    private int IDQuestion;
    private String Answer;
    private int True;

    public Answer(int IDAnswer, int IDQuestion, String answer, int aTrue) {
        this.IDAnswer = IDAnswer;
        this.IDQuestion = IDQuestion;
        Answer = answer;
        True = aTrue;
    }

    public int getIDAnswer() {
        return IDAnswer;
    }

    public void setIDAnswer(int IDAnswer) {
        this.IDAnswer = IDAnswer;
    }

    public int getIDQuestion() {
        return IDQuestion;
    }

    public void setIDQuestion(int IDQuestion) {
        this.IDQuestion = IDQuestion;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public int getTrue() {
        return True;
    }

    public void setTrue(int aTrue) {
        True = aTrue;
    }
}
