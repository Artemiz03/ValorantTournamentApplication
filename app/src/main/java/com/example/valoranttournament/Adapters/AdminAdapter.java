package com.example.valoranttournament.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttournament.Models.Match_Model;
import com.example.valoranttournament.R;
import com.example.valoranttournament.edit_match;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.holder> {

    Context mContext;
    ArrayList<Match_Model> mlist;

    public AdminAdapter(Context mContext, ArrayList<Match_Model> mlist) {
        this.mContext = mContext;
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public AdminAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.admin_match_layout, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminAdapter.holder holder, @SuppressLint("RecyclerView") int position) {
        Match_Model match_model = mlist.get(position);
        holder.gamename.setText(mlist.get(position).getGametype()+" "+mlist.get(position).get_MatchNo());

        holder.menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setMessage("Do you want to delete or edit?"+mlist.get(position).getGametype()+": "+mlist.get(position).get_MatchNo());
                alert.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(mContext, edit_match.class);
                        intent.putExtra("gametype", mlist.get(position).getGametype());
                        intent.putExtra("matchid", mlist.get(position).getMatchid());
                        mContext.startActivity(intent);
                    }
                }).setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(mContext, "Delete", Toast.LENGTH_SHORT).show();
                        FirebaseDatabase.getInstance().getReference().child("New Match")
                                .child(mlist.get(position).getGametype())
                                .child(mlist.get(position).getMatchid())
                                .removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(mContext, "Delete Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class holder extends RecyclerView.ViewHolder {

        TextView gamename;
        ImageView menu;

        public holder(@NonNull View itemView) {
            super(itemView);
            gamename = itemView.findViewById(R.id.gamename);
            menu = itemView.findViewById(R.id.menu);
        }
    }
}
