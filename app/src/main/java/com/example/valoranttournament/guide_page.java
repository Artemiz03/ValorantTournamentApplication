package com.example.valoranttournament;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class guide_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_page_layout);
        getSupportActionBar().hide();
    }
}