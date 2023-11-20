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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    TextView Logintxt;
    EditText nameReg;
    EditText emailReg;
    EditText passReg;
    Button btnRegister;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        Logintxt = findViewById(R.id.Logintxt);

        nameReg = findViewById(R.id.nameReg);
        emailReg = findViewById(R.id.emailReg);
        passReg = findViewById(R.id.passReg);

        btnRegister = findViewById(R.id.btnRegister);

        auth = FirebaseAuth.getInstance();

        Logintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameReg.getText().toString();
                String email = emailReg.getText().toString();
                String pass = passReg.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(RegisterActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    emailReg.setError("Email is Required");
                    emailReg.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    passReg.setError("Password is Required");
                    passReg.requestFocus();
                    return;
                }
                if (pass.length()<6) {
                    passReg.setError("Password must be 6 character long or more");
                    return;
                }
                else{
                    register(email,pass);
                }
            }
            private void register(String email, String pass) {
                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete()) {
                            FirebaseUser Fuser = auth.getCurrentUser();
                            //sent email verification
                            Fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    String name = nameReg.getText().toString();

                                    // move to another activity with same data
                                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                    intent.putExtra("keyname",name);
                                    startActivity(intent);

                                    Toast.makeText(RegisterActivity.this, "Sent Verification Email..", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterActivity.this, "Failed To Sent Email Verification..", Toast.LENGTH_SHORT).show();
                                }
                            });
                            /*startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            Toast.makeText(RegisterActivity.this, "Successfully Register", Toast.LENGTH_SHORT).show();*/
                        } else {
                            Toast.makeText(RegisterActivity.this, "Register Failed..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

}


