package com.example.android_email.DataBase.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_email.DataBase.Entity.Chat;
import com.example.android_email.DataBase.Entity.User;


import java.util.List;

@Dao
public interface ChatDAO {
    @Query("SELECT * FROM Chat WHERE user1 LIKE :username OR user2 LIKE :username")
    public List<Chat> getChatsUser(String username);

    @Query("SELECT * FROM Chat WHERE id = :chatId")
    public Chat get(int chatId);

    @Insert
    public void insert(Chat chat);
}
