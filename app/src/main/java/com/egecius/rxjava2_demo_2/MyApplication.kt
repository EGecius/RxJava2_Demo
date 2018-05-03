package com.egecius.rxjava2_demo_2

import android.app.Application
import android.util.Log

import java.io.IOException

import io.reactivex.exceptions.UndeliverableException
import io.reactivex.functions.Consumer
import io.reactivex.plugins.RxJavaPlugins

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setRxErrorHandler()
    }

    private fun setRxErrorHandler() {


        RxJavaPlugins.setErrorHandler(object : Consumer<Throwable> {
            override fun accept(e: Throwable) {
                var e = e

                if (e is UndeliverableException) if (e.cause != null) {
                    e = e.cause!!
                }
                if (e is IOException) {
                    // fine, irrelevant network problem or API that throws on cancellation
                    return
                }
                if (e is InterruptedException) {
                    // fine, some blocking code was interrupted by a dispose call
                    return
                }
                if (e is NullPointerException || e is IllegalArgumentException) {
                    // that's likely a bug in the application
                    passToUncaughtExceptionHandler(e)
                    return
                }
                if (e is IllegalStateException) {
                    // that's a bug in RxJava or in a custom operator
                    passToUncaughtExceptionHandler(e)
                    return
                }
                Log.w("Undeliverable exception received, not sure what to do", e)
            }

            private fun passToUncaughtExceptionHandler(throwable: Throwable) {
                val currentThread = Thread.currentThread()
                currentThread.uncaughtExceptionHandler
                        .uncaughtException(currentThread, throwable)
            }
        })
    }
}
