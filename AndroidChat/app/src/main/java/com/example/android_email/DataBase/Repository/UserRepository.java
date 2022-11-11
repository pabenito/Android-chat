package com.example.android_email.DataBase.Repository;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_email.DataBase.Entity.Users;

import java.util.List;

public interface UserRepository {

    List<Users> getAll();

    List<Users> loadAllByIds(String userId);

    Users findByUsername(String username);

    void insert(Users user);

    void delete(Users user);

    void update(Users user);
}
