package com.example.android_email.Exception;

import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ToastExceptionHandler implements Thread.UncaughtExceptionHandler {
    private AppCompatActivity activity;

    public ToastExceptionHandler(AppCompatActivity activity){
        this.activity = activity;
    }

    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
        Toast.makeText(activity.getApplicationContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
        Runnable backToActivity = new BackToActivity(activity);
        Thread newThread = new Thread(backToActivity);
        newThread.start();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
