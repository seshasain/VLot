package com.example.vlot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private Button pfbtn;
    public String mail;
    private FirebaseDatabase database;
    private DatabaseReference cuserref,vuserref;
    private static final String cusers="customers";
    private static final String vusers="vendors";
    int flag=0;
    static  String veg,em,rol,mno;
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
        pfbtn=findViewById(R.id.button);

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

        pfbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edtpf=new Intent(Profile.this, Editprofile.class);
                startActivity(edtpf);
            }
        });

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
            public synchronized void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (mail1.equals(ds.child("email").getValue())) {
                        if (req.equals("vegetables")) {
                            veg = ds.child("vegetables").getValue(String.class);
                            rtype = 1;
                            break;
                        }

                        if (req.equals("email")) {
                            em = ds.child("email").getValue(String.class);
                            rtype = 1;
                            break;
                        }
                        if (req.equals("role")) {
                            rol = ds.child("role").getValue(String.class);
                            rtype = 1;
                            break;
                        }
                        if (req.equals("mobilenum")) {
                            mno = ds.child("mobileno").getValue(String.class);
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
                public synchronized void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        if (mail.equals(ds.child("email").getValue())) {
                            if (req.equals("vegetables")) {
                                veg = ds.child("vegetables").getValue(String.class);
                                rtype = 1;
                                break;
                            }

                            if (req.equals("email")) {
                                em = ds.child("email").getValue(String.class);
                                rtype = 1;
                                break;
                            }
                            if (req.equals("role")) {
                                rol = ds.child("role").getValue(String.class);
                                rtype = 1;
                                break;
                            }
                            if (req.equals("mobilenum")) {
                                mno = ds.child("mobileno").getValue(String.class);
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

          return "final";
    }
}