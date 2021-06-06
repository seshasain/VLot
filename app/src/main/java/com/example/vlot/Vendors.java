package com.example.vlot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Vendors extends AppCompatActivity {

    TextView ve1,ve2,ve3,vec1,vec2,vec3;
    Button v1l,v2l,v3l,v1s,v2s,v3s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendors);

        ve1=findViewById(R.id.v1);
        ve2=findViewById(R.id.v2);
        ve3=findViewById(R.id.v3);
        vec1=findViewById(R.id.vc1);
        vec2=findViewById(R.id.vc2);
        vec3=findViewById(R.id.vc3);
        v1l=findViewById(R.id.v1locate);
        v2l=findViewById(R.id.v2locate);
        v3l=findViewById(R.id.v3locate);
        v1s=findViewById(R.id.v1stop);
        v2s=findViewById(R.id.v2stop);
        v3s=findViewById(R.id.v3stop);







    }

}