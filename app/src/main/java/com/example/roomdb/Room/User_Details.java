package com.example.roomdb.Room;


import android.provider.ContactsContract;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "userDetails")
public class User_Details {


    //Primary key Auto generate
    @PrimaryKey(autoGenerate = true)
    private int key;

        @ColumnInfo(name = "mobile")
    private String mobile;


        @ColumnInfo(name = "password")
    private String password;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User_Details(String mobile,String password){
            this.mobile=mobile;
            this.password=password;
        }

    public User_Details (){
        this.mobile=mobile;
        this.password=password;
    }





}
