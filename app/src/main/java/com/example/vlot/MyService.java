package com.example.vlot;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.admin.SystemUpdatePolicy;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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
import static android.media.RingtoneManager.getDefaultUri;

public class MyService extends Service{
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
    public static String latitudec="";
    public static String longitudec="";
    public static String vegetablesc="";
    String namec="";
    public MyService() {
        handler = new Handler();
        test = new Runnable(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            synchronized public void run() {
                gps = new GPSTracker(MyService.this);
                if (gps.getIsGPSTrackingEnabled())
                {
                    latitude = String.valueOf(gps.latitude);
                    longitude = String.valueOf(gps.longitude);
                    //System.out.println("Latitude:"+latitude+"Longitude:"+longitude);
                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("latitude",latitude);
                    userMap.put("longitude",longitude);
                    synchronized (this){Currentuserdetails("role");}
                    synchronized (this){Currentuserdetails("mobilenum");}
                    synchronized (this){Currentuserdetails("email");}
                    if(rol!=null && mno!=null && Double.parseDouble(latitude)!=0 && Double.parseDouble(longitude)!=0 && em!=null)  {
                        if (rol.equals("Customer"))
                        {
                            customers.child(mno).updateChildren(userMap);
                        } else {
                            vendors.child(mno).updateChildren(userMap);
                        }
                    }
                    Currentuserdetails("role");
                    Currentuserdetails("vegetables");
                    if(rol!=null && rol.equals("Customer") && customerpresetdistance!=0)
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
                                    String name=ds.child("name").getValue(String.class);
                                    String number=ds.child("mobileno").getValue(String.class);
                                    String vlatitude=ds.child("latitude").getValue(String.class);
                                    String vlongitude =ds.child("longitude").getValue(String.class);
                                    if(cveg!=null && customerproductarray!=null && vendormail!=null && name!=null && number!=null)
                                    {
                                        String arr[]=cveg.split(",");
                                        String vtemp="";
                                        List<String> innerList = new ArrayList<>();
                                        for(int i=0;i<customerproductarray.length;i++)
                                        {
                                            for(int j=0;j<arr.length;j++)
                                            {
                                                if(customerproductarray[i].equals(arr[j]))
                                                {
                                                    vtemp+=customerproductarray[i]+",";
                                                    break;
                                                }
                                            }
                                        }
                                        if(vlatitude!=null && vlongitude!=null && vtemp!="")
                                        {
                                            double distance=-1;
                                            distance=  distance(Double.parseDouble(latitude),Double.parseDouble(longitude),Double.parseDouble(vlatitude),Double.parseDouble(vlongitude));
                                            if(distance!=-1 && customerpresetdistance!=0)
                                            {
                                                if(distance<=customerpresetdistance) {
                                                    innerList.add(vlatitude);
                                                    innerList.add(vlongitude);
                                                    innerList.add(vendormail);
                                                    innerList.add(em);
                                                    innerList.add(vtemp);
                                                    innerList.add(name);
                                                    innerList.add(number);
                                                    }
                                            }
                                        }
                                        if(innerList.size()>0)
                                        {
                                            allvendorlocations.add(innerList);
                                            //System.out.println("All vendor locations are:"+allvendorlocations.toString());
                                        }
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                        //System.out.println("Length of list:"+allvendorlocations.size());
                        if(allvendorlocations.size()>0)
                        {
                            System.out.println("Allvendorlocations : "+allvendorlocations.toString()+" Allvendorlocationprevious: "+allvendorlocationsprevious.toString());
                            if(!allvendorlocations.equals(allvendorlocationsprevious)){
                            createNotification();
                            allvendorlocationsprevious=allvendorlocations;
                            }
                        }
                    }
                    else if(rol!=null && rol.equals("Vendor"))
                    {
                        if((stopped!="" && stopped!=null && !stopped.equals(stoppedprevious))) {
                            for (String i : stopped.split(",")) {
                                //System.out.println("Checking for "+i);
                                customers.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                         //latitudec="",longitudec="",namec="",vegetablesc="";
                                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                            List<String> innerList = new ArrayList<>();
                                            if (i.equals(ds.child("email").getValue())) {
                                                System.out.println("Found data for email: "+i);
                                                String latitudec = ds.child("latitude").getValue(String.class);
                                                String longitudec = ds.child("longitude").getValue(String.class);
                                                String namec = ds.child("name").getValue(String.class);
                                                String vegetablesc = ds.child("vegetables").getValue(String.class);
                                                if (latitudec != null && longitudec != null && namec != null && vegetablesc != null) {
                                                    innerList.add(latitudec);
                                                    innerList.add(longitudec);
                                                    innerList.add(namec);
                                                    innerList.add(vegetablesc);
                                                }
                                                //System.out.println("Values are: "+"latitudec:"+latitudec+" longitudec:"+longitudec+" namec:"+namec+" vegetablesc:"+vegetablesc);
                                                //System.out.println("innerlist for :"+i+" is "+innerList.toString());
                                                if (innerList.size() > 0) {
                                                    allcustomermailsforvendors.add(innerList);
                                                }
                                            }
                                        }
                                    }
                                    @Override
                                    public synchronized void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                                //System.out.println("Innerlist to be added "+innerList.toString());
                            }
                            if(allcustomermailsforvendors.size()>0) {
                                //createNotificationvendor();
                                setNotification("Orders Available");
                                stoppedprevious = stopped;
                            }
                        }
                    }
                }
                else
                {
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
                    new Intent(getApplicationContext(), AvailableVendors.class).putExtra("list",args), PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(id, "notification", importance);
            mChannel.enableLights(true);
            Notification notification = new Notification.Builder(getApplicationContext(), id)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setOngoing(false)
                    .setAutoCancel(true)
                    .setContentIntent(contentIntent)
                    .setSound(getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
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



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setNotification(String notificationMessage) {
        final int PRIMARY_FOREGROUND_NOTIF_SERVICE_ID = 1009;
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable) allcustomermailsforvendors);

//**add this line**
        int requestID = (int) System.currentTimeMillis();

        Uri alarmSound = getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //NotificationManager mNotificationManager  = (NotificationManager) getApplication().getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(getApplicationContext(), AvailableCustomers.class);
        notificationIntent.putExtra("list",args);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent contentIntent = PendingIntent.getActivity(this, requestID,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel("Order Alert", "notification", importance);
        mChannel.enableLights(true);


        Notification notification = new Notification.Builder(getApplicationContext(), "Order Alert")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setOngoing(false)
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setSound(getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentTitle("Customers Available")
                .setContentText("Someone asked you to stop")
                .setOnlyAlertOnce(true)
                .build();
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (mNotificationManager != null) {
            mNotificationManager.createNotificationChannel(mChannel);
            mNotificationManager.notify(PRIMARY_FOREGROUND_NOTIF_SERVICE_ID, notification);
        }

    }
    public void createNotificationvendor()
    {
        System.out.println("In Vendor's notification with available customer details "+allcustomermailsforvendors.toString());
        final int PRIMARY_FOREGROUND_NOTIF_SERVICE_ID = 1009;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String id = "Order Alert";
            Bundle args = new Bundle();
            args.putSerializable("ARRAYLIST",(Serializable) allcustomermailsforvendors);
            PendingIntent contentIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(),
                    new Intent(this, AvailableCustomers.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP).putExtra("list",args), PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(id, "notification", importance);
            mChannel.enableLights(true);
            Notification notification = new Notification.Builder(getApplicationContext(), id)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setOngoing(false)
                    .setAutoCancel(true)
                    .setContentIntent(contentIntent)
                    .setSound(getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setContentTitle("Customers Available")
                    .setContentText("Someone asked you to stop")
                    .setOnlyAlertOnce(true)
                    .build();
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(mChannel);
                mNotificationManager.notify(PRIMARY_FOREGROUND_NOTIF_SERVICE_ID, notification);
            }
            //allcustomermailsforvendors.clear();
        }
    }
}