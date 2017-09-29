package com.egecius.rxjava2_demo_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.Observable;


public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        emitErrorWithoutOnErrorHandling();
    }

    /** This will crash the app because rxJava 2 not only prints the stracktrace but also
     * forwards uncaught exception to current thread's error handler. On Android this is very
     * strict and crashes the app */
    private void emitErrorWithoutOnErrorHandling() {
        Observable.error(new RuntimeException())
                .subscribe();
    }
}
