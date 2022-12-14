package com.example.android_email.DataBase.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_email.DataBase.Entity.User;

import java.util.List;
// Data access object for users
@Dao
public interface UserDAO {
    @Query("SELECT * FROM User WHERE username LIKE :username LIMIT 1")
    public User get(String username);

    @Insert
    public  void insert(User user);
}
