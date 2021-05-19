package com.example.vlot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {

    private TextView username,email,mobileno,role;
    private String mail,password;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        role=findViewById(R.id.profile_name);
        username=findViewById(R.id.textView5);
        email=findViewById(R.id.textView6);
        mobileno=findViewById(R.id.textView7);
    }
}