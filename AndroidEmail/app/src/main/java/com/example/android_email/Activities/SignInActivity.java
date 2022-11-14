package com.example.android_email.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android_email.DataBase.AppDataBase;
import com.example.android_email.DataBase.Entity.User;
import com.example.android_email.R;
import com.example.android_email.ToastExceptionHandler;
import com.example.android_email.databinding.ActivitySingInBinding;

public class SignInActivity extends AppCompatActivity {

    private ActivitySingInBinding binding;
    private AppDataBase db;

    private static User signedUser;

    public static User getSignedUser() {
        return signedUser;
    }
    public static void setSignedUser() {
        signedUser=null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new ToastExceptionHandler(this));
        super.onCreate(savedInstanceState);
        binding = ActivitySingInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = AppDataBase.getInstance(getApplicationContext());
        setListeners();

    }

    private void setListeners() {
        binding.tvCreateAccount.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SignUpActivity.class)));
        binding.btnSignIn.setOnClickListener(v -> signIn());
    }

    private void signIn() {
        String username = binding.etUsername.getText().toString().trim().toLowerCase();
        User user = db.userDao().get(username);

        if (user == null) {
            showToast(String.format(getResources().getString(R.string.err_UsernameNotFound), username));
        } else {
            signedUser = user;

            showToast(String.format(getResources().getString(R.string.msg_SingInSuccessfully), username));

            startActivity(new Intent(this, FeedActivity.class));
        }


    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
    }

}