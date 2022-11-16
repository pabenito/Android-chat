package com.example.android_email.DataBase.Entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Chat {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long id;

    @NonNull
    public String user1;

    @NonNull
    public String user2;
}
