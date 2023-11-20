package com.example.valoranttournament.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttournament.Models.Joined_Match_Model;
import com.example.valoranttournament.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class teamsAdapter extends RecyclerView.Adapter<teamsAdapter.holder> {

    ArrayList<Joined_Match_Model> mlist;
    Context mContext;

    public teamsAdapter(ArrayList<Joined_Match_Model> mlist, Context mContext) {
        this.mlist = mlist;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.teams_layout, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, @SuppressLint("RecyclerView") int position) {
        //update
        FirebaseDatabase.getInstance().getReference().child("Joined Tournament")
                .child("Valorant Tournament")
                .child(mlist.get(position).getJoinid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        holder.teams_name.setText(mlist.get(position).getJoinedname());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }
        class holder extends RecyclerView.ViewHolder {

        TextView teams_name;

        public holder(@NonNull View itemView) {
            super(itemView);

            teams_name = itemView.findViewById(R.id.teams_name);

        }
    }
    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
