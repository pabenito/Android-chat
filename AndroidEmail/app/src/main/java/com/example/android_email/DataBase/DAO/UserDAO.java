package com.example.android_email.DataBase.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_email.DataBase.Entity.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User WHERE id IN (:userId)")
    List<User> loadAllByIds(String userId);

    @Query("SELECT * FROM User WHERE username LIKE :username LIMIT 1")
    User findByUsername(String username);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);
}
