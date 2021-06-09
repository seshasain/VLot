package com.example.vlot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class Hometest extends AppCompatActivity {
    ImageView mvimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hometest);

        mvimg=findViewById(R.id.movimg);
        int[] imgarr={ R.drawable.move1,R.drawable.mov2,R.drawable.mov4};

        Handler handler=new Handler();
        Runnable r=new Runnable() {
            int i=0;
            @Override
            public void run() {
                mvimg.setImageResource(imgarr[i]);
                i++;
                if(i>=imgarr.length)
                {
                    i=0;
                }
                handler.postDelayed(this,2000);
            }
            };
            handler.postDelayed(r,2000);

            }
}

