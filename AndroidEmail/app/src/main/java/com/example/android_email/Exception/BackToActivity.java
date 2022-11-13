package com.example.android_email.Exception;

import android.content.Intent;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class BackToActivity implements Runnable{
    private AppCompatActivity activity;

    public BackToActivity(AppCompatActivity activity){
        this.activity = activity;
    }

    @Override
    public void run() {
        Looper.prepare();
        activity.startActivity(new Intent(activity, activity.getClass()));
        Looper.loop();
    }
}
