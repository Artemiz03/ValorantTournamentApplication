package com.example.valoranttournament;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class patch_note extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patch_note_activity);
        getSupportActionBar().hide();
    }
}