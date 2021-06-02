package com.example.vlot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    private TextView pname,pemail,pnumber,prole;
    public String mail;
    private FirebaseDatabase database;
    private DatabaseReference cuserref,vuserref;
    private static final String cusers="customers";
    private static final String vusers="vendors";
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mail=user.getEmail();

        DatabaseReference rootref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference cuserref = rootref.child(cusers);
        DatabaseReference vuserref=rootref.child(vusers);
        //Log.v("EMAILADD", userref.orderByChild("email").equalTo(mail).toString());

        prole = findViewById(R.id.prole);
        pname = findViewById(R.id.pname);
        pemail = findViewById(R.id.pemail);
        pnumber = findViewById(R.id.pnumber);

        database = FirebaseDatabase.getInstance();
        cuserref = database.getReference(cusers);
        vuserref= database.getReference(vusers);

        //Toast.makeText(Profile.this, mail, Toast.LENGTH_LONG).show();


        cuserref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    if (mail.equals(ds.child("email").getValue()))
                    {
                        //Toast.makeText(Profile.this, "hai", Toast.LENGTH_LONG).show();
                        pname.setText(ds.child("name").getValue(String.class));
                        pemail.setText(ds.child("email").getValue(String.class));
                        pnumber.setText(ds.child("mobileno").getValue(String.class));
                      prole.setText(ds.child("role").getValue(String.class));
                      flag=1;
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, "something went wrong..", Toast.LENGTH_LONG).show();
            }
        });
        if(flag==0)
        {
            vuserref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        if (mail.equals(ds.child("email").getValue())) {
                            //Toast.makeText(Profile.this, "hai", Toast.LENGTH_LONG).show();
                            pname.setText(ds.child("name").getValue(String.class));
                            pemail.setText(ds.child("email").getValue(String.class));
                            pnumber.setText(ds.child("mobileno").getValue(String.class));
                            prole.setText(ds.child("role").getValue(String.class));
                            flag = 0;

                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Profile.this, "something went wrong..", Toast.LENGTH_LONG).show();
                }
            });
        }


    }
}