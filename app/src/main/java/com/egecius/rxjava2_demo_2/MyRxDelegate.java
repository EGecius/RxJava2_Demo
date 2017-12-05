package com.egecius.rxjava2_demo_2;

import android.util.Log;

import io.reactivex.Observable;

public class MyRxDelegate {
    public MyRxDelegate() {
    }

    void emitStreamWithFailure() {
        createFailingStream()
                .doOnNext(this::printDoOnNext)
                .doOnComplete(this::printDoOnComplete)
                .doOnError(this::printDoOnError)
                .subscribe(this::handleOnNext, this::handleError);
    }

    Observable<Integer> createFailingStream() {
        return Observable.create(e -> {
            for (int i = 0; i < 3; i++) {
                e.onNext(i);
            }
            e.onError(new Exception("Egis"));
        });
    }

    private void handleError(Throwable throwable) {
        Log.w("Eg:MainActivity:32", "handleError throwable " + throwable);
    }

    private void handleOnNext(Integer integer) {
        Log.i("Eg:MainActivity:36", "handleOnNext integer " + integer);
    }

    private int printDoOnError(Throwable throwable) {
        return Log.w("Eg:MainActivity:32", "printDoOnError throwable " + throwable);
    }

    private int printDoOnComplete() {
        return Log.i("Eg:MainActivity:35", "doOnComplete ");
    }

    private void printDoOnNext(Integer integer) {
        Log.i("Eg:MainActivity:34", "printDoOnNext integer " + integer);
    }

    /** This will crash the app because rxJava 2 not only prints the stracktrace but also
     * forwards uncaught exception to current thread's error handler. On Android this is very
     * strict and crashes the app */
    private void emitErrorWithoutOnErrorHandling() {
        Observable.error(new RuntimeException())
                .subscribe();
    }
}