package com.egecius.rxjava2_demo_2;


import android.os.Handler;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CallbackFramework {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler mHandler = new Handler();

    interface Callback {
        void onSuccess();
    }

    void returnOnMainThread(Callback callback) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess();
            }
        }, 300);
    }

    void returnOnBackgroundThread(Callback callback) {
        Log.v("Eg:CallbackFramework:30", "returnOnBackgroundThread");

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess();
            }
        });
    }
}
