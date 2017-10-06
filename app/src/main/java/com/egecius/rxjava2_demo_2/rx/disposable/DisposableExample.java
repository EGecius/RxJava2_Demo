package com.egecius.rxjava2_demo_2.rx.disposable;


import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class DisposableExample {

    /** Inside subscribeWith() you can see that it returns same observer that you just passed */
    void demoDDisposableObserver() {

        DisposableObserver<Integer> disposableObserver = Observable.just(1)
                .subscribeWith(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(@NonNull Integer integer) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
