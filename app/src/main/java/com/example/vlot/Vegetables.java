package com.example.vlot;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.os.Bundle;

import java.util.ArrayList;

public class Vegetables extends AppCompatActivity {

    Button ta,pa,boa,cua,caa,bia,da,la,ga,ma;
    Button tr,pr,bor,cur,car,bir,dr,lr,gr,mr;
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

        ta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!vegies.contains("tomato"))
                vegies.add("tomato");
            }
        });
        pa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!vegies.contains("potato"))
                vegies.add("potato");
            }
        });
        boa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!vegies.contains("bottleguard"))
                vegies.add("bottleguard");
            }
        });
        cua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!vegies.contains("cucumber"))
                vegies.add("cucumber");
            }
        });
        caa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!vegies.contains("capsicum"))
                vegies.add("capsicum");
            }
        });
        bia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!vegies.contains("bitterguard"))
                vegies.add("bitterguard");
            }
        });
        da.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!vegies.contains("drumstick"))
                vegies.add("drumstick");
            }
        });
        la.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!vegies.contains("ladiesfinger"))
                vegies.add("ladiesfinger");
            }
        });
        ga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!vegies.contains("gongura"))
                vegies.add("gongura");
            }
        });
        ma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!vegies.contains("mint"))
                vegies.add("mint");
            }
        });

        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vegies.remove("tomato");
            }
        });
        pr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vegies.remove("potato");
            }
        });
        bor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vegies.remove("bottleguard");
            }
        });
        cur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vegies.remove("cucumber");
            }
        });
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vegies.remove("capsicum");
            }
        });
        bir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vegies.remove("bitterguard");
            }
        });
        dr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vegies.remove("drumstick");
            }
        });
        lr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vegies.remove("ladiesfinger");
            }
        });
        gr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vegies.remove("gongura");
            }
        });
        mr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vegies.remove("mint");
            }
        });

    }
}