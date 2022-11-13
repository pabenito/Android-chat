package com.example.android_email;

import static com.google.android.material.internal.ContextUtils.getActivity;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    private Context context;
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    public ExceptionHandler(Context context){
        this.context = context;
        this.defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
        Toast.makeText(this.context,R.string.err_Unexpected, Toast.LENGTH_LONG).show();
        try {
            Thread.sleep(3000); // Let the Toast display before app will get shutdown
        } catch (InterruptedException e) {
            System.exit(2);
        } finally {
            this.defaultUncaughtExceptionHandler.uncaughtException(thread, throwable);
        }
    }
}
