package com.example.android_email;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
        Toast.makeText(activity, throwable.getMessage(), Toast.LENGTH_LONG).show();
        Intent activityIntent = new Intent(activity, activity.getClass());
        PendingIntent activityPendingIntent = PendingIntent.getActivity(activity, 0, activityIntent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 0, activityPendingIntent );
        System.exit(2);
        thread.getDefaultUncaughtExceptionHandler().uncaughtException(thread, throwable);
    }
}
