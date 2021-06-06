package com.example.vlot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Vendors extends AppCompatActivity {

    TextView ve1,ve2,ve3,ve4,ve5,vec1,vec2,vec3,vec4,vec5;
    Button v1l,v2l,v3l,v4l,v5l,v1s,v2s,v3s,v4s,v5s;
    public String v1lat,v1long,v1email,c1email,v1cart,v2lat,v2long,v2email,c2email,v2cart,v3lat,v3long,v3email;
    public String c3email,v3cart,v4lat,v4long,v4email,c4email,v4cart,v5lat,v5long,v5email,c5email,v5cart;
    public int v1f=0,v2f=0,v3f=0,v4f=0,v5f=0;
    public int count = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendors);
        ve1 = findViewById(R.id.v1);
        ve2 = findViewById(R.id.v2);
        ve3 = findViewById(R.id.v3);
        ve4 = findViewById(R.id.v4);
        ve5 = findViewById(R.id.v5);

        vec1 = findViewById(R.id.vc1);
        vec2 = findViewById(R.id.vc2);
        vec3 = findViewById(R.id.vc3);
        vec4 = findViewById(R.id.vc4);
        vec5 = findViewById(R.id.vc5);

        v1l = findViewById(R.id.v1locate);
        v2l = findViewById(R.id.v2locate);
        v3l = findViewById(R.id.v3locate);
        v4l = findViewById(R.id.v4locate);
        v5l = findViewById(R.id.v5locate);

        v1s = findViewById(R.id.v1stop);
        v2s = findViewById(R.id.v2stop);
        v3s = findViewById(R.id.v3stop);
        v4s = findViewById(R.id.v4stop);
        v5s = findViewById(R.id.v5stop);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("list");
        Set<List<String>> receivedvalues = (Set<List<String>>) args.getSerializable("ARRAYLIST");
        Toast.makeText(Vendors.this, receivedvalues.toString(), Toast.LENGTH_LONG);

        for (List<String> x : receivedvalues) {
             if(count==1)
             {
                 v1lat=x.get(0);
                 v1long=x.get(1);
                 v1email=x.get(2);
                 c1email=x.get(3);
                 v1cart=x.get(4);
                 count+=1;
             }
            if(count==2)
            {
                v2lat=x.get(0);
                v2long=x.get(1);
                v2email=x.get(2);
                c2email=x.get(3);
                v2cart=x.get(4);
                count+=1;
            }
            if(count==3)
            {
                v3lat=x.get(0);
                v3long=x.get(1);
                v3email=x.get(2);
                c3email=x.get(3);
                v3cart=x.get(4);
                count+=1;
            }
            if(count==4)
            {
                v4lat=x.get(0);
                v4long=x.get(1);
                v4email=x.get(2);
                c4email=x.get(3);
                v4cart=x.get(4);
                count+=1;
            }
            if(count==5)
            {
                v5lat=x.get(0);
                v5long=x.get(1);
                v5email=x.get(2);
                c5email=x.get(3);
                v5cart=x.get(4);
                count+=1;
            }
        }
        if (count<=1)
        {
            ve1.setVisibility(View.VISIBLE);
            vec1.setVisibility(View.VISIBLE);
            ve1.setText(v1email);
            ve1.setText(v1cart);
            v1l.setVisibility(View.VISIBLE);
            v1s.setVisibility(View.VISIBLE);
            v1f=1;
        }
        if (count<=2)
        {
            ve2.setVisibility(View.VISIBLE);
            vec2.setVisibility(View.VISIBLE);
            ve2.setText(v2email);
            ve2.setText(v2cart);
            v2l.setVisibility(View.VISIBLE);
            v2s.setVisibility(View.VISIBLE);
            v2f=1;
        }
        if (count<=3)
        {
            ve3.setVisibility(View.VISIBLE);
            vec3.setVisibility(View.VISIBLE);
            ve3.setText(v3email);
            ve3.setText(v3cart);
            v3l.setVisibility(View.VISIBLE);
            v3s.setVisibility(View.VISIBLE);
            v3f=1;
        }
        if (count<=4)
        {
            ve4.setVisibility(View.VISIBLE);
            vec4.setVisibility(View.VISIBLE);
            ve4.setText(v4email);
            ve4.setText(v4cart);
            v4l.setVisibility(View.VISIBLE);
            v4s.setVisibility(View.VISIBLE);
            v4f=1;
        }
        if (count<=5)
        {
            ve5.setVisibility(View.VISIBLE);
            vec5.setVisibility(View.VISIBLE);
            ve5.setText(v5email);
            ve5.setText(v5cart);
            v5l.setVisibility(View.VISIBLE);
            v5s.setVisibility(View.VISIBLE);
            v5f=1;
        }
         if(v1f==0)
         {
             ve1.setVisibility(View.GONE);
             vec1.setVisibility(View.GONE);
             v1l.setVisibility(View.GONE);
             v1s.setVisibility(View.GONE);
         }
        if(v2f==0)
        {
            ve2.setVisibility(View.GONE);
            vec2.setVisibility(View.GONE);
            v2l.setVisibility(View.GONE);
            v2s.setVisibility(View.GONE);
        }
        if(v3f==0)
        {
            ve3.setVisibility(View.GONE);
            vec3.setVisibility(View.GONE);
            v3l.setVisibility(View.GONE);
            v3s.setVisibility(View.GONE);
        }
        if(v4f==0)
        {
            ve4.setVisibility(View.GONE);
            vec4.setVisibility(View.GONE);
            v4l.setVisibility(View.GONE);
            v4s.setVisibility(View.GONE);
        }
        if(v5f==0)
        {
            ve5.setVisibility(View.GONE);
            vec5.setVisibility(View.GONE);
            v5l.setVisibility(View.GONE);
            v5s.setVisibility(View.GONE);
        }

        v1l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?q=" + v1lat + "," + v1long));
                startActivity(intent1);
            }
        });

        v2l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?q=" + v2lat + "," + v2long));
                startActivity(intent2);
            }
        });

        v3l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?q=" + v3lat + "," + v3long));
                startActivity(intent3);
            }
        });

        v4l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?q=" + v4lat + "," + v4long));
                startActivity(intent4);
            }
        });

        v5l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?q=" + v5lat + "," + v5long));
                startActivity(intent5);
            }
        });

        v1s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        v2s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        v3s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        v4s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        v5s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });





    }

}