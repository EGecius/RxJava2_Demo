package com.egecius.rxjava2_demo_2.rx.startWith;

import java.util.concurrent.Callable;

import io.reactivex.Observable;

public class FromCallableExamples {

    static final String STRING_FOR_CALLABLE = "string for callable";
    private boolean isCallableExecuted;

    Observable<String> fromCallableThreadName() {
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                isCallableExecuted = true;
                return getStringForCallable();
            }
        });
    }

    private String getStringForCallable() {
        return STRING_FOR_CALLABLE;
    }

    public boolean isCallableExecuted() {
        return isCallableExecuted;
    }
}
