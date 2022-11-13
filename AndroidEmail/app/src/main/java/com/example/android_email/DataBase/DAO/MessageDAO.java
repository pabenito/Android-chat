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
    @Query("SELECT * FROM Message WHERE chatId =  :chatId")
    public List<Message> getChatMessages(int chatId);

    @Insert
    public void insert(Message messages);
}
