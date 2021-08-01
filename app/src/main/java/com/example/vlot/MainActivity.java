package com.example.vlot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class
MainActivity extends AppCompatActivity {
    public EditText emailId, passwd,cpasswd;
    Button btnSignUp;
    TextView signIn;
    FirebaseAuth firebaseAuth;
    RadioGroup type;
    RadioButton selectedbutton;
    EditText name;
    EditText number;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference customers=db.getReference().child("customers");
    private DatabaseReference vendors=db.getReference().child("vendors");

    public void onStart() {
        super.onStart();
        if (this.firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, Home.class));
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.email);
        passwd = findViewById(R.id.loginpaswd);
        btnSignUp = findViewById(R.id.btnlogin);
        signIn = findViewById(R.id.singup);
        type=findViewById(R.id.radioGroup);
        cpasswd=findViewById(R.id.loginpsw1);
        name=findViewById(R.id.name);
        number=findViewById(R.id.email);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailID = emailId.getText().toString()+"@gmail.com";
                String paswd = passwd.getText().toString();
                String cpaswd=cpasswd.getText().toString();
                int selectedId = type.getCheckedRadioButtonId();
                selectedbutton=findViewById(selectedId);
                String typex="";
                if(!(type.getCheckedRadioButtonId() == -1)) {
                    typex = selectedbutton.getText().toString();
                }
                if (emailID.isEmpty()) {
                    emailId.setError("Provide your Email first!");
                    emailId.requestFocus();
                } else if (paswd.isEmpty()) {
                    passwd.setError("Set your password");
                    passwd.requestFocus();
                }
                else if(typex.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Select Vendor or Customer!", Toast.LENGTH_SHORT).show();
                }else if (emailID.isEmpty() && paswd.isEmpty() && typex.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                }else if(!paswd.equals(cpaswd)) {
                    Toast.makeText(MainActivity.this, "Passwords doesn't match!", Toast.LENGTH_SHORT).show();
                }else if (!(emailID.isEmpty() && paswd.isEmpty())) {
                    String finalTypex = typex;
                    String finalTypex1 = typex;
                    firebaseAuth.createUserWithEmailAndPassword(emailID, paswd).addOnCompleteListener(MainActivity.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this.getApplicationContext(),
                                        "SignUp unsuccessful: " + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                            else {
                                //Database code
                                System.out.println("Type: "+finalTypex);
                                if(finalTypex.equals("Customer")) {
                                    HashMap<String, String> userMap = new HashMap<>();
                                    userMap.put("name",name.getText().toString());
                                    userMap.put("email",emailId.getText().toString()+"@gmail.com");
                                    userMap.put("password",passwd.getText().toString());
                                    userMap.put("mobileno",number.getText().toString());
                                    userMap.put("role", finalTypex1);
                                    userMap.put("distance","1");
                                    userMap.put("vegetables","");

                                    customers.child(number.getText().toString()).setValue(userMap);
                                }
                                else
                                {
                                    HashMap<String, String> userMap = new HashMap<>();
                                    userMap.put("name",name.getText().toString());
                                    userMap.put("email",emailId.getText().toString()+"@gmail.com");
                                    userMap.put("password",passwd.getText().toString());
                                    userMap.put("mobileno",number.getText().toString());
                                    userMap.put("role", finalTypex1);
                                    userMap.put("vegetables","");
                                    userMap.put("stopped","");
                                    vendors.child(number.getText().toString()).setValue(userMap);
                                }
                                Intent I = new Intent(MainActivity.this, Home.class);
                                startActivity(I);
                                finish();
                            }
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(MainActivity.this, Login.class);
                startActivity(I);
                finish();
            }
        });
    }
}