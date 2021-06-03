package com.example.vlot;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MyService extends Service{
    Handler handler;
    Runnable test;
    public GPSTracker gps;
    public static  String veg,em,rol,mno;
    public int rtype,rt;
    public String mail1;
    String latitude,longitude;

    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference customers=db.getReference().child("customers");
    private DatabaseReference vendors=db.getReference().child("vendors");
    public MyService() {
        handler = new Handler();
        test = new Runnable(){
            @Override
            synchronized public void run() {
                gps = new GPSTracker(MyService.this);
                if (gps.getIsGPSTrackingEnabled())
                {
                    latitude = String.valueOf(gps.latitude);
                    longitude = String.valueOf(gps.longitude);
                    System.out.println("Latitude:"+latitude+"Longitude:"+longitude);
                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("latitude",latitude);
                    userMap.put("longitude",longitude);
                    synchronized (this){Currentuserdetails("role");}
                    synchronized (this){Currentuserdetails("mobilenum");}
                    if(rol!=null && mno!=null)  {
                        if (rol.equals("Customer"))
                        {
                            customers.child(mno).updateChildren(userMap);
                        } else {
                            vendors.child(mno).updateChildren(userMap);
                        }
                    }
                    Currentuserdetails("role");
                    System.out.print("check"+rol);

                    if(rol!=null && rol.equals("Customer"))
                    {

                        FirebaseDatabase database;
                        DatabaseReference cuserref,vuserref;
                         final String cusers="customers";
                         final String vusers="vendors";
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
                                    String cveg=ds.child("vegetables").getValue(String.class);
                                    System.out.println(cveg);

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }

                }
                else
                {
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }
                handler.postDelayed(test, 5000);
            }

        };
        handler.postDelayed(test, 0);
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


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public synchronized int onStartCommand(Intent intent, int flags, int startId) {
        final int PRIMARY_FOREGROUND_NOTIF_SERVICE_ID = 1001;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String id = "_channel_01";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannel = new NotificationChannel(id, "notification", importance);
            mChannel.enableLights(true);
            Notification notification = new Notification.Builder(getApplicationContext(), id)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setOngoing(true)
                    .setContentTitle("Vlot Tracking")
                    .setContentText("Tracking according to your list")
                    .build();
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(mChannel);
                mNotificationManager.notify(PRIMARY_FOREGROUND_NOTIF_SERVICE_ID, notification);
            }
            //startForeground(PRIMARY_FOREGROUND_NOTIF_SERVICE_ID, notification);
        }
        return START_STICKY;
    }
}