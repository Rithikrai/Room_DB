package com.example.roomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdb.Room.DatabaseClass;
import com.example.roomdb.Room.User_Details;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    DatabaseClass databaseClass;
    EditText mobile, password;
    Button insert;
    List<User_Details> user_detalsList = new ArrayList<>() ;
   // List<User_Details> user_detalsList1 = new ArrayList<>() ;
    String password_txt, mobile_txt;
    int flags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        //initialize room object
        databaseClass = Room.databaseBuilder(getApplicationContext(), DatabaseClass.class, "Login_Details").
                fallbackToDestructiveMigration().build();


//        databaseClass.dbAccess().getAllData();

        //initilaze
        initializeView();


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTextData();
                if (condCheck()) {
//                    saveData();
                    verifyuser();
                }

            }
        });
    }

    private void verifyuser() {
        ExecutorService executorService4= Executors.newSingleThreadExecutor();
        executorService4.execute(new Runnable() {
            @Override
            public void run() {
                user_detalsList =  databaseClass.dbAccess().verifiedUser(mobile_txt,password_txt);

//                for ( int i = 0;i<user_detalsList.size();i++){
//                    user_detalsList.add(i).
//
//                }


                if (user_detalsList.isEmpty()) {

                    abcd(1);

                }
                else {

                    abcd(2);


                }



            }
        });
        executorService4.shutdown();
    }

    private void abcd(int flag) {
        ExecutorService executorService5= Executors.newSingleThreadExecutor();
        executorService5.execute(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (flag==1){
                            Intent i = new Intent(MainActivity.this,UserSignupActivtity.class);
                            startActivity(i);

                        }
                        else if (flag==2){
                            saveData();
                            Intent i = new Intent(MainActivity.this,DashBoardActivity.class);
                            startActivity(i);

                        }
                        else {
                            Toast.makeText(MainActivity.this, "Hii", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
        executorService5.shutdown();
    }


    private boolean condCheck() {
        if (mobile_txt.length() != 10) {
            Toast.makeText(MainActivity.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password_txt.length() < 10) {
            Toast.makeText(MainActivity.this, "Enter The Password", Toast.LENGTH_SHORT).show();
            return false;

        } else {
            return true;
        }

    }

    private void getTextData() {
        mobile_txt = mobile.getText().toString().trim();
        password_txt = password.getText().toString().trim();
    }

    private void initializeView() {
        mobile = findViewById(R.id.ed_mobile);
        password = findViewById(R.id.ed_password);
        insert = findViewById(R.id.btnAdd_data);
    }

    private void saveData() {
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                User_Details model = new User_Details();
                model.setMobile(mobile_txt);
                model.setPassword(password_txt);
//                databaseClass.dbAccess().insertAlldata(model);



                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
executorService.shutdown();


    }

    public void getlist(View view) {

        Intent r = new Intent(MainActivity.this,DashBoardActivity.class);
        startActivity(r);
        this.finish();
    }
}