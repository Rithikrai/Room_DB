package com.example.roomdb.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(
        entities = {User_Details.class,},version = 5 ,  exportSchema = false)
    public abstract class DatabaseClass extends RoomDatabase {

    private static DatabaseClass getInstance;

    public abstract DBAccess dbAccess();


    public static DatabaseClass getDatebase(final Context context)
    {
        if (getInstance ==null)
        {
            synchronized (DatabaseClass.class)
            {
                getInstance= Room.databaseBuilder(context,DatabaseClass.class,"DATABASE").build();
            }

        }



        return getInstance;
    }

}
