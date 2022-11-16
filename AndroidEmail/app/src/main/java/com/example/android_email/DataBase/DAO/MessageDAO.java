package com.example.android_email.DataBase.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_email.DataBase.Entity.Chat;
import com.example.android_email.DataBase.Entity.Message;

import java.util.List;

@Dao
public interface MessageDAO {
    @Query("SELECT * FROM Message WHERE chatId =  :chatId")
    public List<Message> getChatMessages(long chatId);

    @Query("SELECT * FROM Message WHERE id = :id")
    public Message get(long id);

    @Insert
    public long insert(Message messages);
}
