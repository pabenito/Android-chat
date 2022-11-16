package com.example.android_email.DataBase.Entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Entity Chat
@Entity
public class Chat {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long id;

    @NonNull
    public String user1;

    @NonNull
    public String user2;

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", user1='" + user1 + '\'' +
                ", user2='" + user2 + '\'' +
                '}';
    }
}
