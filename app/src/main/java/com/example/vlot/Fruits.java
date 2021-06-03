package com.example.vlot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fruits extends AppCompatActivity {
    Button aa,gra,gua,oa,paa,pia,poa,wa,sa,ba;
    Button ar,grr,gur,or,par,pir,por,wr,sr,br;
    ArrayList<String> fruits = new ArrayList<>();

    private FirebaseDatabase database;
    private DatabaseReference cuserref,vuserref;
    private static final String cusers="customers";
    private static final String vusers="vendors";
    String mail1;
    public static String cveg;

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

        ar.setEnabled(false);
        grr.setEnabled(false);gur.setEnabled(false);or.setEnabled(false);
        par.setEnabled(false);pir.setEnabled(false);por.setEnabled(false);
        wr.setEnabled(false);sr.setEnabled(false);br.setEnabled(false);



        aa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aa.setEnabled(false);
                ar.setEnabled(true);
                if(!fruits.contains("apple"))
                    fruits.add("apple");
            }
        });
        gra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gra.setEnabled(false);
                grr.setEnabled(true);
                if(!fruits.contains("grape"))
                    fruits.add("grape");
            }
        });
        gua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gua.setEnabled(false);
                gur.setEnabled(true);
                if(!fruits.contains("guava"))
                    fruits.add("guava");
            }
        });
        oa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oa.setEnabled(false);
                or.setEnabled(true);
                if(!fruits.contains("orange"))
                    fruits.add("orange");
            }
        });
        paa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paa.setEnabled(false);
                par.setEnabled(true);
                if(!fruits.contains("papaya"))
                    fruits.add("papaya");
            }
        });
        pia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pia.setEnabled(false);
                pir.setEnabled(true);
                if(!fruits.contains("pineapple"))
                    fruits.add("pineapple");
            }
        });
        poa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poa.setEnabled(false);
                por.setEnabled(true);
                if(!fruits.contains("pomogranate"))
                    fruits.add("pomogranate");
            }
        });
        wa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wa.setEnabled(false);
                wr.setEnabled(true);
                if(!fruits.contains("watermelon"))
                    fruits.add("watermelon");
            }
        });
        sa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sa.setEnabled(false);
                sr.setEnabled(true);
                if(!fruits.contains("sweetcorn"))
                    fruits.add("sweetcorn");
            }
        });
        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ba.setEnabled(false);
                br.setEnabled(true);
                if(!fruits.contains("banana"))
                    fruits.add("banana");
            }
        });


        ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aa.setEnabled(true);
                ar.setEnabled(false);
                fruits.remove("apple");
            }
        });
        grr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gra.setEnabled(true);
                grr.setEnabled(false);
                fruits.remove("grape");
            }
        });
        gur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gua.setEnabled(true);
                gur.setEnabled(false);
                fruits.remove("guava");
            }
        });
        or.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oa.setEnabled(true);
                or.setEnabled(false);
                fruits.remove("orange");
            }
        });
        par.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paa.setEnabled(true);
                par.setEnabled(false);
                fruits.remove("papaya");
            }
        });
        pir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pia.setEnabled(true);
                pir.setEnabled(false);
                fruits.remove("pineapple");
            }
        });
        por.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poa.setEnabled(true);
                por.setEnabled(false);
                fruits.remove("pomogranate");
            }
        });
        wr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wa.setEnabled(true);
                wr.setEnabled(false);
                fruits.remove("watermelon");
            }
        });
        sr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sa.setEnabled(true);
                sr.setEnabled(false);
                fruits.remove("sweetcorn");
            }
        });
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ba.setEnabled(true);
                br.setEnabled(false);
                fruits.remove("banana");
            }
        });
        System.out.println(checkVendors());
    }

    public String checkVendors()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mail1=user.getEmail();

        DatabaseReference rootref = FirebaseDatabase.getInstance().getReference();
        cuserref = rootref.child(cusers);
        vuserref=rootref.child(vusers);

        database = FirebaseDatabase.getInstance();
        cuserref = database.getReference(cusers);
        vuserref= database.getReference(vusers);

        vuserref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    if (mail1.equals(ds.child("email").getValue()))
                    {
                        cveg=ds.child("vegetables").getValue(String.class);
                        Toast.makeText(Fruits.this,cveg ,Toast.LENGTH_LONG).show();
                        System.out.println("hi"+cveg);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return cveg;
    }
}