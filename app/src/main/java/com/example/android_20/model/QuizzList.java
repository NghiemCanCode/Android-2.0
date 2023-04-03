package com.example.android_20.model;

import java.io.Serializable;
import java.util.ArrayList;

public class QuizzList implements Serializable {
    ArrayList<Question> questionSet;
    ArrayList<ArrayList<Answer>> answerSet;

    public QuizzList(ArrayList<Question> questionSet, ArrayList<ArrayList<Answer>> answerSet) {
        this.questionSet = questionSet;
        this.answerSet = answerSet;
    }

    public ArrayList<Question> getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(ArrayList<Question> questionSet) {
        this.questionSet = questionSet;
    }

    public ArrayList<ArrayList<Answer>> getAnswerSet() {
        return answerSet;
    }

    public void setAnswerSet(ArrayList<ArrayList<Answer>> answerSet) {
        this.answerSet = answerSet;
    }
}
