package com.example.android_email.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android_email.DataBase.AppDataBase;
import com.example.android_email.DataBase.Entity.User;
import com.example.android_email.ToastExceptionHandler;
import com.example.android_email.R;
import com.example.android_email.databinding.ActivitySingUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySingUpBinding binding;
    private AppDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new ToastExceptionHandler(this));
        super.onCreate(savedInstanceState);
        binding = ActivitySingUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = AppDataBase.getInstance(getApplicationContext());
        setListeners();
    }

    private void setListeners(){
        binding.tvSignIn.setOnClickListener(v -> onBackPressed());
        binding.btnSignUp.setOnClickListener(v -> singUp());
    }

    private void singUp() {
        String username = binding.etUsername.getText().toString().trim().toLowerCase();
        User user = db.userDao().get(username);

        if (user != null)
            throw new RuntimeException(String.format(getResources().getString(R.string.err_UsernameTaken), username));

        User new_user = new User();
        new_user.username = username;
        db.userDao().insert(new_user);

        Toast.makeText(this, String.format(getResources().getString(R.string.msg_SingUpSuccessfully), username), Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, SignInActivity.class));
    }
}