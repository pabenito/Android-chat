package com.example.android_email.DataBase.Repository;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_email.DataBase.Entity.Messages;

import java.util.List;

public interface MessageRepository {

    List<Messages> getAll();

    List<Messages> loadAllByIds(String messageId);


    void insert(Messages messages);

    void delete(Messages messages);

    void update(Messages messages);
}
