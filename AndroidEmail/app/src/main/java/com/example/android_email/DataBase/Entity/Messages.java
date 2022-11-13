package com.example.android_email.DataBase.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Messages {
    @PrimaryKey
    @NonNull
    public String id;

    @NonNull
    public String chatId;

    @NonNull
    public String sender;

    @NonNull
    public String receiver;
}
