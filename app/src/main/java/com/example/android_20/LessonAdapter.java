package com.example.android_20;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.android_20.model.Lesson;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {
    ArrayList<Lesson> lstLesson;
    Context context;
    Listener listener;


    public LessonAdapter(ArrayList<Lesson> lstLesson, Listener listener) {
        this.lstLesson = lstLesson;
        this.listener =listener;
    }



    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View userView = inflater.inflate(R.layout.lessonlayout, parent, false);

        LessonViewHolder lessonViewHolder = new LessonViewHolder(userView);
        return lessonViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder holder, int position) {
        Lesson item = lstLesson.get(position);
        String a ="Bài " + item.getUnit()+": " +item.getName();
        holder.tvNameC.setText(a);

        if (item.getMarked() == 1){
            holder.ibLessonMarkedC.setImageResource(R.drawable.baseline_star_24);
        }
        else {
            holder.ibLessonMarkedC.setImageResource(R.drawable.baseline_star_border_24);
        }

        if (item.getView() == 1){
            holder.ibStick.setImageResource(R.drawable.baseline_check_24);
        }

        holder.itemView.setOnClickListener(view -> listener.onItemClickListener(item));
        holder.ibLessonMarkedC.setOnClickListener(view -> listener.onMarked(item));
    }

    @Override
    public int getItemCount() {
        return lstLesson.size();
    }


    class LessonViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameC;
        ImageButton ibLessonMarkedC, ibStick;
        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameC = itemView.findViewById(R.id.tvLessonName);
            ibLessonMarkedC = itemView.findViewById(R.id.ibLessonMarked);
            ibStick = itemView.findViewById(R.id.ibLessonStick);
        }
    }
    interface Listener{
        void onItemClickListener(Lesson lesson);
        void onMarked(Lesson lesson);
    }



}
