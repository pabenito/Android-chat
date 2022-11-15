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

    private User user;
    private ActivityFeedBinding binding;
    private AppDataBase db;
    private TextView tvUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        binding = ActivityFeedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user =  SignInActivity.getSignedUser();
        tvUsername = (TextView) findViewById(R.id.userName);
        tvUsername.setText(user.username);
        setListeners();
    }

    private void setListeners() {
        binding.logOut.setOnClickListener(v -> singOut());
    }

    private void singOut() {
        showToast(String.format(getResources().getString(R.string.LogOutMessage), tvUsername.getText().toString()));
        startActivity(new Intent(this, SignInActivity.class));
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
    }


}