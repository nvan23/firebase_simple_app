package com.example.sample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
    private Button btnSignIn;
    private EditText etEmail, etPassword;
    private TextView labelRegister;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnSignIn = findViewById(R.id.btnSignIn);

        labelRegister = findViewById(R.id.labelRegister);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        labelRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirect_Register();
            }
        });

    }

    private void signIn(){
        String email, password;
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if(email.equals("")){
            Toast.makeText(SignIn.this,"Email is required", Toast.LENGTH_SHORT).show();
        }
        else if(password.equals("")){
            Toast.makeText(SignIn.this,"Password is required", Toast.LENGTH_SHORT).show();
        }
        else if(password.length() < 6){
            Toast.makeText(SignIn.this, "Password too short", Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent i = new Intent(SignIn.this,Home.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                        Toast.makeText(SignIn.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void redirect_Register(){
        Intent i = new Intent(SignIn.this, Register.class);
        startActivity(i);
        finish();
    }
}
