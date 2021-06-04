package com.example.vlot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.location.LocationListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.os.Bundle;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;
import android.graphics.Color;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Vegetables extends AppCompatActivity {

    Button ta,pa,boa,cua,caa,bia,da,la,ga,ma;
    Button tr,pr,bor,cur,car,bir,dr,lr,gr,mr;
    Button ok,cancel;

    public static  String veg,em,rol,mno;
    public int rtype,rt;
    public String mail1;

    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference customers=db.getReference().child("customers");
    private DatabaseReference vendors=db.getReference().child("vendors");
    ArrayList<String> vegies=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetables);
        ta=(Button)findViewById(R.id.tomatoadd);
        pa=(Button)findViewById(R.id.potatoadd);
        boa=(Button)findViewById(R.id.bottlegourdadd);
        cua=(Button)findViewById(R.id.cucumberadd);
        caa=(Button)findViewById(R.id.capsicumadd);
        bia=(Button)findViewById(R.id.bittergourdadd);
        da=(Button)findViewById(R.id.drumstickadd);
        la=(Button)findViewById(R.id.ladiesfingeradd);
        ga=(Button)findViewById(R.id.gounguraadd);
        ma=(Button)findViewById(R.id.mintadd);

        tr=(Button)findViewById(R.id.tomatoremove);
        pr=(Button)findViewById(R.id.potatoremove);
        bor=(Button)findViewById(R.id.bottlegourdremove);
        cur=(Button)findViewById(R.id.cucumberremove);
        car=(Button)findViewById(R.id.capsicumremove);
        bir=(Button)findViewById(R.id.bitterguardremove);
        dr=(Button)findViewById(R.id.drumstickremove);
        lr=(Button)findViewById(R.id.ladiesfingerremove);
        gr=(Button)findViewById(R.id.gounguraremove);
        mr=(Button)findViewById(R.id.mintremove);
        ok=(Button)findViewById(R.id.ok_button);
        cancel=(Button)findViewById(R.id.cancel);
        tr.setEnabled(false);
        pr.setEnabled(false);bor.setEnabled(false);cur.setEnabled(false);
        car.setEnabled(false);bir.setEnabled(false);dr.setEnabled(false);
        lr.setEnabled(false);gr.setEnabled(false);mr.setEnabled(false);


        ta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ta.setEnabled(false);
                tr.setEnabled(true);
                if (!vegies.contains("tomato"))
                vegies.add("tomato");
            }
        });
        pa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pa.setEnabled(false);
                pr.setEnabled(true);
                if (!vegies.contains("potato"))
                vegies.add("potato");
            }
        });
        boa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boa.setEnabled(false);
                bor.setEnabled(true);
                if (!vegies.contains("bottleguard"))
                vegies.add("bottleguard");
            }
        });
        cua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cua.setEnabled(false);
                cur.setEnabled(true);
                if (!vegies.contains("cucumber"))
                vegies.add("cucumber");
            }
        });
        caa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caa.setEnabled(false);
                car.setEnabled(true);
                if (!vegies.contains("capsicum"))
                vegies.add("capsicum");
            }
        });
        bia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bia.setEnabled(false);
                bir.setEnabled(true);
                if (!vegies.contains("bitterguard"))
                vegies.add("bitterguard");
            }
        });
        da.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                da.setEnabled(false);
                dr.setEnabled(true);
                if (!vegies.contains("drumstick"))
                vegies.add("drumstick");
            }
        });
        la.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                la.setEnabled(false);
                lr.setEnabled(true);
                if (!vegies.contains("ladiesfinger"))
                vegies.add("ladiesfinger");
            }
        });
        ga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ga.setEnabled(false);
                gr.setEnabled(true);
                if (!vegies.contains("gongura"))
                vegies.add("gongura");
            }
        });
        ma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ma.setEnabled(false);
                mr.setEnabled(true);
                if (!vegies.contains("mint"))
                vegies.add("mint");
            }
        });

        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ta.setEnabled(true);
                tr.setEnabled(false);
                vegies.remove("tomato");
            }
        });
        pr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pa.setEnabled(true);
                pr.setEnabled(false);
                vegies.remove("potato");
            }
        });
        bor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boa.setEnabled(true);
                bor.setEnabled(false);
                vegies.remove("bottleguard");
            }
        });
        cur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cua.setEnabled(true);
                cur.setEnabled(false);
                vegies.remove("cucumber");
            }
        });
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caa.setEnabled(true);
                car.setEnabled(false);
                vegies.remove("capsicum");
            }
        });
        bir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bia.setEnabled(true);
                bir.setEnabled(false);
                vegies.remove("bitterguard");
            }
        });
        dr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                da.setEnabled(true);
                dr.setEnabled(false);
                vegies.remove("drumstick");
            }
        });
        lr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                la.setEnabled(true);
                lr.setEnabled(false);
                vegies.remove("ladiesfinger");
            }
        });
        gr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ga.setEnabled(true);
                gr.setEnabled(false);
                vegies.remove("gongura");
            }
        });
        mr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ma.setEnabled(true);
                mr.setEnabled(false);
                vegies.remove("mint");
            }
        });
        Currentuserdetails("role");
        Currentuserdetails("mobilenum");

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp="";
                for (String i:vegies)
                {
                    temp+=i+",";
                }
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("vegetables",temp);
                //userMap.put("location",)
                if(rol.equals("Customer")) {
                    customers.child(mno).updateChildren(userMap);
                }
                else {
                    vendors.child(mno).updateChildren(userMap);
                }
                new SweetAlertDialog(Vegetables.this)
                        .setTitleText("Sucessfully Added")
                        .show();
                startService(new Intent(Vegetables.this,MyService.class));
            }
        });




        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(Vegetables.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("You won't be able to recover this data!")
                        .setConfirmText("Delete!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        }).setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                        .show();
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
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    System.out.println("hai"+mail1);
                    if (mail1.equals(ds.child("email").getValue(String.class))) {
                        System.out.println(mail1);
                        if (req.equals("vegetables")) {
                            veg = ds.child("vegetables").getValue(String.class);
                            rt = 1;
                            rtype = 1;
                            break;
                        }
                        if (req.equals("email")) {
                            em = ds.child("email").getValue(String.class);
                            rt = 2;
                            rtype = 1;
                            break;
                        }
                        if (req.equals("role")) {
                            rol = ds.child("role").getValue(String.class);
                            System.out.println(rol);
                            rt = 3;
                            rtype = 1;
                            break;
                        }
                        if (req.equals("mobilenum")) {
                            mno = ds.child("mobileno").getValue(String.class);
                            System.out.println(mno);
                            rt = 4;
                            rtype = 1;
                            break;
                        }
                    }
                }
            }

            @Override
            public synchronized void onCancelled(@NonNull DatabaseError error) {
            }
        });

        if (rtype == 0) {
            vuserref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        if (mail1.equals(ds.child("email").getValue())) {
                            if (req.equals("vegetables")) {
                                veg = ds.child("vegetables").getValue(String.class);
                                rt = 1;
                                rtype = 1;
                                break;
                            }

                            if (req.equals("email")) {
                                em = ds.child("email").getValue(String.class);
                                rt = 2;
                                rtype = 1;
                                break;
                            }
                            if (req.equals("role")) {
                                rol = ds.child("role").getValue(String.class);
                                rt = 3;
                                rtype = 1;
                                break;
                            }
                            if (req.equals("mobilenum")) {
                                mno = ds.child("mobileno").getValue(String.class);
                                rt = 4;
                                rtype = 1;
                                break;
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

        return "final";
    }
}