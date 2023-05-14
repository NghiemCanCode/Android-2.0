package com.example.android_20.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android_20.ArcheryDB;
import com.example.android_20.InfoAdapter;
import com.example.android_20.Lesson.LessonDetailActivity;
import com.example.android_20.R;
import com.example.android_20.Utils;
import com.example.android_20.model.Lesson;
import com.example.android_20.model.Question;

import java.util.ArrayList;


public class InfoFragment extends Fragment implements InfoAdapter.Listener{
    TextView tvDiemNvC, tvDiemLsC, tvDiemDlC;
    ArcheryDB archeryDB;
    ArrayList<Question> questionArrayList;
    SharedPreferences sharedPreferences;
    RecyclerView rv;
    double WrongVan = 0, WrongSu = 0, WrongDia = 0, TotalVan = 0, TotalSu = 0, TotalDia = 0;
    int Grade;
    InfoAdapter infoAdapter;

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
        rv = view.findViewById(R.id.rvInfo);

        WrongVan = archeryDB.getWrong(Grade,1);
        WrongSu = archeryDB.getWrong(Grade,2);
        WrongDia = archeryDB.getWrong(Grade,3);

        TotalVan = archeryDB.getViewed(Grade,1);
        TotalSu = archeryDB.getViewed(Grade,2);
        TotalDia = archeryDB.getViewed(Grade,3);

        double mauS = checkDivideBy0(TotalSu);
        double mauV = checkDivideBy0(TotalVan);
        double mauD = checkDivideBy0(TotalDia);

        tvDiemNvC.setText(String.format("%.02f",10 * (TotalVan - WrongVan) /mauV));
        tvDiemLsC.setText(String.format("%.02f",10 * (TotalSu - WrongSu) /mauS));
        tvDiemDlC.setText(String.format("%.02f",10 * (TotalDia - WrongDia) /mauD));

        //anh xa
        questionArrayList = archeryDB.get5Wrong(Grade);
        infoAdapter = new InfoAdapter(questionArrayList, this );

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        rv.setAdapter(infoAdapter);
        rv.setLayoutManager(linearLayoutManager);

        return view;
    }


    private double checkDivideBy0(double a){
        if(a > 0) return a;
        return 1;
    }

    @Override
    public void onItemClickListener(Question question) {
        Lesson lesson = archeryDB.getLessons(question.getIDLesson());
        Intent i = new Intent(InfoFragment.this.getActivity(), LessonDetailActivity.class);
        i.putExtra("Marked", lesson);
        startActivity(i);
    }
}