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
    static  String veg,em,rol,mno;
    private FirebaseDatabase database;
    private DatabaseReference cuserref,vuserref;
    private static final String cusers="customers";
    private static final String vusers="vendors";
    int flag=0;
    int rtype,rt;
    String mail1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mail=user.getEmail();

        DatabaseReference rootref = FirebaseDatabase.getInstance().getReference();
        cuserref = rootref.child(cusers);
        vuserref=rootref.child(vusers);

        prole = findViewById(R.id.prole);
        pname = findViewById(R.id.pname);
        pemail = findViewById(R.id.pemail);
        pnumber = findViewById(R.id.pnumber);

        database = FirebaseDatabase.getInstance();
        cuserref = database.getReference(cusers);
        vuserref= database.getReference(vusers);

        cuserref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    if (mail.equals(ds.child("email").getValue()))
                    {
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
                        if (mail.equals(ds.child("email").getValue()))
                        {
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
        Toast.makeText(Profile.this,Currentuserdetails("email") , Toast.LENGTH_LONG).show();
        Toast.makeText(Profile.this,Currentuserdetails("vegetables") , Toast.LENGTH_LONG).show();
        Toast.makeText(Profile.this,Currentuserdetails("role") , Toast.LENGTH_LONG).show();
        Toast.makeText(Profile.this,Currentuserdetails("mobilenum") , Toast.LENGTH_LONG).show();

    }


    void getValue()
    {
        if(rt==1)
        {
            Toast.makeText(Profile.this, veg, Toast.LENGTH_LONG).show();
            System.out.println(veg);
        }
        if(rt==2)
        {
            Toast.makeText(Profile.this, em, Toast.LENGTH_LONG).show();
        }
        if(rt==3)
        {
            Toast.makeText(Profile.this, rol, Toast.LENGTH_LONG).show();
        }
        if(rt==4)
        {
            Toast.makeText(Profile.this, mno, Toast.LENGTH_LONG).show();
        }
    }
    public synchronized String Currentuserdetails(String req)
    {
        DatabaseReference cuserref, vuserref;
        String cusers = "customers";
        String vusers = "vendors";
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mail1 = user.getEmail();

        DatabaseReference rootref = FirebaseDatabase.getInstance().getReference();
        cuserref = rootref.child(cusers);
        vuserref = rootref.child(vusers);

        cuserref.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (mail1.equals(ds.child("email").getValue())) {
                        if (req.equals("vegetables")) {
                            veg = ds.child("vegetables").getValue(String.class);
                            rt = 1;
                            getValue();
                            rtype = 1;
                            break;
                        }

                        if (req.equals("email")) {
                            em = ds.child("email").getValue(String.class);
                            rt = 2;
                            getValue();
                            rtype = 1;
                            break;
                        }
                        if (req.equals("role")) {
                            rol = ds.child("role").getValue(String.class);
                            rt = 3;
                            getValue();
                            rtype = 1;
                            break;
                        }
                        if (req.equals("mobilenum")) {
                            mno = ds.child("mobileno").getValue(String.class);
                            rt = 4;
                            getValue();
                            rtype = 1;
                            break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, "something went wrong with the database..", Toast.LENGTH_LONG).show();
            }
        });

        if (rtype == 0) {
            vuserref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        if (mail.equals(ds.child("email").getValue())) {
                            if (req.equals("vegetables")) {
                                veg = ds.child("vegetables").getValue(String.class);
                                rt = 1;
                                getValue();
                                rtype = 1;
                                break;
                            }

                            if (req.equals("email")) {
                                em = ds.child("email").getValue(String.class);
                                rt = 2;
                                getValue();
                                rtype = 1;
                                break;
                            }
                            if (req.equals("role")) {
                                rol = ds.child("role").getValue(String.class);
                                rt = 3;
                                getValue();
                                rtype = 1;
                                break;
                            }
                            if (req.equals("mobilenum")) {
                                mno = ds.child("mobileno").getValue(String.class);
                                rt = 4;
                                getValue();
                                rtype = 1;
                                break;
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Profile.this, "something went wrong with the database..", Toast.LENGTH_LONG).show();
                }
            });
        }

          return em;
    }
}