package com.example.android_email.DataBase.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_email.DataBase.Entity.Chats;


import java.util.List;

@Dao
public interface ChatDAO {

    @Query("SELECT * FROM Chats")
    List<Chats> getAll();

    @Query("SELECT * FROM Chats WHERE id IN (:chatId)")
    List<Chats> loadAllByIds(String chatId);


    @Insert
    void insert(Chats chats);

    @Delete
    void delete(Chats chats);

    @Update
    void update(Chats chats);
}
