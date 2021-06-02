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

import com.google.android.gms.location.LocationListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MyService extends Service{
    Handler handler;
    Runnable test;
    GPSTracker gps;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference customers=db.getReference().child("customers");
    private DatabaseReference vendors=db.getReference().child("vendors");
    public MyService() {
        handler = new Handler();
        test = new Runnable() {
            @Override
            public void run() {
                gps = new GPSTracker(MyService.this);

                // Check if GPS enabled
                if(gps.canGetLocation()) {

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    System.out.println("Latitude:"+latitude+"Longitude:"+longitude);
                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("latitude",latitude);
                    userMap.put("longitude",longitude);
                    if("customer"=="customer")
                    {
                        customers.child("8309734591").updateChildren(userMap);
                    }
                    else{
                        customers.child("8309734591").updateChildren(userMap);
                    }
                } else {
                    gps.showSettingsAlert();
                }
                System.out.println("Fsociety");
                handler.postDelayed(test, 5000); //100 ms you should do it 4000
            }
        };
        handler.postDelayed(test, 0);
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
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