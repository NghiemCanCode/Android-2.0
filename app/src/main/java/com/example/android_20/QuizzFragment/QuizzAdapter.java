package com.example.android_20.QuizzFragment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_20.R;
import com.example.android_20.model.Quizz;

import java.util.ArrayList;

public class QuizzAdapter extends RecyclerView.Adapter<QuizzAdapter.ViewHolder>{
    ArrayList<Quizz> quizzes;
    Context context;
    Listener listener;
    int pos = 0;
    public QuizzAdapter(ArrayList<Quizz> quizzes, Listener listener) {
        this.quizzes = quizzes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuizzAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View userView = inflater.inflate(R.layout.quizlayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(userView);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull QuizzAdapter.ViewHolder holder, int position) {
        Quizz item = quizzes.get(position);
        holder.tv.setText(String.valueOf(position + 1));
        if(item.getTrueOrFalse() == 0){
            holder.tv.setBackgroundColor(Color.rgb(255,255,255));
        }
        else if (item.getTrueOrFalse() > 0){
            holder.tv.setBackgroundColor(Color.rgb(0,255,0));
        }
        else
            holder.tv.setBackgroundColor(Color.rgb(255,0,0));

        if(pos == holder.getAdapterPosition())
            holder.tv.setBackgroundColor(Color.rgb(0,0,255));

        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onTextClick(item);
                pos = holder.getLayoutPosition()    ;
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.info_text);
        }
    }

    public interface Listener{
        void onTextClick(Quizz data);
    }
}
