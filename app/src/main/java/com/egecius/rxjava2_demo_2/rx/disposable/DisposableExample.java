package com.egecius.rxjava2_demo_2.rx.disposable;


import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class DisposableExample {

    private DisposableObserver<Integer> mDisposableObserver;

    private Throwable mThrowable;
    private final ArrayList<Integer> onNextList = new ArrayList<>();
    private boolean isOnCompleteCalled;


    /** Inside subscribeWith() you can see that it returns same observer that you just passed */
    DisposableObserver<Integer> createDisposableObserver() {

        mDisposableObserver = Observable.<Integer>never()
                .subscribeWith(new DisposableObserver<Integer>() {

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        onNextList.add(integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        mThrowable = throwable;
                    }

                    @Override
                    public void onComplete() {
                        isOnCompleteCalled = true;
                    }
                });

        return mDisposableObserver;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }

    public ArrayList<Integer> getOnNextList() {
        return onNextList;
    }

    public boolean isOnCompleteCalled() {
        return isOnCompleteCalled;
    }
}
