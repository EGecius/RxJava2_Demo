package com.egecius.rxjava2_demo_2;

import android.app.Application;
import android.util.Log;

import java.io.IOException;

import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setRxErrorHandler();
    }

    private void setRxErrorHandler() {


        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable e) throws Exception {

                if (e instanceof UndeliverableException) {
                    e = e.getCause();
                }
                if (e instanceof IOException) {
                    // fine, irrelevant network problem or API that throws on cancellation
                    return;
                }
                if (e instanceof InterruptedException) {
                    // fine, some blocking code was interrupted by a dispose call
                    return;
                }
                if ((e instanceof NullPointerException) || (e instanceof IllegalArgumentException)) {
                    // that's likely a bug in the application
                    passToUncaughtExceptionHandler(e);
                    return;
                }
                if (e instanceof IllegalStateException) {
                    // that's a bug in RxJava or in a custom operator
                    passToUncaughtExceptionHandler(e);
                    return;
                }
                Log.w("Undeliverable exception received, not sure what to do", e);
            }

            private void passToUncaughtExceptionHandler(Throwable throwable) {
                Thread currentThread = Thread.currentThread();
                currentThread.getUncaughtExceptionHandler()
                        .uncaughtException(currentThread, throwable);
            }
        });
    }
}
