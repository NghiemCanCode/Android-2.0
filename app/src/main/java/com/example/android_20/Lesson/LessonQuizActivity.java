package com.example.android_20.Lesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.example.android_20.ArcheryDB;
import com.example.android_20.R;
import com.example.android_20.model.Answer;
import com.example.android_20.model.Question;
import com.example.android_20.model.QuizzList;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LessonQuizActivity extends AppCompatActivity {
    Toolbar tbLessonQuizz;
    TextView tvQuizz;
    Button btAnswerA, btAnswerB, btAnswerC, btAnswerD, btBack, btNext;
    ArcheryDB archeryDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_quiz);
        archeryDB = new ArcheryDB(this);
        Mapping();

        int currentPos = 0;

        QuizzList quizzList = archeryDB.quizzList(1,1,10);
        Question currentQuestion = quizzList.getQuestionSet().get(currentPos);

        ArrayList<Answer> currentAnswer = quizzList.getAnswerSet().get(currentPos);

        tvQuizz.setText(currentQuestion.getQuestionContent());

        btAnswerA.setText("A. " + currentAnswer.get(0).getAnswer());
        btAnswerB.setText("B. " + currentAnswer.get(1).getAnswer());
        btAnswerC.setText("C. " + currentAnswer.get(2).getAnswer());
        btAnswerD.setText("D. " + currentAnswer.get(3).getAnswer());


    }
    void Mapping(){
        tbLessonQuizz = findViewById(R.id.tbLessonQuizz);
        tvQuizz = findViewById(R.id.tvQuizz);

        btAnswerA = findViewById(R.id.btAnswerA);
        btAnswerB = findViewById(R.id.btAnswerB);
        btAnswerC = findViewById(R.id.btAnswerC);
        btAnswerD = findViewById(R.id.btAnswerD);

        btBack = findViewById(R.id.btBack);
        btNext = findViewById(R.id.btNext);


    }

}