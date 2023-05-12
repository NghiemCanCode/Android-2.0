package com.example.android_20.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android_20.ArcheryDB;
import com.example.android_20.Lesson.LessonDetailActivity;
import com.example.android_20.R;
import com.example.android_20.Utils;
import com.example.android_20.model.Lesson;
import com.example.android_20.model.Question;

import java.util.ArrayList;


public class InfoFragment extends Fragment {
    TextView tvDiemNvC, tvDiemLsC, tvDiemDlC;
    ArcheryDB archeryDB;
    ArrayList<Question> questionArrayList;
    TextView tvCauSai1, tvCauSai2, tvCauSai3, tvCauSai4, tvCauSai5;
    SharedPreferences sharedPreferences;
    double WrongVan=0, WrongSu=0, WrongDia=0, TotalVan=0, TotalSu=0, TotalDia=0;
    int Grade;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_info, container, false);

        archeryDB = new ArcheryDB(InfoFragment.this.getActivity());
        sharedPreferences = getActivity().getSharedPreferences(Utils.filename, Context.MODE_PRIVATE);

        Grade = sharedPreferences.getInt("Class", -1);

        //anh xa
        tvDiemNvC = view.findViewById(R.id.tvDiemNv);
        tvDiemLsC = view.findViewById(R.id.tvDiemLs);
        tvDiemDlC = view.findViewById(R.id.tvDiemDl);

        WrongVan= archeryDB.getWrong(Grade,1);
        WrongSu=archeryDB.getWrong(Grade,2);
        WrongDia=archeryDB.getWrong(Grade,3);

        TotalVan = archeryDB.getViewed(Grade,1);
        TotalSu = archeryDB.getViewed(Grade,2);
        TotalDia = archeryDB.getViewed(Grade,3);

        tvDiemNvC.setText(String.valueOf(10*(TotalVan - WrongVan)/TotalVan));
        tvDiemLsC.setText(String.valueOf(10*(TotalSu - WrongSu)/TotalSu));
        tvDiemDlC.setText(String.valueOf( 10*(TotalDia - WrongSu)/TotalDia));

        //anh xa
        tvCauSai1= view.findViewById(R.id.tvCauSai1);
        tvCauSai2 = view.findViewById(R.id.tvCauSai2);
        tvCauSai3 = view.findViewById(R.id.tvCauSai3);
        tvCauSai4= view.findViewById(R.id.tvCauSai4);
        tvCauSai5= view.findViewById(R.id.tvCauSai5);

        questionArrayList= archeryDB.get5Wrong(Grade);

        tvCauSai1.setText("Câu 1: " +questionArrayList.get(0).getQuestionContent());
        tvCauSai2.setText("Câu 2: " +questionArrayList.get(1).getQuestionContent());
        tvCauSai3.setText("Câu 3: " +questionArrayList.get(2).getQuestionContent());
        tvCauSai4.setText("Câu 4: " +questionArrayList.get(3).getQuestionContent());
        tvCauSai5.setText("Câu 5: " +questionArrayList.get(4).getQuestionContent());

        tvCauSai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lesson ls = archeryDB.getLessons(Grade, questionArrayList.get(0).getIDLesson());
                Intent i = new Intent(InfoFragment.this.getActivity(), LessonDetailActivity.class);
                i.putExtra("Marked", ls);
                startActivity(i);
            }
        });
        tvCauSai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lesson ls = archeryDB.getLessons(Grade, questionArrayList.get(1).getIDLesson());
                Intent i = new Intent(InfoFragment.this.getActivity(), LessonDetailActivity.class);
                i.putExtra("Marked", ls);
                startActivity(i);
            }
        });
        tvCauSai3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lesson ls = archeryDB.getLessons(Grade, questionArrayList.get(2).getIDLesson());
                Intent i = new Intent(InfoFragment.this.getActivity(), LessonDetailActivity.class);
                i.putExtra("Marked", ls);
                startActivity(i);
            }
        });
        tvCauSai4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lesson ls = archeryDB.getLessons(Grade, questionArrayList.get(3).getIDLesson());
                Intent i = new Intent(InfoFragment.this.getActivity(), LessonDetailActivity.class);
                i.putExtra("Marked", ls);
                startActivity(i);
            }
        });
        tvCauSai5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lesson ls = archeryDB.getLessons(Grade, questionArrayList.get(4).getIDLesson());
                Intent i = new Intent(InfoFragment.this.getActivity(), LessonDetailActivity.class);
                i.putExtra("Marked", ls);
                startActivity(i);
            }
        });

        return view;
    }

}