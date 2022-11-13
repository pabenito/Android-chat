package com.example.android_email.DataBase.Entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Chat {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @NonNull
    public int user1;

    @NonNull
    public int user2;
}
