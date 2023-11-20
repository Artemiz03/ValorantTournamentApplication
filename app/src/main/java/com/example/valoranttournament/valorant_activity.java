package com.example.valoranttournament;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttournament.Adapters.myAdapter;
import com.example.valoranttournament.Models.Match_Model;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class valorant_activity extends AppCompatActivity {

    RecyclerView recyclerView;
    myAdapter mAdapter;
    ArrayList<Match_Model> list;
    FirebaseUser auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valorant);
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.recycle_View);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<Match_Model>();
        //retrieve data from database
        FirebaseDatabase.getInstance().getReference().child("New Match").child("Valorant Tournament").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1: snapshot.getChildren()){
                            Match_Model matchModel2 = snapshot1.getValue(Match_Model.class);
                            list.add(matchModel2);
                        }
                        // set data in recycleView
                        mAdapter = new myAdapter(list, valorant_activity.this);
                        recyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}