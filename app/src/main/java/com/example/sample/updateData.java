package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updateData extends AppCompatActivity {

    EditText etName, etPhone, etAddress;
    Button btnUpdate;
    String id, name, phone, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);

        btnUpdate = findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        phone = intent.getStringExtra("phone");
        address = intent.getStringExtra("address");

        etName.setText(name);
        etPhone.setText(phone);
        etAddress.setText(address);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User Data").child(id);
                String uName, uPhone, uAddress;

                uName = etName.getText().toString();
                uPhone = etPhone.getText().toString();
                uAddress = etAddress.getText().toString();
                UserData userData = new UserData(id, uName, uPhone, uAddress);
                databaseReference.setValue(userData);
                Toast.makeText(updateData.this,"Data Updated", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(updateData.this,ViewAllData.class);
                startActivity(i);
                finish();

            }
        });

    }
}
