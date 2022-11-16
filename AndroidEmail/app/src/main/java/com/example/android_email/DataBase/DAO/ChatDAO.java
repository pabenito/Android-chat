package com.example.android_email.DataBase.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.android_email.DataBase.Entity.Chat;


import java.util.List;
// Data access object for chats
@Dao
public interface ChatDAO {
    @Query("SELECT * FROM Chat WHERE user1 LIKE :username OR user2 LIKE :username")
    public List<Chat> getUserChats(String username);

    @Query("SELECT * FROM Chat WHERE id = :chatId")
    public Chat get(long chatId);

    @Insert
    public void insert(Chat chat);

    @Query("SELECT * FROM Chat WHERE (user1 LIKE :user1 AND user2 LIKE :user2) OR (user1 LIKE :user2 AND user2 LIKE :user1)")
    public Chat getByUsers(String user1, String user2);
}
