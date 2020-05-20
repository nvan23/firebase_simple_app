package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SaveData extends AppCompatActivity {

    private EditText name, id, phoneNo, address;
    private Button btnInsert, btnViewAllData;

    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);

        name = findViewById(R.id.etName);
        phoneNo = findViewById(R.id.etPhone);
        address = findViewById(R.id.etAddress);

        btnInsert = findViewById(R.id.btnInsert);
        btnViewAllData = findViewById(R.id.btnViewAllData);

        database = FirebaseDatabase.getInstance().getReference().child("User Data");

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId, userName, userPhone, userAddress;

                userId = database.push().getKey();
                userName = name.getText().toString();
                userPhone = phoneNo.getText().toString();
                userAddress = address.getText().toString();

                if(userName.equals("")){
                    Toast.makeText(SaveData.this,"User Name required", Toast.LENGTH_SHORT).show();
                }
                else if(userPhone.equals("")){
                    Toast.makeText(SaveData.this,"User Phone required", Toast.LENGTH_SHORT).show();
                }
                else if(userAddress.equals("")){
                    Toast.makeText(SaveData.this, "User Address required", Toast.LENGTH_SHORT).show();
                }
                else {
                    UserData userData = new UserData(userId, userName, userPhone, userAddress);
                    database.child(userId).setValue(userData);
                    Toast.makeText(SaveData.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnViewAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SaveData.this,ViewAllData.class);
                startActivity(i);
                finish();
            }
        });
    }

}
