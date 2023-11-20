package com.example.valoranttournament;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttournament.Adapters.AdminAdapter;
import com.example.valoranttournament.Models.Match_Model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class admin_panel_fragment3 extends Fragment {

    RecyclerView recyclerView;
    AdminAdapter mAdapter;
    ArrayList<Match_Model> list;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admin_panel_fragment3, container, false);

        recyclerView = view.findViewById(R.id.recycle_View);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<Match_Model>();
        //retrieve data from database
        FirebaseDatabase.getInstance().getReference().child("New Match").child("Valorant Scrimmage").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    Match_Model matchModel2 = snapshot1.getValue(Match_Model.class);
                    list.add(matchModel2);
                }
                // set data in recycleView
                mAdapter = new AdminAdapter( getContext(), list);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}