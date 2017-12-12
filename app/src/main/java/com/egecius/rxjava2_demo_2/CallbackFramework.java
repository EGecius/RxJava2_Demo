package com.egecius.rxjava2_demo_2;


import android.os.Handler;

class CallbackFramework {

    Handler mHandler = new Handler();

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
}
