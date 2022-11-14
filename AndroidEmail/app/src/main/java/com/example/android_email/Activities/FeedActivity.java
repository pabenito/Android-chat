package com.example.android_email.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_email.DataBase.AppDataBase;
import com.example.android_email.DataBase.Entity.User;
import com.example.android_email.R;
import com.example.android_email.databinding.ActivityFeedBinding;

public class FeedActivity extends AppCompatActivity {

    private User singedUser = new SignInActivity().getSignedUser();
    private ActivityFeedBinding binding;
    private AppDataBase db;
    private TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        binding = ActivityFeedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        username = (TextView) findViewById(R.id.userName);
        username.setText(singedUser.username);
        setListeners();
    }

    private void setListeners() {
        binding.logOut.setOnClickListener(v -> singOut());
    }

    private void singOut() {
        showToast(String.format(getResources().getString(R.string.LogOutMessage), username));
        startActivity(new Intent(this, SignInActivity.class));
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
    }


}