package com.example.vlot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Fruits extends AppCompatActivity {
    Button aa,gra,gua,oa,paa,pia,poa,wa,sa,ba;
    Button ar,grr,gur,or,par,pir,por,wr,sr,br;
    ArrayList<String> fruits = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits);
        aa=(Button)findViewById(R.id.appleadd);
        gra=(Button)findViewById(R.id.grapesadd);
        gua=(Button)findViewById(R.id.guavaadd);
        oa=(Button)findViewById(R.id.orangeadd);
        paa=(Button)findViewById(R.id.papayaadd);
        pia=(Button)findViewById(R.id.pineappleadd);
        poa=(Button)findViewById(R.id.pomogranateadd);
        wa=(Button)findViewById(R.id.watermelonadd);
        sa=(Button)findViewById(R.id.sweetcornadd);
        ba=(Button)findViewById(R.id.bananaadd);

        ar=(Button)findViewById(R.id.appleremove);
        grr=(Button)findViewById(R.id.grapesremove);
        gur=(Button)findViewById(R.id.guavaremove);
        or=(Button)findViewById(R.id.orangeremove);
        par=(Button)findViewById(R.id.papayaremove);
        pir=(Button)findViewById(R.id.pineappleremove);
        por=(Button)findViewById(R.id.pomogranateremove);
        wr=(Button)findViewById(R.id.watermelonremove);
        sr=(Button)findViewById(R.id.sweetcornremove);
        br=(Button)findViewById(R.id.bananaremove);

        aa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fruits.contains("apple"))
                    fruits.add("apple");
            }
        });
        gra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fruits.contains("grape"))
                    fruits.add("grape");
            }
        });
        gua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fruits.contains("guava"))
                    fruits.add("guava");
            }
        });
        oa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fruits.contains("orange"))
                    fruits.add("orange");
            }
        });
        paa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fruits.contains("papaya"))
                    fruits.add("papaya");
            }
        });
        pia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fruits.contains("pineapple"))
                    fruits.add("pineapple");
            }
        });
        poa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fruits.contains("pomogranate"))
                    fruits.add("pomogranate");
            }
        });
        wa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fruits.contains("watermelon"))
                    fruits.add("watermelon");
            }
        });
        sa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fruits.contains("sweetcorn"))
                    fruits.add("sweetcorn");
            }
        });
        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fruits.contains("banana"))
                    fruits.add("banana");
            }
        });


        ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fruits.remove("apple");
            }
        });
        grr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    fruits.remove("grape");
            }
        });
        gur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    fruits.remove("guava");
            }
        });
        or.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    fruits.remove("orange");
            }
        });
        par.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    fruits.remove("papaya");
            }
        });
        pir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    fruits.remove("pineapple");
            }
        });
        por.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    fruits.remove("pomogranate");
            }
        });
        wr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    fruits.remove("watermelon");
            }
        });
        sr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    fruits.remove("sweetcorn");
            }
        });
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    fruits.remove("banana");
            }
        });

    }
}