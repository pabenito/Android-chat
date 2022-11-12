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
    @ColumnInfo(name="sender")
    public String sender;

    @NonNull
    @ColumnInfo(name="reciever")
    public String reciever;

    @NonNull
    public String message;

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
    public String getSender() {
        return sender;
    }

    public void setSender(@NonNull String sender) {
        this.sender = sender;
    }

    @NonNull
    public String getReciever() {
        return reciever;
    }

    public void setReciever(@NonNull String reciever) {
        this.reciever = reciever;
    }

    @NonNull
    public String getMessage() {
        return message;
    }

    public void setMessage(@NonNull String message) {
        this.message = message;
    }

    @NonNull
    public String getChat() {
        return chat;
    }

    public void setChat(@NonNull String chat) {
        this.chat = chat;
    }
}
