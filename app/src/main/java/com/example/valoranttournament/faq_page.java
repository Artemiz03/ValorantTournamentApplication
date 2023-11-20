package com.example.valoranttournament;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class faq_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq_page_layout);
        getSupportActionBar().hide();
    }
}