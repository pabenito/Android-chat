package com.example.android_email;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    private Context context;
    private String message;
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    public ExceptionHandler(Context context){
        this.context = context;
        this.message = "Unexpected Error";
        this.defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
        Toast.makeText(this.context, this.message, Toast.LENGTH_LONG).show();
        try {
            Thread.sleep(3000); // Let the Toast display before app will get shutdown
        } catch (InterruptedException e) {
            System.exit(2);
        } finally {
            this.defaultUncaughtExceptionHandler.uncaughtException(thread, throwable);
        }
    }
}
