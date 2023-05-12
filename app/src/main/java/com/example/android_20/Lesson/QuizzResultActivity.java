package com.example.android_20.Lesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android_20.MainActivity;
import com.example.android_20.QuizzFragment.QuizzExamFragment;
import com.example.android_20.R;
import com.example.android_20.fragment.HomeFragment;
import com.example.android_20.model.Quizz;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;

public class QuizzResultActivity extends AppCompatActivity {
    Button btnAgain,btnViewResult;
    TextView tvScore;
    CircularProgressBar circularProgressBar;
    ArrayList<Quizz> resultList;
    QuizzExamFragment quizzExamFragment;
    ExamQuizAtivity examQuizAtivity;
    public Quizz currentQuizz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz_result);
        btnAgain = findViewById(R.id.btnAgain);
        btnViewResult = findViewById(R.id.btnWatchResult);
        tvScore = findViewById(R.id.tvResultScore);
        circularProgressBar = findViewById(R.id.circularProgressBar);
        int score=getIntent().getIntExtra("correct",0);
        circularProgressBar.setProgress(score);
        tvScore.setText(score + "/5");
        resultList = new ArrayList<>();
        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(QuizzResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        btnViewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = getIntent().getBundleExtra("Bundle");
                resultList = args.getSerializable("ArrayList", ArrayList.class);
                Intent intent = new Intent(QuizzResultActivity.this,ExamQuizAtivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("Result",1);
                bundle.putSerializable("ArrayList",resultList);
                intent.putExtra("Bundle",bundle);
                startActivity(intent);
            }
        });
    }
}