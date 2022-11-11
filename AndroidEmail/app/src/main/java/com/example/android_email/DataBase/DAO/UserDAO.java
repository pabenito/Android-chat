package com.example.android_email.DataBase.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_email.DataBase.Entity.Users;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM Users")
    List<Users> getAll();

    @Query("SELECT * FROM Users WHERE id IN (:userId)")
    List<Users> loadAllByIds(String userId);

    @Query("SELECT * FROM Users WHERE username LIKE :username LIMIT 1")
    Users findByUsername(String username);

    @Insert
    void insert(Users user);

    @Delete
    void delete(Users user);

    @Update
    void update(Users user);
}
