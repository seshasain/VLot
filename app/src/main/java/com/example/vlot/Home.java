package com.example.vlot;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
//package com.jarves.navigationdrawer;

public class Home extends AppCompatActivity {
    Handler handler;
    Runnable test;
    public GPSTracker gps;
    public static  String veg,em,rol,mno;
    public int rtype,rt;
    public String  mail1,cdist;
    String latitude,longitude;
    String customerproductarray[];
    double customerpresetdistance=0;
    String stopped="";
    String stoppedprevious="";
    int val=0;
    Set<List<String>> allvendorlocations = new HashSet();
    Set<List<String>> allcustomermailsforvendors = new HashSet();
    Set<List<String>> allvendorlocationsprevious = new HashSet();
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference customers=db.getReference().child("customers");
    private DatabaseReference vendors=db.getReference().child("vendors");
    private TextView pname;
    public String mail;
    ImageButton hcart;
    private FirebaseDatabase database;
    private DatabaseReference cuserref,vuserref;
    private static final String cusers="customers";
    private static final String vusers="vendors";
    int flag=0;
    //private FirebaseDatabase db=FirebaseDatabase.getInstance();
    //private DatabaseReference customers=db.getReference().child("customers");
    //private DatabaseReference vendors=db.getReference().child("vendors");
    private String ph;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ImageButton veg1,fru1,dai1;
    NavigationView navigationView;
    FirebaseAuth Auth;
    String name;
    ImageView chimg;
    private static final int PERMISSIONS_REQUEST = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Currentuserdetails("role");
        Currentuserdetails("vegetables");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mail=user.getEmail();
        hcart=findViewById(R.id.homecart);
        chimg=findViewById(R.id.img);
        veg1=findViewById(R.id.veg);

        DatabaseReference rootref = FirebaseDatabase.getInstance().getReference();
        cuserref = rootref.child(cusers);
        vuserref=rootref.child(vusers);
        if(!canGetLocation(getApplicationContext()))
        {
            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
        pname = findViewById(R.id.txt);
        database = FirebaseDatabase.getInstance();
        cuserref = database.getReference(cusers);
        vuserref= database.getReference(vusers);
        int[] imgarr={R.drawable.one, R.drawable.two,R.drawable.three,R.drawable.four};

        veg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vegint= new Intent(Home.this,Vegetables.class);
                startActivity(vegint);
            }

        });


        Handler handler=new Handler();
        Runnable r=new Runnable() {
            int i=0;
            @Override
            public void run() {
                chimg.setImageResource(imgarr[i]);
                i++;
                if(i>=imgarr.length)
                {
                    i=0;
                }
                handler.postDelayed(this,5000);
            }
        };
        handler.postDelayed(r,5000);


        cuserref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    if (mail.equals(ds.child("email").getValue()))
                    {
                        pname.setText("WELCOME\n"+ds.child("name").getValue(String.class).toUpperCase());
                        flag=1;
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Home.this, "something went wrong..", Toast.LENGTH_LONG).show();
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
                            pname.setText("WELCOME\n"+ds.child("name").getValue(String.class).toUpperCase());
                            flag = 0;

                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Home.this, "something went wrong..", Toast.LENGTH_LONG).show();
                }

            });
        }


        //fru1=(ImageButton)findViewById(R.id.fru);
        //dai1=(ImageButton)findViewById(R.id.dai);
                hcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Home.this,Cart.class);
                startActivity(intent2);
            }

        });
        /*fru1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(Home.this,Fruits.class);
                startActivity(intent3);
            }
        });
        dai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(Home.this,DairyProducts.class);
                startActivity(intent4);
            }
        });*/
        setUpToolbar();
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            finish();
        }
        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            startTrackerService();
        } else {

//If the app doesn’t currently have access to the user’s location, then request access//

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST);
        }


        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_profsettings:
                        Intent intent = new Intent(Home.this, Profile.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_home:
                        Intent homeint = new Intent(Home.this, Home.class);
                        startActivity(homeint);
                        break;
                    case R.id.nav_cart:
                        Intent homecart = new Intent(Home.this, Cart.class);
                        startActivity(homecart);
                        break;
                    case  R.id.nav_about:
                        Intent abt = new Intent(Home.this,Aboutus.class);
                        startActivity(abt);
                        break;
                    case  R.id.nav_privacypolicy:
                        Intent pp = new Intent(Home.this,Privacypolicy.class);
                        startActivity(pp);
                        break;

                    case R.id.logout:
                        FirebaseAuth.getInstance().signOut();
                        Intent intent1 = new Intent(Home.this,Login.class);
                        stopService(new Intent(Home.this,MyService.class));
                        startActivity(intent1);
                        finish();
                        break;
                    /*case  R.id.nav_location:
                        if (flag==1) {
                            Intent loc = new Intent(Home.this,AvailableVendors.class);
                            startActivity(loc);
                        }
                        else{
                            Intent loc = new Intent(Home.this, AvailableCustomers.class);
                            startActivity(loc);
                        }
                        break;*/

                }
                return false;
            }
        });


    }
    public static boolean canGetLocation(Context context) {
        return isLocationEnabled(context); // application context
    }

    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            return !TextUtils.isEmpty(locationProviders);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[]
            grantResults) {

//If the permission has been granted...//

        if (requestCode == PERMISSIONS_REQUEST && grantResults.length == 1
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

//...then start the GPS tracking service//

            startTrackerService();
        } else {

//If the user denies the permission request, then display a toast with some more information//

            Toast.makeText(this, "Please enable location services to allow GPS tracking", Toast.LENGTH_SHORT).show();
        }
    }


