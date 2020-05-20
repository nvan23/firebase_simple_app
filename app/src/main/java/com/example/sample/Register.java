package com.example.sample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private Button btnSignUp, btnSignIn;
    private EditText etEmail, etPassword;


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirect_SignIn();
            }
        });

    }

    private void signUp(){
        String email, password;
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if(email.equals("")){
            Toast.makeText(Register.this,"Email required", Toast.LENGTH_SHORT).show();
        }
        else if(password.equals("")){
            Toast.makeText(Register.this,"Password required", Toast.LENGTH_SHORT).show();
        }
        else if(password.length() < 6){
            Toast.makeText(Register.this, "Password too short", Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Register.this,"Registered Successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Register.this,SignIn.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                        Toast.makeText(Register.this, "Registrations Failure", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void redirect_SignIn(){
        Intent i = new Intent(Register.this,SignIn.class);
        startActivity(i);
        finish();
    }
}





