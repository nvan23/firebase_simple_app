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
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ChangePassword extends AppCompatActivity {
    EditText etOldPass, etNewPass;
    Button btnChangeNow, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        etOldPass = findViewById(R.id.etOldPass);
        etNewPass = findViewById(R.id.etNewPass);

        btnChangeNow = findViewById(R.id.btnChangeNow);
        btnHome = findViewById(R.id.btnHome);

        btnChangeNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String oldPass, newPass;
                oldPass = etOldPass.getText().toString();
                newPass = etNewPass.getText().toString();

                if(oldPass.equals("")){
                    Toast.makeText(ChangePassword.this,"Old Password is required", Toast.LENGTH_SHORT).show();
                }
                else if(newPass.equals("")){
                    Toast.makeText(ChangePassword.this,"New Password is required", Toast.LENGTH_SHORT).show();
                }
                else if(newPass.length() < 6){
                    Toast.makeText(ChangePassword.this, "Password too short", Toast.LENGTH_SHORT).show();
                }
                else {
                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(),oldPass);

                    user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                user.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(ChangePassword.this,"Password Changed", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(ChangePassword.this,"Password not Changed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }
                            else {
                                Toast.makeText(ChangePassword.this,"Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChangePassword.this,Home.class);
                startActivity(i);
                finish();
            }
        });

    }
}
