package com.egecius.rxjava2_demo_2.rx.other;

import com.egecius.rxjava2_demo_2.rx.utils.TestingUtils;

import org.junit.Test;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ExceptionExamples {

    @Test(expected = RuntimeException.class)
    public void failsWhenExceptionIsThrownInOnSuccess() {

        TestingUtils.setUncaughtExceptionsHandlerAsStrict();

        Completable.complete()
                .subscribe(() -> {
                    throw new RuntimeException();
                });
    }

    @Test
    public void succeedsWhenExceptionIsThrownInOnError() {

        TestingUtils.setUncaughtExceptionsHandlerAsStrict();

        Completable.complete()
                .subscribe(() -> {
                    //do nothing
                }, throwable -> {
                    throw new RuntimeException();
                });
    }

    @Test(expected = RuntimeException.class)
    public void onSubscribeFailure() {

        Observable.just(1)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        throw new RuntimeException();
                    }

                    @Override
                    public void onNext(Integer integer) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Test(expected = RuntimeException.class)
    public void onNextFailure() {

        Observable.just(1)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Integer integer) {
                        throw new RuntimeException();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Test
    public void onErrorFailure() {

        Observable.just(1)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Integer integer) {
                    }

                    @Override
                    public void onError(Throwable e) {
                        throw new RuntimeException();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Test(expected = RuntimeException.class)
    public void onCompleteFailure() {

        Observable.just(1)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        throw new RuntimeException();
                    }
                });
    }
}
