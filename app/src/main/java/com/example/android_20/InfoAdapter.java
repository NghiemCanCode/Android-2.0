package com.example.android_20;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_20.model.Question;

import java.util.ArrayList;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder>{
    ArrayList<Question> questionArrayList;
    Context context;
    public InfoAdapter(ArrayList<Question> questionArrayList) {
        this.questionArrayList = questionArrayList;
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View userView = inflater.inflate(R.layout.infoitemlayout, parent, false);

        return new InfoViewHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        Question question = questionArrayList.get(position);
        String tmp = "Top " + (position + 1) + ": "+ question.getQuestionContent();
        holder.tvInfoItemC.setText(tmp);
        holder.tvInfoItem2C.setText(String.valueOf(question.getWrongCount()));
    }

    @Override
    public int getItemCount() {
        return questionArrayList.size();
    }

    class InfoViewHolder extends RecyclerView.ViewHolder {
        TextView tvInfoItemC, tvInfoItem2C;

        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);

            tvInfoItemC = itemView.findViewById(R.id.tvInfoItem);
            tvInfoItem2C = itemView.findViewById(R.id.tvInfoItem2);
        }
    }
}
