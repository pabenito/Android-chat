package com.example.android_email.DataBase.Entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Chats {
    @PrimaryKey
    @NonNull
    public String id;

    @NonNull
    public String user1;

    @NonNull
    public String user2;

    @NonNull
    public String chat;
}
