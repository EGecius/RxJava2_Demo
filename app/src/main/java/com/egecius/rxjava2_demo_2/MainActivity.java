package com.egecius.rxjava2_demo_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private final MyRxDelegate mMyRxDelegate = new MyRxDelegate();

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//        emitErrorWithoutOnErrorHandling();
//        mMyRxDelegate.emitStreamWithFailure();

        mMyRxDelegate.applyMultipleObservOn();
    }

}
