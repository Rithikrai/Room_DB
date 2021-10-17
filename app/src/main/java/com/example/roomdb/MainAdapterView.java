package com.example.roomdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomdb.Room.DatabaseClass;
import com.example.roomdb.Room.User_Details;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainAdapterView  extends RecyclerView.Adapter< MainAdapterView.ViewHolder>{
    private List<User_Details> user_detailsList;
    DatabaseClass databaseClasss;
    Context context;
    AlertDialog.Builder alertDialog;

    public MainAdapterView(Context context ,List<User_Details> user_baba) {
        this.context =context;
        this.user_detailsList=user_baba;
        //this.deleteItemClicklistner


    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.main_adapter_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {


       final User_Details ghk = user_detailsList.get(position);
       holder.id.setText(ghk.getKey()+"");
       holder.mobile.setText(ghk.getMobile());
       holder.password.setText(ghk.getPassword());

       databaseClasss = Room.databaseBuilder(context, DatabaseClass.class, "Login_Details").
                fallbackToDestructiveMigration().build();
       alertDialog=new AlertDialog.Builder(context);
       holder.imgdelete.setOnClickListener(new View.OnClickListener() {
           @Override

           public void onClick(View v) {

               alertDialog.setMessage("Welcome Alert Dialog") .setTitle("Alert Dialog");


               alertDialog.setMessage("Do you want to Delete Id?")
                       .setCancelable(false)
                       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {

                               ExecutorService executorService= Executors.newSingleThreadExecutor();
                               executorService.execute(new Runnable() {
                                   @Override
                                   public void run() {
                                       databaseClasss.dbAccess().deletedata(ghk.getKey());

                                       removeItem(position);

                                   }
                               });
                               executorService.shutdown();
                           }
                       })
                       .setNegativeButton("No", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                               //  Action for 'NO' Button
                               dialog.cancel();
                               Toast.makeText(context,"You choose no action ",
                                       Toast.LENGTH_SHORT).show();
                           }
                       });
               //Creating dialog box
               AlertDialog alert = alertDialog.create();
               //Setting the title manually
               alert.setTitle("Delete");
               alert.show();

           }
       });

       }

    public void removeItem(int position) {
        this.user_detailsList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount() - position);
    }

    @Override
    public int getItemCount() {
        return user_detailsList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mobile;
        public TextView password;
        public TextView id;
        public ImageView imgdelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.id = (TextView) itemView.findViewById(R.id .akey);
            this.mobile = (TextView) itemView.findViewById(R.id.amobile_number);
            this.password = (TextView) itemView.findViewById(R.id.apassword);
            imgdelete = (ImageView) itemView.findViewById(R.id.aimgdelete);


        }

    }
}