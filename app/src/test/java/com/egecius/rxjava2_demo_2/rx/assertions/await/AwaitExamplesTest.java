package com.egecius.rxjava2_demo_2.rx.assertions.await;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

public class AwaitExamplesTest {

    // test fails when delay is added without calling awaitX() on TestObserver
    @Test (expected = AssertionError.class)
    public void delay() {
        Observable<Integer> observable = Observable.just(1).delay(1, TimeUnit.NANOSECONDS);

        TestObserver<Integer> testObserver = observable.test();

        testObserver.assertResult(1);
    }
}
