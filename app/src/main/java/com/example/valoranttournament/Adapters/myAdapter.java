package com.example.valoranttournament.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttournament.Models.Match_Model;
import com.example.valoranttournament.R;
import com.example.valoranttournament.valorant_detail_one;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.holder> {

    ArrayList<Match_Model> mList;
    Context mContext;
    FirebaseUser auth;
    StorageReference mStorage;

    public myAdapter(ArrayList<Match_Model> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tour_match_design, parent, false);

        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, @SuppressLint("RecyclerView") int position) {
        FirebaseDatabase.getInstance().getReference().child("New Match").child("Valorant Tournament").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        holder.matchNo.setText(mList.get(position).get_MatchNo());
                        holder.datetext.setText(mList.get(position).get_date());
                        holder.timetext.setText(mList.get(position).get_time());
                        holder.prizepooltext1.setText(mList.get(position).get_prizepool1());
                        holder.prizepooltext2.setText(mList.get(position).get_prizepool2());
                        holder.prizepooltext3.setText(mList.get(position).get_prizepool3());
                        holder.tour_formattext.setText(mList.get(position).get_tourformat());

                        holder.join_tour.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mContext, valorant_detail_one.class);
                                intent.putExtra("_matchNo", mList.get(position).get_MatchNo());
                                intent.putExtra("_date", mList.get(position).get_date());
                                intent.putExtra("_time", mList.get(position).get_time());
                                intent.putExtra("_prizepool1", mList.get(position).get_prizepool1());
                                intent.putExtra("_prizepool2", mList.get(position).get_prizepool2());
                                intent.putExtra("_prizepool3", mList.get(position).get_prizepool3());
                                intent.putExtra("_tourformat", mList.get(position).get_tourformat());
                                mContext.startActivity(intent);
                            }
                        });
                         /*FirebaseDatabase.getInstance().getReference().child("Joined Tournament")
                                        .child("Valorant Tournament")
                                        .child(mList.get(position).getMatchid())
                                        .addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.child(auth.getUid()).exists()){
                                                    holder.join_tour.setText("Joined");
                                                }
                                                else {
                                                    holder.join_tour.setText("Join");
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });*/
                        if (mList.get(position).getGametype().equals("Valorant Tournament")){
                            mStorage = FirebaseStorage.getInstance().getReference("picture/tournament.jpg");
                            try {
                                File localfile = File.createTempFile("tournament", "jpg");
                                mStorage.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                        Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                        holder.image.setImageBitmap(bitmap);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(mContext, "Image can't load", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        }

                        if (mList.get(position).getGametype().equals("Valorant Scrimmage")){
                            mStorage = FirebaseStorage.getInstance().getReference("picture/scrim.jpg");
                            try {
                                File localfile = File.createTempFile("scrim", "jpg");
                                mStorage.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                        Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                        holder.image.setImageBitmap(bitmap);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(mContext, "Image can't load", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class holder extends RecyclerView.ViewHolder{

        TextView matchNo, datetext, timetext, prizepooltext1, prizepooltext2, prizepooltext3, tour_formattext;
        Button join_tour;
        ImageView image;
        public holder(@NonNull View itemView) {
            super(itemView);
            //link
            matchNo = itemView.findViewById(R.id.matchNo);
            datetext = itemView.findViewById(R.id.datetext);
            timetext = itemView.findViewById(R.id.timetext);
            prizepooltext1 = itemView.findViewById(R.id.prizepooltext1);
            prizepooltext2 = itemView.findViewById(R.id.prizepooltext2);
            prizepooltext3 = itemView.findViewById(R.id.prizepooltext3);
            tour_formattext = itemView.findViewById(R.id.tour_formattext);
            join_tour = itemView.findViewById(R.id.join_tour);
            image = itemView.findViewById(R.id.image);
        }


    }

}
