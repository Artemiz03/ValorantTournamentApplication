package com.example.valoranttournament;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttournament.Adapters.myAdapter;
import com.example.valoranttournament.Models.Match_Model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class valorant_scrim_layout extends AppCompatActivity {

    RecyclerView recycleViews;
    myAdapter mAdapter;
    ArrayList<Match_Model> list;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrim_layout);
        getSupportActionBar().hide();

        recycleViews = findViewById(R.id.recycleViews);
        recycleViews.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<Match_Model>();
        //retrieve data from database
        FirebaseDatabase.getInstance().getReference().child("New Match").child("Valorant Scrimmage")
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    Match_Model matchModel2 = snapshot1.getValue(Match_Model.class);
                    list.add(matchModel2);
                }
                // set data in recycleView
                mAdapter = new myAdapter(list, valorant_scrim_layout.this);
                recycleViews.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}