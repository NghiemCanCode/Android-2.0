package com.example.android_20.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android_20.LessonActivity;
import com.example.android_20.R;

public class HomeFragment extends Fragment {
    Button btNguVanC;
    Button btLichSuC;
    Button btDiaLyC;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        btNguVanC= view.findViewById(R.id.btNguVan);
        btLichSuC= view.findViewById(R.id.btLichSu);
        btDiaLyC= view.findViewById(R.id.btDiaLy);

        btNguVanC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeFragment.this.getActivity(), LessonActivity.class);
                i.putExtra("Subject", 1);
                startActivity(i);
            }
        });
        btDiaLyC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeFragment.this.getActivity(), LessonActivity.class);
                i.putExtra("Subject", 3);
                startActivity(i);
            }
        });
        btLichSuC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeFragment.this.getActivity(), LessonActivity.class);
                i.putExtra("Subject", 2);
                startActivity(i);
            }
        });
        return view;
    }


}