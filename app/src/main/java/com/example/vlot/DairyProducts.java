package com.example.vlot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class DairyProducts extends AppCompatActivity {
    Button ma,pa,ba,ca,ia;
    Button mr,pr,br,cr,ir;
    ArrayList<String> dairy = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dairy_products);
        ma=(Button)findViewById(R.id.milkadd);
        pa=(Button)findViewById(R.id.panneradd);
        ba=(Button)findViewById(R.id.butteradd);
        ca=(Button)findViewById(R.id.curdadd);
        ia=(Button)findViewById(R.id.icecreamadd);

        mr=(Button)findViewById(R.id.milkremove);
        pr=(Button)findViewById(R.id.pannerremove);
        br=(Button)findViewById(R.id.butterremove);
        cr=(Button)findViewById(R.id.curdremove);
        ir=(Button)findViewById(R.id.icecreamremove);

        ma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dairy.contains("milk"))
                    dairy.add("milk");
            }
        });
        pa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dairy.contains("paneer"))
                    dairy.add("paneer");
            }
        });
        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dairy.contains("butter"))
                    dairy.add("butter");
            }
        });
        ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dairy.contains("curd"))
                    dairy.add("curd");
            }
        });
        ia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dairy.contains("icecream"))
                    dairy.add("icecream");
            }
        });



        mr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dairy.remove("milk");
            }
        });
        pr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dairy.remove("paneer");
            }
        });
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dairy.remove("butter");
            }
        });
        cr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dairy.remove("curd");
            }
        });
        ir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dairy.remove("icecream");
            }
        });
    }
}