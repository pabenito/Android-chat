package com.example.android_email.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android_email.Adapters.ContactsAdapter;
import com.example.android_email.DataBase.AppDataBase;
import com.example.android_email.DataBase.Entity.Chat;
import com.example.android_email.DataBase.Entity.User;
import com.example.android_email.R;
import com.example.android_email.databinding.ActivityContactsBinding;

import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    private ActivityContactsBinding binding;
    private AppDataBase db;
    private User user;
    private List<Chat> contacts;
    private static Chat selectedChat;

    public static Chat getSelectedChat() {
        return selectedChat;
    }

    public static void setSelectedChat(Chat chat) {
        selectedChat = chat;
    }

    public ContactsActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactsBinding.inflate(getLayoutInflater());
        db = AppDataBase.getInstance(getApplicationContext());
        user = SignInActivity.getSignedUser();

        if (user == null)
            startActivity(new Intent(this, SignInActivity.class));
        else {
            contacts = db.chatDAO().getUserChats(user.username);
            if (contacts == null || contacts.size() == 0)
                startActivity(new Intent(this, AddContactActivity.class));
        }

        setContentView(binding.getRoot());
        setSupportActionBar(findViewById(R.id.toolbar));
        binding.rvUsers.setAdapter(new ContactsAdapter(contacts, user, this));
        binding.rvUsers.setVisibility(View.VISIBLE);
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
}