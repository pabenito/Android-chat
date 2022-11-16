package com.example.android_email.DataBase.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Entity User
@Entity
public class User {
    @PrimaryKey
    @NonNull
    public String username;
}
