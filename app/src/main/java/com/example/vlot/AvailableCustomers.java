package com.example.vlot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AvailableCustomers extends AppCompatActivity {

    TextView ve1,ve2,ve3,ve4,ve5,vec1,vec2,vec3,vec4,vec5;
    Button v1l,v2l,v3l,v4l,v5l;
    public static String v1lat,v1long,v1email,c1email,v1cart,v2lat,v2long,v2email,c2email,v2cart,v3lat,v3long,v3email;
    public static String c3email,v3cart,v4lat,v4long,v4email,c4email,v4cart,v5lat,v5long,v5email,c5email,v5cart;
    public static int v1f=0,v2f=0,v3f=0,v4f=0,v5f=0;
    public static int count = 1;
    public static String mno1,mno2,mno3,mno4,mno5,n1,n2,n3,n4,n5;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference customers=db.getReference().child("customers");
    private DatabaseReference vendors=db.getReference().child("vendors");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_vendors);

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

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("list");
        Set<List<String>> receivedvalues = (Set<List<String>>) args.getSerializable("ARRAYLIST");
        System.out.println("FFFFF"+receivedvalues.toString());
        for (List<String> x : receivedvalues) {
            if(count==1)
            {
                v1lat=x.get(0);
                v1long=x.get(1);
                v1email=x.get(2);
                c1email=x.get(3);
                v1cart=x.get(4);
                n1=x.get(5);
                mno1=x.get(6);
            }
            if(count==2)
            {
                v2lat=x.get(0);
                v2long=x.get(1);
                v2email=x.get(2);
                c2email=x.get(3);
                v2cart=x.get(4);
                n2=x.get(5);
                mno2=x.get(6);
            }
            if(count==3)
            {
                v3lat=x.get(0);
                v3long=x.get(1);
                v3email=x.get(2);
                c3email=x.get(3);
                v3cart=x.get(4);
                n3=x.get(5);
                mno3=x.get(6);
            }
            if(count==4)
            {
                v4lat=x.get(0);
                v4long=x.get(1);
                v4email=x.get(2);
                c4email=x.get(3);
                v4cart=x.get(4);
                n4=x.get(5);
                mno4=x.get(6);
            }
            if(count==5)
            {
                v5lat=x.get(0);
                v5long=x.get(1);
                v5email=x.get(2);
                c5email=x.get(3);
                v5cart=x.get(4);
                n5=x.get(5);
                mno5=x.get(6);
            }
            count++;
        }
        System.out.println("Count:"+count+" v1lat"+v1lat);
        if (count>1)
        {
            ve1.setVisibility(View.VISIBLE);
            vec1.setVisibility(View.VISIBLE);
            ve1.setText(n1);
            vec1.setText(v1cart);
            v1l.setVisibility(View.VISIBLE);
            v1f=1;
        }
        if (count>2)
        {
            ve2.setVisibility(View.VISIBLE);
            vec2.setVisibility(View.VISIBLE);
            ve2.setText(n2);
            vec2.setText(v2cart);
            v2l.setVisibility(View.VISIBLE);
            v2f=1;
        }
        if (count>3)
        {
            ve3.setVisibility(View.VISIBLE);
            vec3.setVisibility(View.VISIBLE);
            ve3.setText(n3);
            vec3.setText(v3cart);
            v3l.setVisibility(View.VISIBLE);
            v3f=1;
        }
        if (count>4)
        {
            ve4.setVisibility(View.VISIBLE);
            vec4.setVisibility(View.VISIBLE);
            ve4.setText(n4);
            vec4.setText(v4cart);
            v4l.setVisibility(View.VISIBLE);
            v4f=1;
        }
        if (count>5)
        {
            ve5.setVisibility(View.VISIBLE);
            vec5.setVisibility(View.VISIBLE);
            ve5.setText(n5);
            vec5.setText(v5cart);
            v5l.setVisibility(View.VISIBLE);
            v5f=1;
        }
        if(v1f==0)
        {
            ve1.setVisibility(View.GONE);
            vec1.setVisibility(View.GONE);
            v1l.setVisibility(View.GONE);
        }
        if(v2f==0)
        {
            ve2.setVisibility(View.GONE);
            vec2.setVisibility(View.GONE);
            v2l.setVisibility(View.GONE);
        }
        if(v3f==0)
        {
            ve3.setVisibility(View.GONE);
            vec3.setVisibility(View.GONE);
            v3l.setVisibility(View.GONE);
        }
        if(v4f==0)
        {
            ve4.setVisibility(View.GONE);
            vec4.setVisibility(View.GONE);
            v4l.setVisibility(View.GONE);
        }
        if(v5f==0)
        {
            ve5.setVisibility(View.GONE);
            vec5.setVisibility(View.GONE);
            v5l.setVisibility(View.GONE);
        }

        v1l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?q=" + v1lat + "," + v1long));
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


        System.out.println("mno1"+mno1+"email:"+c1email);
        System.out.println("mno2"+mno1);
        System.out.println("mno3"+mno1);
        System.out.println("mno4"+mno1);
        System.out.println("mno5"+mno1);
    }
}