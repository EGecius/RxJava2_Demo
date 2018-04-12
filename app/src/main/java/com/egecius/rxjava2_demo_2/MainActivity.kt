package com.egecius.rxjava2_demo_2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val mMyRxDelegate = MyRxDelegate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //        emitErrorWithoutOnErrorHandling();
        //        mMyRxDelegate.emitStreamWithFailure();

        mMyRxDelegate.applyMultipleObservOn()
    }

}
