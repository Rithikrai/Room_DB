package com.example.roomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.roomdb.Room.DatabaseClass;
import com.example.roomdb.Room.User_Details;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserSignupActivtity extends AppCompatActivity {
    Button signupp;
    DatabaseClass databaseClassss;
    EditText email, password, mobile, confirmpass;
    ImageView imgbacksignup;
    String password_txxt, mobile_txxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup_activtity);

        signupp = findViewById(R.id.btnsignup);

        mobile = findViewById(R.id.mobilesignup);
        password = findViewById(R.id.passwordsignup);

        databaseClassss = Room.databaseBuilder(getApplicationContext(), DatabaseClass.class, "Login_Details").
                fallbackToDestructiveMigration().build();


        getSupportActionBar().hide();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFFFF")));
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));


        signupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDaata();
                //duplicatedetails();


//                if (condCheck());
//
//
//
            }
        });
    }

    private void saveDaata() {
        ExecutorService executorService8= Executors.newSingleThreadExecutor();
        executorService8.execute(new Runnable() {
            @Override
            public void run() {
//                User_Details models = new User_Details();
//                models.setMobile(mobile.getText().toString().trim());
//                models.setPassword(password.getText().toString().trim());
                User_Details userdetalis1 = new User_Details(mobile.getText().toString().trim(),password.getText().toString().trim());

                databaseClassss.dbAccess().insertAlldata(userdetalis1);



                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent h = new Intent(UserSignupActivtity.this,DashBoardActivity.class);
                        startActivity(h);
                        Toast.makeText(UserSignupActivtity.this, "Account Created Login Sucessfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        executorService8.shutdown();



    }

//
//
//    private boolean condCheck() {
//
//          if (mobile.getText().toString().equals("")) {
//            Toast.makeText(getApplicationContext(), "Enter Mobile Number", Toast.LENGTH_SHORT).show();
//            mobile.setError("Enter Mobile Number");
//            return true;
//        } else if (password.getText().toString().equals("")) {
//            Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
//            password.setError("Enter Password");
//            return true;
//        }
//
//        Intent e = new Intent(UserSignupActivtity.this,DashBoardActivity.class);
//        {
//            Toast.makeText(getApplicationContext(), "Entered Sucessfully", Toast.LENGTH_SHORT).show();
//
//        }
//        startActivity(e);
//
//        return false;
//    }
//
//
//
//    public void Alreadyuser(View view) {
//        Intent j = new Intent(UserSignupActivtity.this,MainActivity.class);
//        startActivity(j);


    public void twwiterlogin(View view) {

        Intent itent = new Intent(Intent.ACTION_VIEW);
        itent.setData(Uri.parse("https://twitter.com/i/flow/login?input_flow_data=%7B%22requested_variant%22%3A%22eyJsYW5nIjoiZW4ifQ%3D%3D%22%7D"));
        startActivity(itent);
    }

    public void googlelogin(View view) {


        Intent itent = new Intent(Intent.ACTION_VIEW);
        itent.setData(Uri.parse("https://accounts.google.com/signin/v2/identifier?flowName=GlifWebSignIn&flowEntry=ServiceLogin&cid=1&navigationDirection=forward"));
        startActivity(itent);
    }

    public void facebooklogin(View view) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.facebook.com/"));
        startActivity(intent);

    }

    public void newuser(View view) {
        Intent t = new Intent(UserSignupActivtity.this,MainActivity.class);
        startActivity(t);
    }
}

