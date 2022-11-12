package com.example.android_email.DataBase.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Messages")
public class Messages {

    @PrimaryKey
    @NonNull
    public String id;

    @NonNull
    @ColumnInfo(name="chatId")
    public String chatId;

    @NonNull
    @ColumnInfo(name="sender")
    public String sender;

    @NonNull
    @ColumnInfo(name="receiver")
    public String receiver;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(@NonNull String receiver) {
        this.receiver = receiver;
    }
}
