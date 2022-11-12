package com.example.android_email.DataBase.Entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Chats {

    @PrimaryKey
    @NonNull
    public String id;

    @NonNull
    @ColumnInfo(name="sender")
    public Users sender;

    @NonNull
    @ColumnInfo(name="reciever")
    public Users reciever;

    @NonNull
    @ColumnInfo(name="messages")
    public List<Messages> message;

    @NonNull
    @ColumnInfo(name="Chat")
    public Chats chat;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NonNull
    public Users getSender() {
        return sender;
    }

    public void setSender(@NonNull Users sender) {
        this.sender = sender;
    }

    @NonNull
    public Users getReciever() {
        return reciever;
    }

    public void setReciever(@NonNull Users reciever) {
        this.reciever = reciever;
    }

    @NonNull
    public List<Messages> getMessage() {
        return message;
    }

    public void setMessage(@NonNull List<Messages> message) {
        this.message = message;
    }

    @NonNull
    public Chats getChat() {
        return chat;
    }

    public void setChat(@NonNull Chats chat) {
        this.chat = chat;
    }
}
