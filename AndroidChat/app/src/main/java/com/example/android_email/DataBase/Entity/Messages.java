package com.example.android_email.DataBase.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Messages {

    @PrimaryKey
    @NonNull
    public String id;

    @NonNull
    @ColumnInfo(name="user1")
    public Users user1;

    @NonNull
    @ColumnInfo(name="user2")
    public Users user2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NonNull
    public Users getUser1() {
        return user1;
    }

    public void setUser1(@NonNull Users user1) {
        this.user1 = user1;
    }

    @NonNull
    public Users getUser2() {
        return user2;
    }

    public void setUser2(@NonNull Users user2) {
        this.user2 = user2;
    }
}
