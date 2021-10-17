package com.example.roomdb.Room;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DBAccess {


    //On which the data is inserted
    @Insert
    void insertAlldata(User_Details user_details);

    //For geting the list and all class data get selected
    @Query("select * from userDetails")
    List<User_Details> getAllData();

   @Query("DELETE FROM userDetails WHERE `key` = :key")
    void deletedata(int key);

   @Query("Select Distinct * From userDetails")
    List<User_Details>duplicatedata();

   @Query("Select * From userDetails Where mobile= :mobile and password= :password;")
   List<User_Details> verifiedUser(String mobile,String password);

//   @Query("UPDATE USERDETAILS SET mobile= :mobile,password =:password WHERE 'key'=:key")
//    void updatedata(int mobile,String password,int key);


}
