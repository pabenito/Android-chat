package com.example.android_email.DataBase.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_email.DataBase.Entity.Chat;


import java.util.List;

@Dao
public interface ChatDAO {

    @Query("SELECT * FROM Chat")
    List<Chat> getAll();

    @Query("SELECT * FROM Chat WHERE id IN (:chatId)")
    List<Chat> loadAllByIds(String chatId);


    @Insert
    void insert(Chat chat);

    @Delete
    void delete(Chat chat);

    @Update
    void update(Chat chat);
}
