package com.example.android_email.DataBase.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_email.DataBase.Entity.Message;

import java.util.List;

@Dao
public interface MessageDAO {

    @Query("SELECT * FROM Message")
    List<Message> getAll();

    @Query("SELECT * FROM Message WHERE id IN (:messageId)")
    List<Message> loadAllByIds(String messageId);


    @Insert
    void insert(Message messages);

    @Delete
    void delete(Message messages);

    @Update
    void update(Message messages);
}
