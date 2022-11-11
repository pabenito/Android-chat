package com.example.android_email.DataBase.Repository;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_email.DataBase.Entity.Chats;

import java.util.List;

public interface ChatRepository {

    List<Chats> getAll();

    List<Chats> loadAllByIds(String chatId);


    void insert(Chats chats);

    void delete(Chats chats);

    void update(Chats chats);
}
