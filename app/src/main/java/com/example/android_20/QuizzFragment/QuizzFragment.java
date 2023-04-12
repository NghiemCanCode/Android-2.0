package com.example.android_20.QuizzFragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android_20.Lesson.LessonQuizActivity;
import com.example.android_20.LessonActivity;
import com.example.android_20.R;
import com.example.android_20.model.Quizz;

import java.util.ArrayList;

public class QuizzFragment extends Fragment {
    private View view;
    TextView tv;
    Quizz quizz;

  //  boolean flag = false;
    boolean[] flag = {false, false, false, false};
    Button btAnswerA, btAnswerB, btAnswerC, btAnswerD;

    LessonQuizActivity lessonQuizActivity;
    public QuizzFragment(){}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.quizzfragment, container, false);
        tv = view.findViewById(R.id.tvQuizz);
        btAnswerA = view.findViewById(R.id.btAnswerA);
        btAnswerB = view.findViewById(R.id.btAnswerB);
        btAnswerC = view.findViewById(R.id.btAnswerC);
        btAnswerD = view.findViewById(R.id.btAnswerD);

        lessonQuizActivity = (LessonQuizActivity) getActivity();
        quizz = lessonQuizActivity.currentQuizz;
        if (quizz == null) {
            tv.setText("BBB");
        } else {
            tv.setText(quizz.getQuestion().getQuestionContent());
            btAnswerA.setText("A. " + quizz.getAnswers().get(0).getAnswer());
            btAnswerB.setText("B. " + quizz.getAnswers().get(1).getAnswer());
            btAnswerC.setText("C. " + quizz.getAnswers().get(2).getAnswer());
            btAnswerD.setText("D. " + quizz.getAnswers().get(3).getAnswer());

            initalData(quizz);

            ClickButton(btAnswerA, quizz.getAnswers().get(0).getTrue(), 0);
            ClickButton(btAnswerB, quizz.getAnswers().get(1).getTrue(), 1);
            ClickButton(btAnswerC, quizz.getAnswers().get(2).getTrue(), 2);
            ClickButton(btAnswerD, quizz.getAnswers().get(3).getTrue(), 3);

        }
        return view;
    }

    private void ClickButton(View view, int tf, int index){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkSum() == 0){
                    if( tf == 1){
                        view.setBackgroundColor(Color.rgb(0,255,0));
                        flag[index] = true;
                        quizz.setTrueOrFalse(+(index + 1));
                    }
                    else if(tf == 0){
                        view.setBackgroundColor(Color.rgb(255,0,0));
                        flag[index] = true;
                        quizz.setTrueOrFalse(-(index + 1));
                    }
                }
                else if(flag[index])
                {
                    btAnswerA.setBackgroundColor(Color.rgb(255,255,255));
                    btAnswerB.setBackgroundColor(Color.rgb(255,255,255));
                    btAnswerC.setBackgroundColor(Color.rgb(255,255,255));
                    btAnswerD.setBackgroundColor(Color.rgb(255,255,255));

                    flag[0] = false;
                    flag[1] = false;
                    flag[2] = false;
                    flag[3] = false;

                    quizz.setTrueOrFalse(0);
                }
                else {
                    btAnswerA.setBackgroundColor(Color.rgb(255,255,255));
                    btAnswerB.setBackgroundColor(Color.rgb(255,255,255));
                    btAnswerC.setBackgroundColor(Color.rgb(255,255,255));
                    btAnswerD.setBackgroundColor(Color.rgb(255,255,255));
                    flag[0] = false;
                    flag[1] = false;
                    flag[2] = false;
                    flag[3] = false;

                    if( tf == 1){
                        view.setBackgroundColor(Color.rgb(0,255,0));
                        flag[index] = true;
                        quizz.setTrueOrFalse(+(index + 1));
                    }
                    else if(tf == 0){
                        view.setBackgroundColor(Color.rgb(255,0,0));
                        flag[index] = true;
                        quizz.setTrueOrFalse(-(index + 1));
                    }
                }
            }
        });
    }

    private int checkSum(){
        int rs = 0;
        for (boolean fla: flag) {
            if(fla) rs++;
        }
        return rs;
    }

    private void initalData(Quizz quizz){
        switch (quizz.getTrueOrFalse()){
            case 1:
                btAnswerA.setBackgroundColor(Color.rgb(0,255,0));
                return;
            case 2:
                btAnswerB.setBackgroundColor(Color.rgb(0,255,0));
                return;
            case 3:
                btAnswerC.setBackgroundColor(Color.rgb(0,255,0));
                return;
            case 4:
                btAnswerD.setBackgroundColor(Color.rgb(0,255,0));
                return;
            case -1:
                btAnswerA.setBackgroundColor(Color.rgb(255,0,0));
                return;
            case -2:
                btAnswerB.setBackgroundColor(Color.rgb(255,0,0));
                return;
            case -3:
                btAnswerC.setBackgroundColor(Color.rgb(255,0,0));
                return;
            case -4:
                btAnswerD.setBackgroundColor(Color.rgb(255,0,0));
                return;
            case 0:
                btAnswerA.setBackgroundColor(Color.rgb(255,255,255));
                btAnswerB.setBackgroundColor(Color.rgb(255,255,255));
                btAnswerC.setBackgroundColor(Color.rgb(255,255,255));
                btAnswerD.setBackgroundColor(Color.rgb(255,255,255));
            default:
                return;
        }
    }
}
