package com.example.roomdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.roomdb.Room.DatabaseClass;
import com.example.roomdb.Room.User_Details;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DashBoardActivity extends AppCompatActivity {

    DatabaseClass databaseClas;
    List<User_Details> user_detailsList = new ArrayList<>() ;
    RecyclerView recyclerView;
    MainAdapterView adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);




        databaseClas = Room.databaseBuilder(getApplicationContext(), DatabaseClass.class, "Login_Details").
                fallbackToDestructiveMigration().build();

        ExecutorService executorService= Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                       user_detailsList= databaseClas.dbAccess().getAllData();

                       showData();
                    afterdelete();

            }
        });
        executorService.shutdown();

    }

    private void afterdelete() {
        ExecutorService executorService3=Executors.newSingleThreadExecutor();
        executorService3.execute(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewdash);
                        adapter = new MainAdapterView(DashBoardActivity.this, user_detailsList);
                        recyclerView.destroyDrawingCache();
                        recyclerView.setAdapter(adapter);
                        recyclerView.setVisibility(RecyclerView.INVISIBLE);
                        recyclerView.setVisibility(RecyclerView.VISIBLE);
                    }
                });
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        showData();
    }


    private void showData() {

        ExecutorService executorService1= Executors.newSingleThreadExecutor();
        executorService1.execute(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerviewdash);
                        MainAdapterView adapter = new MainAdapterView(DashBoardActivity.this, user_detailsList);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(DashBoardActivity.this));
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();


                    }
                });

            }

        });
        executorService1.shutdown();
    }

}