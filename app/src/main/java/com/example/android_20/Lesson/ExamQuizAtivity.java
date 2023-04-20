package com.example.android_20.Lesson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android_20.ArcheryDB;
import com.example.android_20.MainActivity;
import com.example.android_20.QuizzFragment.QuizzAdapter;
import com.example.android_20.QuizzFragment.QuizzExamFragment;
import com.example.android_20.QuizzFragment.QuizzFragment;
import com.example.android_20.R;
import com.example.android_20.fragment.HomeFragment;
import com.example.android_20.model.Quizz;

import java.util.ArrayList;
import java.util.Random;

public class ExamQuizAtivity extends AppCompatActivity {
    ArcheryDB db;
    Toolbar tb;
    Button btnNext,btnPre;
    ArrayList<Quizz> quizzes;
    ArrayList<Quizz> listQuizzExam;
    CountDownTimer countDownTimer;
    public int Result;
    int position = 0;
    public int correct = 0;
    public Quizz currentQuizz;
    public ExamQuizAtivity(){
        super(R.layout.activity_lesson_quiz);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_quiz_ativity);
        btnNext=findViewById(R.id.btnNext);
        btnPre=findViewById(R.id.btnPre);
        db=new ArcheryDB(this);

        btnPre.setEnabled(true);
        btnNext.setEnabled(true);
        listQuizzExam = new ArrayList<>();
        if(getIntent().getIntExtra("Result",0)==1){
            Result=1;
            Bundle args=getIntent().getBundleExtra("Bundle");
            listQuizzExam=args.getSerializable("ArrayList", ArrayList.class);
        }
        else {
            quizzes=db.quizzListExam(getIntent().getIntExtra("Class",0),getIntent().getIntExtra("IDSubject",0));
//            listQuizzExam.add(quizzes.get(0));
            while (listQuizzExam.size()<=4){
                int dup = 0;
                int i = new Random().nextInt(quizzes.size()-1);
                for(int j = 0; j < listQuizzExam.size();j++){
                    if(listQuizzExam.get(j)==quizzes.get(i))
                        dup++;
                }
                if(dup == 0){
                    listQuizzExam.add(quizzes.get(i));
                }
            }
        }

        currentQuizz=listQuizzExam.get(position);
        db.updateViewed(listQuizzExam.get(position).getQuestion().getIDQuestion(),listQuizzExam.get(position).getQuestion().getViewed());
        tb = findViewById(R.id.tbLessonQuizz);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.FM, new QuizzExamFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        if(position==0){
            btnPre.setEnabled(false);
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if(getIntent().getIntExtra("Result",0)==1) {
                    if (position == 5) {

                        Intent intent = new Intent(ExamQuizAtivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                if(position==5 && Result==0){
                    Intent intent= new Intent(ExamQuizAtivity.this,QuizzResultActivity.class);
                    Bundle bundle=new Bundle();
                    intent.putExtra("correct",correct);
                    bundle.putSerializable("ArrayList",listQuizzExam);
                    intent.putExtra("Bundle",bundle);
                    startActivity(intent);
                }
                else {
                    if(position>0){
                        btnPre.setEnabled(true);
                        btnPre.setBackgroundResource(R.drawable.roung_back_green);
                    }
                    if(position==4 && Result==1){
                        btnNext.setEnabled(true);
                    }
                    if(position==4){
                        if(getIntent().getIntExtra("Result",0)==1) {
                            btnNext.setText("Về trang chủ");
                        }
                        else  {
                            btnNext.setText("Nộp bài");
                        }

                    }
                    currentQuizz = listQuizzExam.get(position);
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.FM, new QuizzExamFragment(), null);
                    fragmentTransaction.commit();
                }

            }
        });
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if(position<4){
                    btnNext.setText("Next");
                }

                if(position==0){
                    btnPre.setEnabled(false);
                }
                currentQuizz=listQuizzExam.get(position);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.FM, new QuizzExamFragment(), null);
                fragmentTransaction.commit();
            }
        });
    }



    public int getPosition(){
        return position;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lesson_quizzexam_menu, menu);

        countDownTimer= new CountDownTimer(120000,1000) {
            @Override
            public void onTick(long l) {
                menu.getItem(0).setTitle(l/1000+"s");
            }

            @Override
            public void onFinish() {
                Intent intent= new Intent(ExamQuizAtivity.this,QuizzResultActivity.class);
                Bundle bundle=new Bundle();
                intent.putExtra("correct",correct);
                bundle.putSerializable("ArrayList",listQuizzExam);
                intent.putExtra("Bundle",bundle);
                startActivity(intent);
            }
        };
        countDownTimer.start();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void z(int a){
        correct+=a;
    }
}