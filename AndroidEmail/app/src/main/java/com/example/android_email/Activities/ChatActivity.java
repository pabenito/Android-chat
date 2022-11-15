package com.example.android_email.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android_email.Models.User;
import com.example.android_email.R;
import com.example.android_email.databinding.ActivityChatBinding;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    private User reveiverUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
        loadReceiverDetails();
    }

    private  void loadReceiverDetails(){
        reveiverUser = new User();
        reveiverUser.name=SignInActivity.getSignedUser().username;
        binding.texName.setText(reveiverUser.name);
    }

    private void setListeners(){
        binding.imageBack.setOnClickListener(v -> onBackPressed());
    }

}