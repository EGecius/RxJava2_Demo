package com.egecius.rxjava2_demo_2.rx.errors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;


@RunWith(MockitoJUnitRunner.class)
public class ErrorExamplesTest {

    private void setCrashingUncaughtExceptionHandler() {
        Thread.currentThread().setUncaughtExceptionHandler((thread, throwable) -> {
            throw new IllegalStateException(throwable);
        });
    }

    // when error is emitted but onError does not get implemented, execution does not crash on
    // Desktop but crashes on more Strict Android
    //
    // What happens is:
    // 1) exception is wrapped in OnErrorNotImplementedException and printed at the end of
    // 2) RxJavaPlugins.onError()
    // 3) passed to UncaughtExceptionHandler interface
    // 4) which passes exception to current thread's UncaughtExceptionHandler. On desktop it
    // does not crash app but on Android it does

    @Test(expected = RuntimeException.class)
    public void Observable_Error() {
        setCrashingUncaughtExceptionHandler();

        Observable<Object> observable = Observable.error(new RuntimeException());

        //intentionally do not implement onError to see if it will fail
        observable.subscribe(o -> {
            //nothing
        });

        // if execution has not failed by this point, test has passed
    }

    @Test
    public void Observable_Error_doesNotFailWhenOnErrorImplemented() {
        setCrashingUncaughtExceptionHandler();

        Observable<Object> observable = Observable.error(new RuntimeException());

        //intentionally do not implement onError to see if it will fail
        observable.subscribe(o -> {
            //nothing
        }, throwable -> {
            //implements onError
        });

        // if execution has not failed by this point, test has passed
    }


    @Test(expected = RuntimeException.class)
    public void Single_Error() {
        setCrashingUncaughtExceptionHandler();

        Single<Object> single = Single.error(new RuntimeException());

        //intentionally do not implement onError to see if it will fail
        single.subscribe(o -> {
            //nothing
        });

        // if execution has not failed by this point, test has passed
    }


    @Test(expected = RuntimeException.class)
    public void Completable_Error() {
        setCrashingUncaughtExceptionHandler();

        Completable completable = Completable.error(new RuntimeException());

        //intentionally do not implement onError to see if it will fail
        completable.subscribe(new Action() {
            @Override
            public void run() throws Exception {
                // nothing
            }
        });

        // if execution has not failed by this point, test has passed
    }

    @Test(expected = RuntimeException.class)
    public void Maybe_Error() {
        setCrashingUncaughtExceptionHandler();

        Maybe maybe = Maybe.error(new RuntimeException());

        //intentionally do not implement onError to see if it will fail
        maybe.subscribe(new Consumer() {
            @Override
            public void accept(@NonNull Object o) throws Exception {

            }
        });

        // if execution has not failed by this point, test has passed
    }

}