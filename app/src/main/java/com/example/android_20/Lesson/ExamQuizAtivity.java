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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android_20.ArcheryDB;
import com.example.android_20.QuizzFragment.QuizzAdapter;
import com.example.android_20.QuizzFragment.QuizzExamFragment;
import com.example.android_20.QuizzFragment.QuizzFragment;
import com.example.android_20.R;
import com.example.android_20.model.Quizz;

import java.util.ArrayList;
import java.util.Random;

public class ExamQuizAtivity extends AppCompatActivity {
    ArcheryDB db;
    Toolbar tb;
    Button btnNext,btnPre;
    ArrayList<Quizz> quizzes;
    ArrayList<Quizz> listQuizzExam;
    public int Result;
    int position=0;
    public int correct=0;
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
        db.copyDatabase();
        btnPre.setEnabled(true);
        btnNext.setEnabled(true);
        listQuizzExam=new ArrayList<>();
        if(getIntent().getIntExtra("Result",0)==1){
            Result=1;
            Bundle args=getIntent().getBundleExtra("Bundle");
            listQuizzExam= (ArrayList<Quizz>) args.getSerializable("ArrayList");
        }
        else {
            quizzes=db.quizzListExam(getIntent().getIntExtra("Class",0),getIntent().getIntExtra("IDSubject",0));

            while (listQuizzExam.size()<=4){
                int dup=0;
                int i = new Random().nextInt(quizzes.size()-1);
                for(int j=0;j<listQuizzExam.size();j++){
                    if(listQuizzExam.get(j)==quizzes.get(i))
                        dup++;
                }
                if(dup==0)
                    listQuizzExam.add(quizzes.get(i));
            }
        }



        currentQuizz=listQuizzExam.get(position);
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
                if(position==5){
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
                        btnNext.setEnabled(false);
                    }
                    if(position==4){
                        btnNext.setText("Submit");
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