package com.example.vlot;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static android.content.ContentValues.TAG;

public class MyService extends Service{
    Handler handler;
    Runnable test;
    public GPSTracker gps;
    public static  String veg,em,rol,mno;
    public int rtype,rt;
    public String mail1;
    String latitude,longitude;
    String customerproductarray[];
    Set<List<String>> allvendorlocations = new HashSet();
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
                    if(rol!=null && mno!=null && Double.parseDouble(latitude)!=0 && Double.parseDouble(longitude)!=0)  {
                        if (rol.equals("Customer"))
                        {
                            customers.child(mno).updateChildren(userMap);
                        } else {
                            vendors.child(mno).updateChildren(userMap);
                        }
                    }
                    int customerpresetdistance=10000;
                    Currentuserdetails("role");
                    Currentuserdetails("vegetables");
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
                                    customerproductarray = veg.split(",");
                                    String cveg=ds.child("vegetables").getValue(String.class);
                                    String vendormail=ds.child("email").getValue(String.class);
                                    if(cveg!=null && customerproductarray!=null && vendormail!=null)
                                    {
                                        String arr[]=cveg.split(",");
                                        for(int i=0;i<customerproductarray.length;i++)
                                        {
                                            List<String> innerList = new ArrayList<>();
                                            for(int j=0;j<arr.length;j++)
                                            {
                                                if(customerproductarray[i].equals(arr[j]))
                                                {
                                                    String vlatitude=ds.child("latitude").getValue(String.class);
                                                    String vlongitude =ds.child("longitude").getValue(String.class);
                                                    if(vlatitude!=null && vlongitude!=null)
                                                    {
                                                        double distance=0;
                                                        distance=  distance(Double.parseDouble(latitude),Double.parseDouble(longitude),Double.parseDouble(vlatitude),Double.parseDouble(vlatitude));
                                                        if(distance!=0 && customerpresetdistance!=0)
                                                        {
                                                            System.out.println("distance="+distance);
                                                            if(distance<=customerpresetdistance) {
                                                                innerList.add(vlatitude);
                                                                innerList.add(vlongitude);
                                                                System.out.println("Location To Be Shown: " +vlatitude+","+vlongitude);
                                                                //startActivity(new Intent(MyService.this,MainActivity.class));
                                                            }
                                                        }
                                                    }
                                                    break;
                                                }
                                            }
                                            if(innerList.size()>0)
                                            {
                                                allvendorlocations.add(innerList);
                                                System.out.println("All vendor locations are:"+allvendorlocations.size());
                                            }
                                        }
                                    }

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                        System.out.println("Length of list:"+allvendorlocations.size());
                        if(allvendorlocations.size()>0)
                        {
                            createNotification();
                        }
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

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public void createNotification()
    {
        final int PRIMARY_FOREGROUND_NOTIF_SERVICE_ID = 100;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String id = "Order Alert";
            Bundle args = new Bundle();
            args.putSerializable("ARRAYLIST",(Serializable)allvendorlocations);
            PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                    new Intent(getApplicationContext(), Fruits.class).putExtra("list",args), PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(id, "notification", importance);
            mChannel.enableLights(true);
            Notification notification = new Notification.Builder(getApplicationContext(), id)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setOngoing(false)
                    .setContentIntent(contentIntent)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setContentTitle("Orders available")
                    .setContentText("Some of your items are arriving you")
                    .setOnlyAlertOnce(true)
                    .build();
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(mChannel);
                mNotificationManager.notify(PRIMARY_FOREGROUND_NOTIF_SERVICE_ID, notification);
            }
        }
    }
}