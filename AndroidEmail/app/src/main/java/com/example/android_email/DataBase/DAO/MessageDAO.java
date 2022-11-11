package com.example.android_email.DataBase.DAO;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_email.DataBase.Entity.Messages;
import com.example.android_email.DataBase.Entity.Users;

import java.util.List;

public interface MessageDAO {

    @Query("SELECT * FROM Messages")
    List<Messages> getAll();

    @Query("SELECT * FROM Messages WHERE id IN (:messageId)")
    List<Messages> loadAllByIds(String messageId);


    @Insert
    void insert(Messages messages);

    @Delete
    void delete(Messages messages);

    @Update
    void update(Messages messages);
}
