package com.example.valoranttournament;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Host_Control_Activity extends AppCompatActivity {

    EditText code_txt;
    Button host_btn;
    FirebaseUser auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamehost);
        getSupportActionBar().hide();

        code_txt = findViewById(R.id.code_txt);
        host_btn = findViewById(R.id.host_btn);

        auth = FirebaseAuth.getInstance().getCurrentUser();

        host_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code_text = code_txt.getText().toString();

                if (TextUtils.isEmpty(code_text)){
                    Toast.makeText(Host_Control_Activity.this, "Enter Code", Toast.LENGTH_SHORT).show();
                }
                else if (code_text.equals("APP001")){
                    Toast.makeText(Host_Control_Activity.this, "You are Host Now :)", Toast.LENGTH_SHORT).show();
                    FirebaseDatabase.getInstance().getReference().child("AppHost").child(auth.getUid())
                            .setValue("true");

                    startActivity(new Intent(getApplicationContext(), admin_panel.class));
                }
                else {
                    Toast.makeText(Host_Control_Activity.this, "Enter Valid Code", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}