//Start the TrackerService//

    private void startTrackerService() {
        //startService(new Intent(this, TrackingService.class));

//Notify the user that tracking has been enabled//

        Toast.makeText(this, "GPS tracking enabled", Toast.LENGTH_SHORT).show();



//Close MainActivity//
    }
    public void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
    public synchronized String Currentuserdetails(String req)
    {
        DatabaseReference cuserref, vuserref;
        String cusers = "customers";
        String vusers = "vendors";
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mail1 = user.getEmail();

        DatabaseReference rootref = FirebaseDatabase.getInstance().getReference();
        cuserref = rootref.child(cusers);
        vuserref = rootref.child(vusers);

        cuserref.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (mail1.equals(ds.child("email").getValue())) {
                        customerpresetdistance=0;
                        cdist=ds.child("distance").getValue(String.class);
                        customerpresetdistance=Double.parseDouble(ds.child("distance").getValue(String.class));
                        //System.out.println("Calculated distance:"+customerpresetdistance);
                        if (req.equals("vegetables")) {
                            veg = ds.child("vegetables").getValue(String.class);
                            rt = 1;
                            rtype = 1;
                            break;
                        }
                        if (req.equals("email")) {
                            em = ds.child("email").getValue(String.class);
                            rt = 2;
                            rtype = 1;
                            break;
                        }
                        if (req.equals("role")) {
                            rol = ds.child("role").getValue(String.class);
                            rt = 3;
                            rtype = 1;
                            break;
                        }
                        if (req.equals("mobilenum")) {
                            mno = ds.child("mobileno").getValue(String.class);
                            rt = 4;
                            rtype = 1;
                            break;
                        }
                    }
                }
            }

            @Override
            public synchronized void onCancelled(@NonNull DatabaseError error) {
            }
        });
        if (rtype == 0) {
            vuserref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        if (mail1.equals(ds.child("email").getValue())) {
                            stopped=ds.child("stopped").getValue(String.class);
                            if (req.equals("vegetables")) {
                                veg = ds.child("vegetables").getValue(String.class);
                                rt = 1;
                                rtype = 1;
                                break;
                            }

                            if (req.equals("email")) {
                                em = ds.child("email").getValue(String.class);
                                rt = 2;
                                rtype = 1;
                                break;
                            }
                            if (req.equals("role")) {
                                rol = ds.child("role").getValue(String.class);
                                rt = 3;
                                rtype = 1;
                                break;
                            }
                            if (req.equals("mobilenum")) {
                                mno = ds.child("mobileno").getValue(String.class);
                                rt = 4;
                                rtype = 1;
                                break;
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

        return "final";
    }
}