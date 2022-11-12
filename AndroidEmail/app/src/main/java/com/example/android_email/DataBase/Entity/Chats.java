package com.example.android_email.DataBase.Entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "Chats")
public class Chats {

    @PrimaryKey
    @NonNull
    public String id;

    @NonNull
    @ColumnInfo(name="user1")
    public String user1;

    @NonNull
    @ColumnInfo(name="user2")
    public String user2;

    @NonNull
    @ColumnInfo(name="Chat")
    public String chat;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getUser1() {
        return user1;
    }

    public void setUser1(@NonNull String user1) {
        this.user1 = user1;
    }

    @NonNull
    public String getUser2() {
        return user2;
    }

    public void setUser2(@NonNull String user2) {
        this.user2 = user2;
    }

    @NonNull
    public String getChat() {
        return chat;
    }

    public void setChat(@NonNull String chat) {
        this.chat = chat;
    }
}
