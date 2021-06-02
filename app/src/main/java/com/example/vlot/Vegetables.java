package com.example.vlot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Vegetables extends AppCompatActivity {

    Button ta,pa,boa,cua,caa,bia,da,la,ga,ma;
    Button tr,pr,bor,cur,car,bir,dr,lr,gr,mr;
    Button ok,cancel;
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

                customers.child("8309734591").updateChildren(userMap);
            }
        });
    }
}