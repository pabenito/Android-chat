package com.example.android_email.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_email.Adapters.UsersAdapter;
import com.example.android_email.DataBase.AppDataBase;
import com.example.android_email.DataBase.Entity.Chat;
import com.example.android_email.DataBase.Entity.User;
import com.example.android_email.Listeners.UserListener;
import com.example.android_email.R;
import com.example.android_email.ToastExceptionHandler;
import com.example.android_email.databinding.ActivityContactsBinding;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity implements UserListener {

    private ActivityContactsBinding binding;
    private AppDataBase db;
    private User user;
    private List<Chat> contacts;
    private TextView tvUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new ToastExceptionHandler(this));
        super.onCreate(savedInstanceState);
        binding = ActivityContactsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = AppDataBase.getInstance(getApplicationContext());
        user = SignInActivity.getSignedUser();

        tvUsername = (TextView) findViewById(R.id.et_Username);
        tvUsername.setText(user.username);
/*
        if (user == null)
            startActivity(new Intent(this, SignInActivity.class));
        else {
            contacts = db.chatDAO().getUserChats(user.username);
            if (contacts.size() == 0)
                startActivity(new Intent(this, AddContactActivity.class));
        }

 */
        setSupportActionBar(findViewById(R.id.toolbar));
        getChats();
    }

    private void getChats() {
        contacts = db.chatDAO().getUserChats(user.username);
        List<com.example.android_email.Models.User> userList = new ArrayList<>();
        for (Chat c : contacts) {
            String nombre = (c.user1 == user.username ? c.user2 : c.user1);
            User aux = db.userDao().get(nombre);
            com.example.android_email.Models.User aux2 = new com.example.android_email.Models.User();
            aux2.name = aux.username;
            userList.add(aux2);
        }

        if (userList.size() > 0) {
            UsersAdapter usersAdapter = new UsersAdapter(userList, this);
            binding.usersRecycleView.setAdapter(usersAdapter);
            binding.usersRecycleView.setVisibility(View.VISIBLE);
        } else {
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.findItem(R.id.a_contacts).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean ok = true;
        switch (item.getItemId()) {
            case R.id.a_AddContacts:
                startActivity(new Intent(this, AddContactActivity.class));
                break;
            case R.id.a_contacts:
                break;
            case R.id.a_LogOut:
                showToast(String.format(getResources().getString(R.string.LogOutMessage), user.username));
                startActivity(new Intent(this, SignInActivity.class));
            default:
                ok = super.onOptionsItemSelected(item);
                break;
        }

        return ok;
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onUserClicked(com.example.android_email.Models.User user) {
        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        startActivity(intent);
        finish();
    }
}