package com.example.vlot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Map;
import java.util.HashMap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    TextView tom,pot,bot,cucu,cap,bit,drum,lad,gon,mint;
    ImageView itom,ipot,ibot,icucu,icap,ibit,idrum,ilad,igon,imint;
    Button rtom,rpot,rbot,rcucu,rcap,rbit,rdrum,rlad,rgon,rmint;

    public String mail;
    int flag=0;
    private FirebaseDatabase database;
    private DatabaseReference cuserref,vuserref;
    private String ph;
    private static final String cusers="customers";
    private static final String vusers="vendors";
    static  String veg,veg1;
    String custveg[];
    ArrayList<String> veglist = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        /*itom.setVisibility(View.GONE);
        tom.setVisibility(View.GONE);
        ipot.setVisibility(View.GONE);
        pot.setVisibility(View.GONE);
        ibot.setVisibility(View.GONE);
        bot.setVisibility(View.GONE);
        icucu.setVisibility(View.GONE);
        cucu.setVisibility(View.GONE);*/

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mail=user.getEmail();

        DatabaseReference rootref = FirebaseDatabase.getInstance().getReference();
        cuserref = rootref.child(cusers);
        vuserref=rootref.child(vusers);

        database = FirebaseDatabase.getInstance();
        cuserref = database.getReference(cusers);
        vuserref= database.getReference(vusers);

        itom=findViewById(R.id.tomato);
        ipot=findViewById(R.id.potato);
        icucu=findViewById(R.id.cucumber);
        ibot=findViewById(R.id.bottleguard);
        icap=findViewById(R.id.capsicum);
        ibit=findViewById(R.id.bitterguard);
        idrum=findViewById(R.id.drumstick);
        ilad=findViewById(R.id.ladiesfinger);
        igon=findViewById(R.id.gongura);
        imint=findViewById(R.id.mint);


        tom=findViewById(R.id.tomatoctv);
        pot=findViewById(R.id.potatoctv);
        bot=findViewById(R.id.bguardctv);
        cucu=findViewById(R.id.cucuctv);
        cap=findViewById(R.id.capsicumctv);
        bit=findViewById(R.id.bitterguardctv);
        drum=findViewById(R.id.drumstickctv);
        lad=findViewById(R.id.ladiesfingerctv);
        gon=findViewById(R.id.gonguractv);
        mint=findViewById(R.id.mintctv);

        rtom=findViewById(R.id.tomator);
        rpot=findViewById(R.id.potator);
        rbot=findViewById(R.id.bottleguardr);
        rcucu=findViewById(R.id.cucumberr);
        rcap=findViewById(R.id.capsicumr);
        rbit=findViewById(R.id.bitterguardr);
        rdrum=findViewById(R.id.drumstickr);
        rlad=findViewById(R.id.ladiesfingerr);
        rgon=findViewById(R.id.gongurar);
        rmint=findViewById(R.id.mintr);




        cuserref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    if (mail.equals(ds.child("email").getValue()))
                    {
                        ph=ds.child("mobileno").getValue(String.class);
                       veg=ds.child("vegetables").getValue(String.class);
                       veg1=ds.child("vegetables").getValue(String.class);
                        displaycart(veg);
                        flag=1;
                        break;
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Cart.this, "something went wrong..", Toast.LENGTH_LONG).show();
            }

        });

        if(flag==0)
        {
            vuserref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        if (mail.equals(ds.child("email").getValue()))
                        {
                            ph=ds.child("mobileno").getValue(String.class);
                            veg=ds.child("vegetables").getValue(String.class);
                            veg1=ds.child("vegetables").getValue(String.class);
                            displaycart(veg);
                            flag = 0;
                            break;

                        }

                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Cart.this, "something went wrong..", Toast.LENGTH_LONG).show();
                }
            });
        }
        rtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veg1=veg1.replace("tomato,","");
                veg1=veg1.replace("tomato","");
                Map<String,Object> userMpad=new HashMap<>();
                userMpad.put("vegetables",veg1);
                if(flag==0)
                {
                    vuserref.child(ph).updateChildren(userMpad);
                }
                if(flag==1)
                {
                    cuserref.child(ph).updateChildren(userMpad);
                }

            }
        });
        rpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veg1=veg1.replace("potato,","");
                veg1=veg1.replace("potato","");
                Map<String,Object> userMpad=new HashMap<>();
                userMpad.put("vegetables",veg1);
                if(flag==0)
                {
                    vuserref.child(ph).updateChildren(userMpad);
                }
                if(flag==1)
                {
                    cuserref.child(ph).updateChildren(userMpad);
                }
            }
        });
        rbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veg1=veg1.replace("bottleguard,","");
                veg1=veg1.replace("bottleguard","");
                Map<String,Object> userMpad=new HashMap<>();
                userMpad.put("vegetables",veg1);
                if(flag==0)
                {
                    vuserref.child(ph).updateChildren(userMpad);
                }
                if(flag==1)
                {
                    cuserref.child(ph).updateChildren(userMpad);
                }
            }
        });
        rcucu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veg1=veg1.replace("cucumber,","");
                veg1=veg1.replace("cucumber","");
                Map<String,Object> userMpad=new HashMap<>();
                userMpad.put("vegetables",veg1);
                if(flag==0)
                {
                    vuserref.child(ph).updateChildren(userMpad);
                }
                if(flag==1)
                {
                    cuserref.child(ph).updateChildren(userMpad);
                }
            }
        });
        rcap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veg1=veg1.replace("capsicum,","");
                veg1=veg1.replace("capsicum","");
                Map<String,Object> userMpad=new HashMap<>();
                userMpad.put("vegetables",veg1);
                if(flag==0)
                {
                    vuserref.child(ph).updateChildren(userMpad);
                }
                if(flag==1)
                {
                    cuserref.child(ph).updateChildren(userMpad);
                }
            }
        });
        rbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veg1=veg1.replace("bitterguard,","");
                veg1=veg1.replace("bitterguard","");
                Map<String,Object> userMpad=new HashMap<>();
                userMpad.put("vegetables",veg1);
                if(flag==0)
                {
                    vuserref.child(ph).updateChildren(userMpad);
                }
                if(flag==1)
                {
                    cuserref.child(ph).updateChildren(userMpad);
                }
            }
        });
        rdrum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veg1=veg1.replace("drumstick,","");
                veg1=veg1.replace("drumstick","");
                Map<String,Object> userMpad=new HashMap<>();
                userMpad.put("vegetables",veg1);
                if(flag==0)
                {
                    vuserref.child(ph).updateChildren(userMpad);
                }
                if(flag==1)
                {
                    cuserref.child(ph).updateChildren(userMpad);
                }
            }
        });
        rlad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veg1=veg1.replace("ladiesfinger,","");
                veg1=veg1.replace("ladiesfinger","");
                Map<String,Object> userMpad=new HashMap<>();
                userMpad.put("vegetables",veg1);
                if(flag==0)
                {
                    vuserref.child(ph).updateChildren(userMpad);
                }
                if(flag==1)
                {
                    cuserref.child(ph).updateChildren(userMpad);
                }
            }
        });
        rgon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veg1=veg1.replace("gongura,","");
                veg1=veg1.replace("gongura","");
                Map<String,Object> userMpad=new HashMap<>();
                userMpad.put("vegetables",veg1);
                if(flag==0)
                {
                    vuserref.child(ph).updateChildren(userMpad);
                }
                if(flag==1)
                {
                    cuserref.child(ph).updateChildren(userMpad);
                }
            }
        });
        rmint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veg1=veg1.replace("mint,","");
                veg1=veg1.replace("mint","");
                Map<String,Object> userMpad=new HashMap<>();
                userMpad.put("vegetables",veg1);
                if(flag==0)
                {
                    vuserref.child(ph).updateChildren(userMpad);
                }
                if(flag==1)
                {
                    cuserref.child(ph).updateChildren(userMpad);
                }
            }
        });

    }

    public void displaycart(String veg)
    {
        int to=0,po=0,bg=0,cu=0,ca=0,bi=0,dr=0,la=0,g=0,mi=0,f=0;
            custveg = veg.split(",");
            for(int i=0;i<custveg.length;i++)
            {
                if(custveg[i].equals("tomato"))
                {
                    to=1;
                    f=1;
                    itom.setVisibility(View.VISIBLE);
                    tom.setVisibility(View.VISIBLE);
                    rtom.setVisibility(View.VISIBLE);
                }
                else if(custveg[i].equals("potato"))
                {
                    po=1;
                    f=1;
                    ipot.setVisibility(View.VISIBLE);
                    pot.setVisibility(View.VISIBLE);
                    rpot.setVisibility(View.VISIBLE);
                }
                else if(custveg[i].equals("bottleguard"))
                {
                    bg=1;
                    f=1;
                    ibot.setVisibility(View.VISIBLE);
                    bot.setVisibility(View.VISIBLE);
                    rbot.setVisibility(View.VISIBLE);
                }
                else if(custveg[i].equals("cucumber"))
                {
                    cu=1;
                    f=1;
                    icucu.setVisibility(View.VISIBLE);
                    cucu.setVisibility(View.VISIBLE);
                    rcucu.setVisibility(View.VISIBLE);
                }
                else if(custveg[i].equals("capsicum"))
                {
                    ca=1;
                    f=1;
                    icap.setVisibility(View.VISIBLE);
                    cap.setVisibility(View.VISIBLE);
                    rcap.setVisibility(View.VISIBLE);
                }
                else if(custveg[i].equals("bitterguard"))
                {
                    bi=1;
                    f=1;
                    ibit.setVisibility(View.VISIBLE);
                    bit.setVisibility(View.VISIBLE);
                    rbit.setVisibility(View.VISIBLE);
                }
                else if(custveg[i].equals("drumstick"))
                {
                    dr=1;
                    f=1;
                    idrum.setVisibility(View.VISIBLE);
                    drum.setVisibility(View.VISIBLE);
                    rdrum.setVisibility(View.VISIBLE);
                }
                else if(custveg[i].equals("ladiesfinger"))
                {
                    la=1;
                    f=1;
                    ilad.setVisibility(View.VISIBLE);
                    lad.setVisibility(View.VISIBLE);
                    rlad.setVisibility(View.VISIBLE);
                }
                else if(custveg[i].equals("gongura"))
                {
                    g=1;
                    f=1;
                    igon.setVisibility(View.VISIBLE);
                    gon.setVisibility(View.VISIBLE);
                    rgon.setVisibility(View.VISIBLE);
                }
                else if(custveg[i].equals("mint"))
                {
                    mi=1;
                    f=1;
                    imint.setVisibility(View.VISIBLE);
                    mint.setVisibility(View.VISIBLE);
                    rmint.setVisibility(View.VISIBLE);
                }
            }
            if(to==0)
            {
                itom.setVisibility(View.GONE);
                tom.setVisibility(View.GONE);
                rtom.setVisibility(View.GONE);
            }
            if(po==0)
            {
                ipot.setVisibility(View.GONE);
                pot.setVisibility(View.GONE);
                rpot.setVisibility(View.GONE);
            }
            if(bg==0)
            {
                ibot.setVisibility(View.GONE);
                bot.setVisibility(View.GONE);
                rbot.setVisibility(View.GONE);
            }
            if(cu==0)
            {
                icucu.setVisibility(View.GONE);
                cucu.setVisibility(View.GONE);
                rcucu.setVisibility(View.GONE);
            }
        if(ca==0)
        {
            icap.setVisibility(View.GONE);
            cap.setVisibility(View.GONE);
            rcap.setVisibility(View.GONE);
        }
        if(bi==0)
        {
            ibit.setVisibility(View.GONE);
            bit.setVisibility(View.GONE);
            rbit.setVisibility(View.GONE);
        }
        if(dr==0)
        {
            idrum.setVisibility(View.GONE);
            drum.setVisibility(View.GONE);
            rdrum.setVisibility(View.GONE);
        }
        if(la==0)
        {
            ilad.setVisibility(View.GONE);
            lad.setVisibility(View.GONE);
            rlad.setVisibility(View.GONE);
        }
        if(g==0)
        {
            igon.setVisibility(View.GONE);
            gon.setVisibility(View.GONE);
            rgon.setVisibility(View.GONE);
        }
        if(mi==0)
        {
            imint.setVisibility(View.GONE);
            mint.setVisibility(View.GONE);
            rmint.setVisibility(View.GONE);
        }
        }
    }
