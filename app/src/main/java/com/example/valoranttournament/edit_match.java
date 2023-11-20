package com.example.valoranttournament;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttournament.Adapters.EditMatchAdapter;
import com.example.valoranttournament.Models.Match_Model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class edit_match extends AppCompatActivity {

    RecyclerView recyclerView;
    EditMatchAdapter mAdapter;
    ArrayList<Match_Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_match);
        getSupportActionBar().hide();

        //Get data from adminAdapter
        String gameType = getIntent().getStringExtra("gametype");
        String matchId = getIntent().getStringExtra("matchid");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<Match_Model>();
        //retrieve data from database
        FirebaseDatabase.getInstance().getReference().child("New Match")
                .child(gameType).child(matchId)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                    Match_Model matchModel2 = snapshot.getValue(Match_Model.class);
                    list.add(matchModel2);

                // set data in recycleView
                mAdapter = new EditMatchAdapter ( edit_match.this, list);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}