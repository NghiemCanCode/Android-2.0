package com.example.android_20.QuizzFragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android_20.ArcheryDB;
import com.example.android_20.Lesson.ExamQuizAtivity;
import com.example.android_20.Lesson.LessonQuizActivity;
import com.example.android_20.R;
import com.example.android_20.model.Quizz;

import java.util.ArrayList;
import java.util.Random;


public class QuizzExamFragment extends Fragment {
    TextView tvQuestion,tvQuestions;
    Quizz quizz;
    Button btnOP1,btnOP2,btnOP3,btnOP4;

    ArcheryDB db;
    ExamQuizAtivity examQuizAtivity;
    public QuizzExamFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quizzexam_fragment, container, false);
        tvQuestion = view.findViewById(R.id.tvQuestion);
        btnOP1 = view.findViewById(R.id.btnOP1);
        btnOP2 = view.findViewById(R.id.btnOP2);
        btnOP3 = view.findViewById(R.id.btnOP3);
        btnOP4 = view.findViewById(R.id.btnOP4);
        tvQuestions= view.findViewById(R.id.tvQuestions);
        db=new ArcheryDB(getContext());
        db.copyDatabase();

        examQuizAtivity = (ExamQuizAtivity) getActivity();
        quizz = examQuizAtivity.currentQuizz;
        tvQuestions.setText(examQuizAtivity.getPosition() + 1 +"/5");


        if (quizz == null) {
            tvQuestion.setText("BBB");
        } else {
            tvQuestion.setText(quizz.getQuestion().getQuestionContent());
            btnOP1.setText(quizz.getAnswers().get(0).getAnswer());
            btnOP2.setText(quizz.getAnswers().get(1).getAnswer());
            btnOP3.setText(quizz.getAnswers().get(2).getAnswer());
            btnOP4.setText(quizz.getAnswers().get(3).getAnswer());
            initalData(quizz);
            if(examQuizAtivity.Result==1){
                revealAnswer();
                btnOP1.setEnabled(false);
                btnOP2.setEnabled(false);
                btnOP3.setEnabled(false);
                btnOP4.setEnabled(false);
            }
            btnOP1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quizz.setTrueOrFalse(1);
                    btnOP1.setBackgroundResource(R.drawable.round_back_gray);
                    btnOP2.setBackgroundResource(R.drawable.round_back_white_stoke);
                    btnOP3.setBackgroundResource(R.drawable.round_back_white_stoke);
                    btnOP4.setBackgroundResource(R.drawable.round_back_white_stoke);
                    examQuizAtivity.z(quizz.getAnswers().get(0).getTrue());
                    if(quizz.getAnswers().get(0).getTrue()==0){
                        db.updateWrong(quizz.getQuestion().getIDQuestion(),quizz.getQuestion().getWrongCount());
                    }
                }
            });
            btnOP2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quizz.setTrueOrFalse(2);
                    btnOP2.setBackgroundResource(R.drawable.round_back_gray);
                    btnOP1.setBackgroundResource(R.drawable.round_back_white_stoke);
                    btnOP3.setBackgroundResource(R.drawable.round_back_white_stoke);
                    btnOP4.setBackgroundResource(R.drawable.round_back_white_stoke);
                    examQuizAtivity.z(quizz.getAnswers().get(1).getTrue());
                    if(quizz.getAnswers().get(1).getTrue()==0){
                        db.updateWrong(quizz.getQuestion().getIDQuestion(),quizz.getQuestion().getWrongCount());
                    }
                }
            });
            btnOP3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quizz.setTrueOrFalse(3);
                    btnOP3.setBackgroundResource(R.drawable.round_back_gray);
                    btnOP2.setBackgroundResource(R.drawable.round_back_white_stoke);
                    btnOP1.setBackgroundResource(R.drawable.round_back_white_stoke);
                    btnOP4.setBackgroundResource(R.drawable.round_back_white_stoke);
                    examQuizAtivity.z(quizz.getAnswers().get(2).getTrue());
                    if(quizz.getAnswers().get(2).getTrue()==0){
                        db.updateWrong(quizz.getQuestion().getIDQuestion(),quizz.getQuestion().getWrongCount());
                    }
                }
            });
            btnOP4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quizz.setTrueOrFalse(4);
                    btnOP4.setBackgroundResource(R.drawable.round_back_gray);
                    btnOP2.setBackgroundResource(R.drawable.round_back_white_stoke);
                    btnOP3.setBackgroundResource(R.drawable.round_back_white_stoke);
                    btnOP1.setBackgroundResource(R.drawable.round_back_white_stoke);
                    examQuizAtivity.z(quizz.getAnswers().get(3).getTrue());
                    if(quizz.getAnswers().get(3).getTrue()==0){
                        db.updateWrong(quizz.getQuestion().getIDQuestion(),quizz.getQuestion().getWrongCount());
                    }
                }
            });

        }
        return view;
    }
    private void initalData(Quizz quizz){
        if(examQuizAtivity.Result==1){
            switch (quizz.getTrueOrFalse()){
                case 1:
                    btnOP1.setBackgroundResource(R.drawable.round_back_red);
                    return;
                case 2:
                    btnOP2.setBackgroundResource(R.drawable.round_back_red);
                    return;
                case 3:
                    btnOP3.setBackgroundResource(R.drawable.round_back_red);
                    return;
                case 4:
                    btnOP4.setBackgroundResource(R.drawable.round_back_red);
                    return;
                case 0:
                    btnOP1.setBackgroundResource(R.drawable.round_back_white_stoke);
                    btnOP2.setBackgroundResource(R.drawable.round_back_white_stoke);
                    btnOP3.setBackgroundResource(R.drawable.round_back_white_stoke);
                    btnOP4.setBackgroundResource(R.drawable.round_back_white_stoke);
                default:
                    return;
            }
        }
        else {
            switch (quizz.getTrueOrFalse()){
                case 1:
                    btnOP1.setBackgroundResource(R.drawable.round_back_gray);
                    return;
                case 2:
                    btnOP2.setBackgroundResource(R.drawable.round_back_gray);
                    return;
                case 3:
                    btnOP3.setBackgroundResource(R.drawable.round_back_gray);
                    return;
                case 4:
                    btnOP4.setBackgroundResource(R.drawable.round_back_gray);

                    return;
                case 0:
                    btnOP1.setBackgroundResource(R.drawable.round_back_white_stoke);
                    btnOP2.setBackgroundResource(R.drawable.round_back_white_stoke);
                    btnOP3.setBackgroundResource(R.drawable.round_back_white_stoke);
                    btnOP4.setBackgroundResource(R.drawable.round_back_white_stoke);
                default:
                    return;
            }
        }

    }
    private void revealAnswer(){
        if(quizz.getAnswers().get(0).getTrue()==1){
            btnOP1.setBackgroundResource(R.drawable.roung_back_green);
        } else if (quizz.getAnswers().get(1).getTrue()==1) {
            btnOP2.setBackgroundResource(R.drawable.roung_back_green);
        } else if (quizz.getAnswers().get(2).getTrue()==1) {
            btnOP3.setBackgroundResource(R.drawable.roung_back_green);
        } else if (quizz.getAnswers().get(3).getTrue()==1) {
            btnOP4.setBackgroundResource(R.drawable.roung_back_green);
        }

    }
}