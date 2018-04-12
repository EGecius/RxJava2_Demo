package com.egecius.rxjava2_demo_2


import android.os.Handler
import android.util.Log
import java.util.concurrent.Executors

internal class CallbackFramework {

    private val executorService = Executors.newSingleThreadExecutor()
    private val mHandler = Handler()

    internal interface Callback {
        fun onSuccess()
    }

    fun returnOnMainThread(callback: Callback) {
        mHandler.postDelayed({ callback.onSuccess() }, 300)
    }

    fun returnOnBackgroundThread(callback: Callback) {
        Log.v("Eg:CallbackFramework:30", "returnOnBackgroundThread")

        executorService.execute { callback.onSuccess() }
    }
}
