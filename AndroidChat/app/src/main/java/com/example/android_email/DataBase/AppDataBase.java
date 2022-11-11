package com.example.android_email.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.android_email.DataBase.DAO.ChatDAO;
import com.example.android_email.DataBase.DAO.MessageDAO;
import com.example.android_email.DataBase.DAO.UserDAO;
import com.example.android_email.DataBase.Entity.Users;

@Database(entities = {Users.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static volatile AppDataBase INSTANCE;

    public abstract UserDAO userDao();
    public abstract ChatDAO chatDAO();
    public abstract MessageDAO messageDAO();

    public static AppDataBase getInstance(Context context) {
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,AppDataBase.class,"checklist.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return INSTANCE;
    }
}
