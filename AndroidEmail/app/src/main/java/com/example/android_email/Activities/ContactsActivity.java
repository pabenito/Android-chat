package com.example.android_email.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.android_email.DataBase.AppDataBase;
import com.example.android_email.DataBase.Entity.Chat;
import com.example.android_email.DataBase.Entity.User;
import com.example.android_email.ToastExceptionHandler;
import com.example.android_email.databinding.ActivityAddContactBinding;
import com.example.android_email.databinding.ActivityContactsBinding;

import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    private ActivityContactsBinding binding;
    private AppDataBase db;
    private User user;
    private List<Chat> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new ToastExceptionHandler(this));
        super.onCreate(savedInstanceState);
        binding =  ActivityContactsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = AppDataBase.getInstance(getApplicationContext());
        user = SignInActivity.getSignedUser();
        if (user == null)
            startActivity(new Intent(this, SignInActivity.class));
        else {
            contacts = db.chatDAO().getUserChats(user.username);
            if (contacts.size() == 0)
                startActivity(new Intent(this, AddContactActivity.class));
        }
    }
}