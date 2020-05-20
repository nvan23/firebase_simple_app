package com.example.sample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private FloatingActionButton btnSignUp;
    private EditText etEmail, etPassword;
    private TextView labelSignIn;


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        labelSignIn = findViewById(R.id.labelSignIn);

        btnSignUp = findViewById(R.id.btnSignUp);
        //btnSignIn = findViewById(R.id.btnSignIn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        labelSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirect_SignIn();
            }
        });

    }

    public void OpenSignupPage(View view) {
        startActivity(new Intent(Register.this,SignIn.class));
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





