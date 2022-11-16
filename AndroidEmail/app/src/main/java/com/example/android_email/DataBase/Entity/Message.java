package com.example.android_email.DataBase.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Entity Message
@Entity
public class Message {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long id;

    @NonNull
    public long chatId;

    @NonNull
    public String sender;

    @NonNull
    public String receiver;

    @NonNull
    public String message;
}
