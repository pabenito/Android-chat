package com.example.android_email.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android_email.DataBase.AppDataBase;
import com.example.android_email.DataBase.Entity.Chat;
import com.example.android_email.DataBase.Entity.User;
import com.example.android_email.R;
import com.example.android_email.ToastExceptionHandler;
import com.example.android_email.databinding.ActivityAddContactBinding;

import java.util.List;

public class AddContactActivity extends AppCompatActivity {

    private ActivityAddContactBinding binding;
    private AppDataBase db;
    private User user;
    private List<Chat> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new ToastExceptionHandler(this));
        super.onCreate(savedInstanceState);
        binding =  ActivityAddContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = AppDataBase.getInstance(getApplicationContext());
        setListeners();
        user = SignInActivity.getSignedUser();
        if (user == null)
            startActivity(new Intent(this, SignInActivity.class));
        else
            contacts = db.chatDAO().getUserChats(user.username);
    }

    private void setListeners(){
        binding.btnAddContact.setOnClickListener(v -> addContact());
    }

    private void addContact() {
        String username = binding.etUsername.getText().toString().trim().toLowerCase();
        User new_contact = db.userDao().get(username);

        if (new_contact == null)
            throw new RuntimeException(String.format(getResources().getString(R.string.err_UsernameNotFound), username));

        if (contacts.contains(user))
            throw new RuntimeException(String.format(getResources().getString(R.string.err_AlreadyAContact), username));

        Chat new_chat = new Chat();
        new_chat.user1 = user.username;
        new_chat.user2 = new_contact.username;
        db.chatDAO().insert(new_chat);

        Toast.makeText(this, String.format(getResources().getString(R.string.msg_ContactAdded), username), Toast.LENGTH_LONG).show();
    }
}