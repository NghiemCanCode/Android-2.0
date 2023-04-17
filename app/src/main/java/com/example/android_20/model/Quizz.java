package com.example.android_20.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Quizz implements Serializable {
    private Question question;
    private ArrayList<Answer> answers;
    private int TrueOrFalse = 0;
    private int Chosen = 0;

    public Quizz(){}

    public int getTrueOrFalse() {
        return TrueOrFalse;
    }

    public void setTrueOrFalse(int trueOrFalse) {
        TrueOrFalse = trueOrFalse;
    }

    public Quizz(Question question, ArrayList<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
    public int getChosen() {
        return Chosen;
    }
    public void setChosen(int chosen) {
        Chosen = chosen;
    }
}
