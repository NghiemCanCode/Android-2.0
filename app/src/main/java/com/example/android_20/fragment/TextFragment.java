package com.example.android_20.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.fonts.Font;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android_20.Lesson.LessonExamActivity;
import com.example.android_20.R;
import com.example.android_20.SettingActivity;


public class TextFragment extends Fragment {
    private View view;
    TextView tv;
    Button btnStart;
    SharedPreferences sharedPreferences;
    int FontSize;
    public TextFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_text, container, false);
        btnStart=view.findViewById(R.id.btnThi);
//        sharedPreferences = getActivity().getSharedPreferences(SettingActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        FontSize = sharedPreferences.getInt(SettingActivity.KEY_FONT_SIZE, -1);
//        tv=view.findViewById(R.id.tvTest);
//        tv.setTextSize(FontSize);
//        btnStart.setTextSize(FontSize);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TextFragment.this.getActivity(),LessonExamActivity.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}