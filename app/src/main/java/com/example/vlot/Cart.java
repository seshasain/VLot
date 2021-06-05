package com.example.vlot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
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

public class Cart extends AppCompatActivity {

    TextView tom,pot,bot,cucu;
    ImageView itom,ipot,ibot,icucu;

    public String mail;
    int flag=0;
    private FirebaseDatabase database;
    private DatabaseReference cuserref,vuserref;
    private static final String cusers="customers";
    private static final String vusers="vendors";
    static  String veg;
    String custveg[];



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

        tom=findViewById(R.id.tomatoctv);
        pot=findViewById(R.id.potatoctv);
        bot=findViewById(R.id.bguardctv);
        cucu=findViewById(R.id.cucuctv);

        cuserref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    if (mail.equals(ds.child("email").getValue()))
                    {
                       veg=ds.child("vegetables").getValue(String.class);
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
                            veg=ds.child("vegetables").getValue(String.class);
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

    }

    public void displaycart(String veg)
    {
        int to=0,po=0,bg=0,cu=0,f=0;
            custveg = veg.split(",");
            for(int i=0;i<custveg.length;i++)
            {
                if(custveg[i].equals("tomato"))
                {
                    to=1;
                    f=1;
                    itom.setVisibility(View.VISIBLE);
                    tom.setVisibility(View.VISIBLE);
                }
                else if(custveg[i].equals("potato"))
                {
                    po=1;
                    f=1;
                    ipot.setVisibility(View.VISIBLE);
                    pot.setVisibility(View.VISIBLE);
                }
                else if(custveg[i].equals("bottleguard"))
                {
                    bg=1;
                    f=1;
                    ibot.setVisibility(View.VISIBLE);
                    bot.setVisibility(View.VISIBLE);
                }
                else if(custveg[i].equals("cucumber"))
                {
                    cu=1;
                    f=1;
                    icucu.setVisibility(View.VISIBLE);
                    cucu.setVisibility(View.VISIBLE);
                }
            }
            if(to==0)
            {
                itom.setVisibility(View.GONE);
                tom.setVisibility(View.GONE);
            }
            if(po==0)
            {
                ipot.setVisibility(View.GONE);
                pot.setVisibility(View.GONE);
            }
            if(bg==0)
            {
                ibot.setVisibility(View.GONE);
                bot.setVisibility(View.GONE);
            }
            if(cu==0)
            {
                icucu.setVisibility(View.GONE);
                cucu.setVisibility(View.GONE);
            }
            
        }
    }
