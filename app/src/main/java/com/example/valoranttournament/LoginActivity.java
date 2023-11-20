package com.example.valoranttournament;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    TextView Registertxt;
    TextView usernametext;
    EditText emailLogin;
    EditText passLogin;
    Button btnLogin;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        usernametext = findViewById(R.id.usernametext);
        usernametext.setVisibility(View.VISIBLE);
        //get value from register activity
        String name = getIntent().getStringExtra("keyname");
        usernametext.setText(name+":Please check your email box to verify email address");

        Registertxt = findViewById(R.id.Registertxt);

        emailLogin = findViewById(R.id.emailLogin);
        passLogin = findViewById(R.id.passLogin);

        btnLogin = findViewById(R.id.btnLogin);

        auth = FirebaseAuth.getInstance();

        Registertxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailLogin.getText().toString();
                String pass = passLogin.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    emailLogin.setError("Email is Required");
                    emailLogin.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    passLogin.setError("Password is Required");
                    passLogin.requestFocus();
                    return;
                }
                if (pass.length()<6) {
                    passLogin.setError("Password must be 6 character long or more");
                    return;
                }
                else {
                    login(email, pass);
                }
            }
            private void login(String email, String pass) {
                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //get verification of email
                            if(auth.getCurrentUser().isEmailVerified()){
                                String email = emailLogin.getText().toString();
                                String pass = passLogin.getText().toString();

                                //Store data in database
                                HashMap <String, Object> hashMap = new HashMap<>();
                                hashMap.put("name", name);
                                hashMap.put("email", email);
                                hashMap.put("pass", pass);
                                //Insert data to database & use to create random UID for user
                                FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getCurrentUser().getUid()).setValue(hashMap);

                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                Toast.makeText(LoginActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Please Verify Your Email", Toast.LENGTH_SHORT).show();
                            }
                            /*startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();*/
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}