package com.example.android_20;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_20.model.Lesson;
import com.example.android_20.model.Notes;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteVH> {
    ArrayList<Notes> notes;
    Listener listener;
    public NoteAdapter(Listener listener, ArrayList<Notes> notes){
        this.listener=listener;
        this.notes=notes;
    }
    @NonNull
    @Override
    public NoteVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.note_design, parent,false);
        return new NoteVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteVH holder, int position) {
        Notes note=notes.get(position);
        holder.tvName.setText(note.getContent());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDelete(note);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClickItemListener(note);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteVH extends RecyclerView.ViewHolder{
        TextView tvName;
        ImageButton imgDelete;

        public NoteVH(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);

            imgDelete=itemView.findViewById(R.id.imgDelete);
        }
    }
    interface Listener{
        void OnClickItemListener(Notes notes);
        void onDelete(Notes notes);
    }
}