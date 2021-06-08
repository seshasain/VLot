package com.example.vlot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Editprofile extends AppCompatActivity {

    private EditText pname,ppwd,cdist;
    private ImageView dist1;
    private TextView pemail,pnumber;
    public static String mail,prole,mno;
    private FirebaseDatabase database;
    private DatabaseReference cuserref,vuserref;
    private static final String cusers="customers";
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference customers=db.getReference().child("customers");
    private DatabaseReference vendors=db.getReference().child("vendors");
    private static final String vusers="vendors";
    int flag=0;
    Button updateprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mail=user.getEmail();
        DatabaseReference rootref = FirebaseDatabase.getInstance().getReference();
        cuserref = rootref.child(cusers);
        vuserref=rootref.child(vusers);
        pname = findViewById(R.id.editpname);
        pemail = findViewById(R.id.edemail);
        pnumber = findViewById(R.id.edmnum);
        ppwd = findViewById(R.id.editpwd);
        cdist=findViewById(R.id.cdistreq);
        dist1=findViewById(R.id.distimg);
        updateprofile=findViewById(R.id.editprofile);
        database = FirebaseDatabase.getInstance();
        cuserref = database.getReference(cusers);
        vuserref= database.getReference(vusers);
        cuserref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (mail.equals(ds.child("email").getValue())) {
                        pname.setText(ds.child("name").getValue(String.class));
                        pemail.setText(ds.child("email").getValue(String.class));
                        pnumber.setText(ds.child("mobileno").getValue(String.class));
                        ppwd.setText(ds.child("password").getValue(String.class));
                        cdist.setText(ds.child("distance").getValue(String.class));
                        prole = ds.child("role").getValue(String.class);
                        mno=ds.child("mobileno").getValue(String.class);
                        System.out.println("role customer");
                        cdist.setVisibility(View.VISIBLE);
                        dist1.setVisibility(View.VISIBLE);
                    }
                    //Toast.makeText(Editprofile.this, prole, Toast.LENGTH_LONG).show();
                    //cdist.setText(ds.child("distance").getValue(String.class));
                    flag = 1;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Editprofile.this, "something went wrong..", Toast.LENGTH_LONG).show();
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
                            ppwd.setText(ds.child("password").getValue(String.class));
                            prole=ds.child("role").getValue(String.class);
                            mno=ds.child("mobileno").getValue(String.class);
                            cdist.setVisibility(View.INVISIBLE);
                            dist1.setVisibility(View.INVISIBLE);
                            System.out.println("disp else");
                            flag = 0;

                        }

                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Editprofile.this, "something went wrong..", Toast.LENGTH_LONG).show();
                }
            });
        }
        updateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name,mail,pnum,pwd,setdist;
                name=pname.getText().toString();
                pwd=ppwd.getText().toString();
                Map<String, Object> userMapd = new HashMap<>();
                setdist = cdist.getText().toString();
                userMapd.put("distance",setdist);
                //setdist = cdist.getText().toString();
                    //Toast.makeText(Editprofile.this, setdist, Toast.LENGTH_LONG).show();

                Map<String, Object> userMapn = new HashMap<>();
                Map<String, Object> userMapp = new HashMap<>();
                userMapn.put("name",name);
                userMapp.put("password",pwd);
                //Toast.makeText(Editprofile.this, setdist, Toast.LENGTH_LONG).show();
                if(prole.equals("Customer"))
                {
                    int dist=Integer.parseInt(setdist);
                      if(dist>500 && dist<=5000)
                      {
                          customers.child(mno).updateChildren(userMapn);
                          customers.child(mno).updateChildren(userMapp);
                          customers.child(mno).updateChildren(userMapd);
                          //cdist.setText(setdist);
                          Toast.makeText(Editprofile.this, "Profile details updated successfully", Toast.LENGTH_LONG).show();
                      }
                      else
                      {
                          Toast.makeText(Editprofile.this, "Please enter range from 0.5 to 5km", Toast.LENGTH_LONG).show();
                          Toast.makeText(Editprofile.this, setdist, Toast.LENGTH_LONG).show();
                      }
                }
                else if(prole.equals("Vendor"))
                {
                    vendors.child(mno).updateChildren(userMapn);
                    vendors.child(mno).updateChildren(userMapp);
                    Toast.makeText(Editprofile.this, "Profile details updated successfully", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(Editprofile.this, prole, Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}

