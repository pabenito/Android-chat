package com.example.android_email.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.android_email.DataBase.AppDataBase;
import com.example.android_email.DataBase.Entity.Chat;
import com.example.android_email.DataBase.Entity.User;
import com.example.android_email.R;
import com.example.android_email.databinding.ActivityAddContactBinding;

import java.util.List;
//Activity where users can add new users to chat with.
public class AddContactActivity extends AppCompatActivity {

    private ActivityAddContactBinding binding;
    private AppDataBase db;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = AppDataBase.getInstance(getApplicationContext());
        setListeners();
        user = SignInActivity.getSignedUser();
        //If by any chance the user can get on this view without sign up. The app would redirect to the sign up activity
        if (user == null)
            startActivity(new Intent(this, SignInActivity.class));

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.primary));
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.findItem(R.id.a_AddContacts).setVisible(false);
        return true;
    }

    //Options on tool bar
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean ok = true;
        switch (item.getItemId()) {
            case R.id.a_AddContacts: break;
            case R.id.a_contacts: startActivity(new Intent(this, ContactsActivity.class)); break;
            case R.id.a_LogOut: showToast(String.format(getResources().getString(R.string.LogOutMessage), user.username)); startActivity(new Intent(this, SignInActivity.class)); break;
            default:
                ok = super.onOptionsItemSelected(item);
                break;
        }

        return ok;
    }

    private void setListeners(){
        binding.btnAddContact.setOnClickListener(v -> addContact());
    }

    //We create the chat between both users at the time when one of them add the other
    private void addContact() {
        String username = binding.etUsername.getText().toString().trim().toLowerCase();
        User new_contact = db.userDao().get(username);

        if (new_contact == null){
            showToast(String.format(getResources().getString(R.string.err_UsernameNotFound), username));

        }else if (db.chatDAO().getByUsers(user.username, new_contact.username) != null){
            showToast(String.format(getResources().getString(R.string.err_AlreadyAContact), username));
        }else{
            Chat new_chat = new Chat();
            new_chat.user1 = user.username;
            new_chat.user2 = new_contact.username;
            db.chatDAO().insert(new_chat);

            showToast(String.format(getResources().getString(R.string.msg_ContactAdded), username));
        }

        hideSoftKeyboard(binding.etUsername);
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
    }

    private void hideSoftKeyboard(View view) {
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}