package com.example.android_email.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        setSupportActionBar(findViewById(R.id.toolbar));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.findItem(R.id.a_AddContacts).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean ok = true;
        switch (item.getItemId()) {
            case R.id.a_AddContacts: break;
            case R.id.a_contacts: startActivity(new Intent(this, ContactsActivity.class));
            default:
                ok = super.onOptionsItemSelected(item);
                break;
        }

        return ok;
    }

    private void setListeners(){
        binding.btnAddContact.setOnClickListener(v -> addContact());
    }

    private void addContact() {
        String username = binding.etUsername.getText().toString().trim().toLowerCase();
        User new_contact = db.userDao().get(username);

        if (new_contact == null){
            showToast(String.format(getResources().getString(R.string.err_UsernameNotFound), username));

        }else if (contacts.contains(user)){
            showToast(String.format(getResources().getString(R.string.err_AlreadyAContact), username));
        }else{
            Chat new_chat = new Chat();
            new_chat.user1 = user.username;
            new_chat.user2 = new_contact.username;
            db.chatDAO().insert(new_chat);

            showToast(String.format(getResources().getString(R.string.msg_ContactAdded), username));
        }

    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
    }
}