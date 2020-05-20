package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button btnInsert, btnViewAllData, btnChangePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnInsert = findViewById(R.id.btnInsertData);
        btnViewAllData = findViewById(R.id.btnViewAllData);
        btnChangePassword = findViewById(R.id.btnChangePassword);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this,SaveData.class);
                startActivity(i);
                finish();
            }
        });

        btnViewAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this,ViewAllData.class);
                startActivity(i);
                finish();
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this,ChangePassword.class);
                startActivity(i);
                finish();
            }
        });
    }
}
