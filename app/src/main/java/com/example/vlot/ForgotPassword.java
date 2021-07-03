package com.example.vlot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.provider.Telephony;
import android.telephony.SmsManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

public class ForgotPassword extends AppCompatActivity {

    EditText upno,uotp;
    String getusermno;
    Button gotpbtn,votpbtn,rotp;
    ImageView otpimg;
    String msg="4406";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        upno=findViewById(R.id.fpmno);
        uotp=findViewById(R.id.eotp);
        gotpbtn=findViewById(R.id.gotp);
        votpbtn=findViewById(R.id.votp);
        otpimg=findViewById(R.id.imgotp);

        otpimg.setVisibility(View.GONE);
        uotp.setVisibility(View.GONE);
        votpbtn.setVisibility(View.GONE);

        gotpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getusermno=upno.getText().toString().trim();
                if(!getusermno.equals(""))
                {
                    otpimg.setVisibility(View.VISIBLE);
                    uotp.setVisibility(View.VISIBLE);
                    votpbtn.setVisibility(View.VISIBLE);

                    if(ContextCompat.checkSelfPermission(ForgotPassword.this, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED)
                    {
                        sendSms();
                    }
                    else{
                        ActivityCompat.requestPermissions(ForgotPassword.this,new String[]{Manifest.permission.SEND_SMS},100);
                    }
                }
                else
                {
                    Toast.makeText(ForgotPassword.this,"Enter Mobile number",Toast.LENGTH_LONG).show();
                }
            }
        });

        votpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyOtp();
            }
        });

    }
    public void sendSms()
    {
        getusermno=upno.getText().toString().trim();
        if(!getusermno.equals(""))
        {
            //initialize smsManager
            //Toast.makeText(getApplicationContext(),"Sending..",Toast.LENGTH_LONG).show();
            String s="VLOt";
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(getusermno,"s",msg,null,null);
            Toast.makeText(getApplicationContext(),"OTP Sent Successfully",Toast.LENGTH_LONG).show();
            gotpbtn.setText("Resend Otp");
        }
    }

    public void verifyOtp()
    {
        String vuserotp=uotp.getText().toString().trim();
        if(vuserotp.equals(msg))
        {
            Toast.makeText(ForgotPassword.this,"Verification done successfully",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(ForgotPassword.this,"Incorrect OTP",Toast.LENGTH_LONG).show();
        }
    }

}