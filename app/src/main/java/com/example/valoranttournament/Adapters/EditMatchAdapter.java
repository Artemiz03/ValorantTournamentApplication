package com.example.valoranttournament.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttournament.Models.Match_Model;
import com.example.valoranttournament.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class EditMatchAdapter extends RecyclerView.Adapter<EditMatchAdapter.holder> {

    Context mContext;
    ArrayList<Match_Model> mlist;

    public EditMatchAdapter(Context mContext, ArrayList<Match_Model> mlist) {
        this.mContext = mContext;
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public EditMatchAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.edit_match_layout, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EditMatchAdapter.holder holder, @SuppressLint("RecyclerView") int position) {

        holder.edit_prize_pool1.setText(mlist.get(position).get_prizepool1());
        holder.edit_prize_pool2.setText(mlist.get(position).get_prizepool2());
        holder.edit_prize_pool3.setText(mlist.get(position).get_prizepool3());
        holder.edit_match_name.setText(mlist.get(position).get_MatchNo());
        holder.edit_date_txt_.setText(mlist.get(position).get_date());
        holder.edit_time_txt_.setText(mlist.get(position).get_time());

        holder.submit_edit_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("_MatchNo" , holder.edit_match_name.getText().toString());
                hashMap.put("_date" , holder.edit_date_txt_.getText().toString());
                hashMap.put("_time" , holder.edit_time_txt_.getText().toString());
                hashMap.put("_prizepool1" , holder.edit_prize_pool1.getText().toString());
                hashMap.put("_prizepool2" , holder.edit_prize_pool2.getText().toString());
                hashMap.put("_prizepool3" , holder.edit_prize_pool3.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("New Match")
                        .child(mlist.get(position).getGametype())
                        .child(mlist.get(position).getMatchid())
                        .updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(mContext, "Update Successfully", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

        class holder extends RecyclerView.ViewHolder {

        EditText edit_prize_pool1, edit_prize_pool2, edit_prize_pool3, edit_match_name, edit_date_txt_, edit_time_txt_;
        Spinner edit_spinner2;
        Button submit_edit_match;

        public holder(@NonNull View itemView) {
            super(itemView);

            edit_prize_pool1 = itemView.findViewById(R.id.edit_prize_pool1);
            edit_prize_pool2 = itemView.findViewById(R.id.edit_prize_pool2);
            edit_prize_pool3 = itemView.findViewById(R.id.edit_prize_pool3);
            edit_match_name = itemView.findViewById(R.id.edit_match_name);
            edit_date_txt_ = itemView.findViewById(R.id.edit_date_txt_);
            edit_time_txt_ = itemView.findViewById(R.id.edit_time_txt_);
            submit_edit_match = itemView.findViewById(R.id.submit_edit_match);
            edit_spinner2 = itemView.findViewById(R.id.edit_spinner2);
        }
    }
}
