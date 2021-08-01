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
    TextView ve6,ve7,ve8,ve9,ve10,vec6,vec7,vec8,vec9,vec10;
    Button v1l,v2l,v3l,v4l,v5l;Button v6l,v7l,v8l,v9l,v10l;
    public static String v1lat,v1long,v1email,c1email,v1cart,v2lat,v2long,v2email,c2email,v2cart,v3lat,v3long,v3email;
    public static String c3email,v3cart,v4lat,v4long,v4email,c4email,v4cart,v5lat,v5long,v5email,c5email,v5cart;
    public static String v6lat,v6long,v6email,c6email,v6cart,v7lat,v7long,v7email,c7email,v7cart,v8lat,v8long,v8email;
    public static String c8email,v8cart,v9lat,v9long,v9email,c9email,v9cart,v10lat,v10long,v10email,c10email,v10cart;
    public static int v1f=0,v2f=0,v3f=0,v4f=0,v5f=0;
    public static int v6f=0,v7f=0,v8f=0,v9f=0,v10f=0;
    public static int count = 1;
    public static String mno1,mno2,mno3,mno4,mno5,n1,n2,n3,n4,n5;
    public static String mno6,mno7,mno8,mno9,mno10,n6,n7,n8,n9,n10;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference customers=db.getReference().child("customers");
    private DatabaseReference vendors=db.getReference().child("vendors");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_customers);
        ve1 = findViewById(R.id.v1);
        ve2 = findViewById(R.id.v2);
        ve3 = findViewById(R.id.v3);
        ve4 = findViewById(R.id.v4);
        ve5 = findViewById(R.id.v5);
        ve6 = findViewById(R.id.v6);
        ve7 = findViewById(R.id.v7);
        ve8 = findViewById(R.id.v8);
        ve9 = findViewById(R.id.v9);
        ve10 = findViewById(R.id.v10);


        vec1 = findViewById(R.id.vc1);
        vec2 = findViewById(R.id.vc2);
        vec3 = findViewById(R.id.vc3);
        vec4 = findViewById(R.id.vc4);
        vec5 = findViewById(R.id.vc5);
        vec6 = findViewById(R.id.vc6);
        vec7 = findViewById(R.id.vc7);
        vec8 = findViewById(R.id.vc8);
        vec9 = findViewById(R.id.vc9);
        vec10 = findViewById(R.id.vc10);


        v1l = findViewById(R.id.v1locate);
        v2l = findViewById(R.id.v2locate);
        v3l = findViewById(R.id.v3locate);
        v4l = findViewById(R.id.v4locate);
        v5l = findViewById(R.id.v5locate);
        v6l = findViewById(R.id.v6locate);
        v7l = findViewById(R.id.v7locate);
        v8l = findViewById(R.id.v8locate);
        v9l = findViewById(R.id.v9locate);
        v10l = findViewById(R.id.v10locate);


        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("list");
        Set<List<String>> receivedvalues = (Set<List<String>>) args.getSerializable("ARRAYLIST");
        for (List<String> x : receivedvalues) {
            System.out.println("traversing: "+x.toString());
            if(count==1)
            {
                if(!((n1!=null && n1.equals(x.get(2)))||(n2!=null && n2.equals(x.get(2)))||(n3!=null && n3.equals(x.get(2)))||(n4!=null && n4.equals(x.get(2)))||(n5!=null && n5.equals(x.get(2)))||(n6!=null && n6.equals(x.get(2)))||(n7!=null &&n7.equals(x.get(2)))||(n8!=null && n8.equals(x.get(2))) ||(n9!=null && n9.equals(x.get(2)) )|| (n10!=null && n10.equals(x.get(2))))) {
                    v1lat = x.get(0);
                    v1long = x.get(1);
                    n1 = x.get(2);
                    v1cart = x.get(3);
                    count++;
                }
            }
            if(count==2)
            {
                if(!((n1!=null && n1.equals(x.get(2)))||(n2!=null && n2.equals(x.get(2)))||(n3!=null && n3.equals(x.get(2)))||(n4!=null && n4.equals(x.get(2)))||(n5!=null && n5.equals(x.get(2)))||(n6!=null && n6.equals(x.get(2)))||(n7!=null &&n7.equals(x.get(2)))||(n8!=null && n8.equals(x.get(2))) ||(n9!=null && n9.equals(x.get(2)) )|| (n10!=null && n10.equals(x.get(2))))) {
                    v2lat = x.get(0);
                    v2long = x.get(1);
                    n2 = x.get(2);
                    v2cart = x.get(3);
                    count++;
                }
            }
            if(count==3)
            {
                if(!((n1!=null && n1.equals(x.get(2)))||(n2!=null && n2.equals(x.get(2)))||(n3!=null && n3.equals(x.get(2)))||(n4!=null && n4.equals(x.get(2)))||(n5!=null && n5.equals(x.get(2)))||(n6!=null && n6.equals(x.get(2)))||(n7!=null &&n7.equals(x.get(2)))||(n8!=null && n8.equals(x.get(2))) ||(n9!=null && n9.equals(x.get(2)) )|| (n10!=null && n10.equals(x.get(2))))) {
                    v3lat = x.get(0);
                    v3long = x.get(1);
                    n3 = x.get(2);
                    v3cart = x.get(3);
                    count++;
                }
            }
            if(count==4)
            {
                if(!((n1!=null && n1.equals(x.get(2)))||(n2!=null && n2.equals(x.get(2)))||(n3!=null && n3.equals(x.get(2)))||(n4!=null && n4.equals(x.get(2)))||(n5!=null && n5.equals(x.get(2)))||(n6!=null && n6.equals(x.get(2)))||(n7!=null &&n7.equals(x.get(2)))||(n8!=null && n8.equals(x.get(2))) ||(n9!=null && n9.equals(x.get(2)) )|| (n10!=null && n10.equals(x.get(2))))) {
                    v4lat = x.get(0);
                    v4long = x.get(1);
                    n4 = x.get(2);
                    v4cart = x.get(3);
                    count++;
                }
            }
            if(count==5)
            {
                if(!((n1!=null && n1.equals(x.get(2)))||(n2!=null && n2.equals(x.get(2)))||(n3!=null && n3.equals(x.get(2)))||(n4!=null && n4.equals(x.get(2)))||(n5!=null && n5.equals(x.get(2)))||(n6!=null && n6.equals(x.get(2)))||(n7!=null &&n7.equals(x.get(2)))||(n8!=null && n8.equals(x.get(2))) ||(n9!=null && n9.equals(x.get(2)) )|| (n10!=null && n10.equals(x.get(2))))) {
                    v5lat = x.get(0);
                    v5long = x.get(1);
                    n5 = x.get(2);
                    v5cart = x.get(3);
                    count++;
                }
            }
            if(count==6)
            {
                if(!((n1!=null && n1.equals(x.get(2)))||(n2!=null && n2.equals(x.get(2)))||(n3!=null && n3.equals(x.get(2)))||(n4!=null && n4.equals(x.get(2)))||(n5!=null && n5.equals(x.get(2)))||(n6!=null && n6.equals(x.get(2)))||(n7!=null &&n7.equals(x.get(2)))||(n8!=null && n8.equals(x.get(2))) ||(n9!=null && n9.equals(x.get(2)) )|| (n10!=null && n10.equals(x.get(2))))) {
                    v6lat = x.get(0);
                    v6long = x.get(1);
                    n6 = x.get(2);
                    v6cart = x.get(3);
                    count++;
                }
            }
            if(count==7)
            {
                if(!((n1!=null && n1.equals(x.get(2)))||(n2!=null && n2.equals(x.get(2)))||(n3!=null && n3.equals(x.get(2)))||(n4!=null && n4.equals(x.get(2)))||(n5!=null && n5.equals(x.get(2)))||(n6!=null && n6.equals(x.get(2)))||(n7!=null &&n7.equals(x.get(2)))||(n8!=null && n8.equals(x.get(2))) ||(n9!=null && n9.equals(x.get(2)) )|| (n10!=null && n10.equals(x.get(2))))) {
                    v7lat = x.get(0);
                    v7long = x.get(1);
                    n7 = x.get(2);
                    v7cart = x.get(3);
                    count++;
                }
            }
            if(count==8)
            {
                if(!((n1!=null && n1.equals(x.get(2)))||(n2!=null && n2.equals(x.get(2)))||(n3!=null && n3.equals(x.get(2)))||(n4!=null && n4.equals(x.get(2)))||(n5!=null && n5.equals(x.get(2)))||(n6!=null && n6.equals(x.get(2)))||(n7!=null &&n7.equals(x.get(2)))||(n8!=null && n8.equals(x.get(2))) ||(n9!=null && n9.equals(x.get(2)) )|| (n10!=null && n10.equals(x.get(2))))) {
                    v8lat = x.get(0);
                    v8long = x.get(1);
                    n8 = x.get(2);
                    v8cart = x.get(3);
                    count++;
                }
            }
            if(count==9)
            {
                if(!((n1!=null && n1.equals(x.get(2)))||(n2!=null && n2.equals(x.get(2)))||(n3!=null && n3.equals(x.get(2)))||(n4!=null && n4.equals(x.get(2)))||(n5!=null && n5.equals(x.get(2)))||(n6!=null && n6.equals(x.get(2)))||(n7!=null &&n7.equals(x.get(2)))||(n8!=null && n8.equals(x.get(2))) ||(n9!=null && n9.equals(x.get(2)) )|| (n10!=null && n10.equals(x.get(2))))) {
                    v9lat = x.get(0);
                    v9long = x.get(1);
                    n9 = x.get(2);
                    v9cart = x.get(3);
                    count++;
                }
            }
            if(count==10)
            {
                if(!((n1!=null && n1.equals(x.get(2)))||(n2!=null && n2.equals(x.get(2)))||(n3!=null && n3.equals(x.get(2)))||(n4!=null && n4.equals(x.get(2)))||(n5!=null && n5.equals(x.get(2)))||(n6!=null && n6.equals(x.get(2)))||(n7!=null &&n7.equals(x.get(2)))||(n8!=null && n8.equals(x.get(2))) ||(n9!=null && n9.equals(x.get(2)) )|| (n10!=null && n10.equals(x.get(2))))) {
                    v10lat = x.get(0);
                    v10long = x.get(1);
                    n10 = x.get(2);
                    v10cart = x.get(3);
                    count++;
                }
            }
        }
        if (count>1 && n1!=null)
        {
            ve1.setVisibility(View.VISIBLE);
            vec1.setVisibility(View.VISIBLE);
            ve1.setText(n1);
            vec1.setText(v1cart);
            v1l.setVisibility(View.VISIBLE);
            v1f=1;
        }
        if (count>2 && n2!=null)
        {
            ve2.setVisibility(View.VISIBLE);
            vec2.setVisibility(View.VISIBLE);
            ve2.setText(n2);
            vec2.setText(v2cart);
            v2l.setVisibility(View.VISIBLE);
            v2f=1;
        }
        if (count>3 && n3!=null)
        {
            ve3.setVisibility(View.VISIBLE);
            vec3.setVisibility(View.VISIBLE);
            ve3.setText(n3);
            vec3.setText(v3cart);
            v3l.setVisibility(View.VISIBLE);
            v3f=1;
        }
        if (count>4 && n4!=null)
        {
            ve4.setVisibility(View.VISIBLE);
            vec4.setVisibility(View.VISIBLE);
            ve4.setText(n4);
            vec4.setText(v4cart);
            v4l.setVisibility(View.VISIBLE);
            v4f=1;
        }
        if (count>5 && n5!=null)
        {
            ve5.setVisibility(View.VISIBLE);
            vec5.setVisibility(View.VISIBLE);
            ve5.setText(n5);
            vec5.setText(v5cart);
            v5l.setVisibility(View.VISIBLE);
            v5f=1;
        }

        if (count>6 && n6!=null)
        {
            ve6.setVisibility(View.VISIBLE);
            vec6.setVisibility(View.VISIBLE);
            ve6.setText(n6);
            vec6.setText(v6cart);
            v6l.setVisibility(View.VISIBLE);
            v6f=1;
        }
        if (count>7 && n7!=null)
        {
            ve7.setVisibility(View.VISIBLE);
            vec7.setVisibility(View.VISIBLE);
            ve7.setText(n7);
            vec7.setText(v7cart);
            v7l.setVisibility(View.VISIBLE);
            v7f=1;
        }
        if (count>8 && n8!=null)
        {
            ve8.setVisibility(View.VISIBLE);
            vec8.setVisibility(View.VISIBLE);
            ve8.setText(n8);
            vec8.setText(v8cart);
            v8l.setVisibility(View.VISIBLE);
            v8f=1;
        }
        if (count>9 && n9!=null)
        {
            ve9.setVisibility(View.VISIBLE);
            vec9.setVisibility(View.VISIBLE);
            ve9.setText(n9);
            vec9.setText(v9cart);
            v9l.setVisibility(View.VISIBLE);
            v9f=1;
        }
        if (count>10 && n10!=null)
        {
            ve10.setVisibility(View.VISIBLE);
            vec10.setVisibility(View.VISIBLE);
            ve10.setText(n10);
            vec10.setText(v10cart);
            v10l.setVisibility(View.VISIBLE);
            v10f=1;
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
        if(v6f==0)
        {
            ve6.setVisibility(View.GONE);
            vec6.setVisibility(View.GONE);
            v6l.setVisibility(View.GONE);
        }
        if(v7f==0)
        {
            ve7.setVisibility(View.GONE);
            vec7.setVisibility(View.GONE);
            v7l.setVisibility(View.GONE);
        }
        if(v8f==0)
        {
            ve8.setVisibility(View.GONE);
            vec8.setVisibility(View.GONE);
            v8l.setVisibility(View.GONE);
        }
        if(v9f==0)
        {
            ve9.setVisibility(View.GONE);
            vec9.setVisibility(View.GONE);
            v9l.setVisibility(View.GONE);
        }
        if(v10f==0)
        {
            ve10.setVisibility(View.GONE);
            vec10.setVisibility(View.GONE);
            v10l.setVisibility(View.GONE);
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

        v6l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?q=" + v6lat + "," + v6long));
                startActivity(intent6);
            }
        });

        v7l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent7 = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?q=" + v7lat + "," + v7long));
                startActivity(intent7);
            }
        });

        v8l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent8 = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?q=" + v8lat + "," + v8long));
                startActivity(intent8);
            }
        });

        v9l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent9 = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?q=" + v9lat + "," + v9long));
                startActivity(intent9);
            }
        });

        v10l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent10 = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?q=" + v10lat + "," + v10long));
                startActivity(intent10);
            }
        });
    }
}