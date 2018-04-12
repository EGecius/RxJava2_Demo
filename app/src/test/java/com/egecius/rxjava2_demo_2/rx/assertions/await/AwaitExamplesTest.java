package com.egecius.rxjava2_demo_2.rx.assertions.await;

import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

public class AwaitExamplesTest {

    // test fails when delay is added without calling awaitX() on TestObserver
    @Test(expected = AssertionError.class)
    public void delay() {
        TestObserver<Integer> testObserver = Observable
                .just(1)
                .delay(1, TimeUnit.NANOSECONDS)
                .test();

        testObserver.assertResult(1);
    }

    @Test
    public void await() throws InterruptedException {
        TestObserver<Integer> testObserver = Observable
                .just(1)
                .delay(1, TimeUnit.SECONDS)
                .test();

        testObserver.await();

        testObserver.assertResult(1);
    }

    @Test
    public void await2() throws InterruptedException {
        TestObserver<Integer> testObserver = Observable
                .just(1)
                .delay(1, TimeUnit.SECONDS)
                .test();

        testObserver.await(2, TimeUnit.SECONDS);

        testObserver.assertResult(1);
    }


    // awaitTerminalEvent(): await() + returns false in case of InterruptedException
    @Test
    public void awaitTerminalEvent() throws InterruptedException {
        TestObserver<Integer> testObserver = Observable
                .just(1)
                .delay(1, TimeUnit.SECONDS)
                .test();

        testObserver.awaitTerminalEvent();

        testObserver.assertResult(1);
    }

    @Test
    public void awaitCount() throws InterruptedException {
        TestObserver<Integer> testObserver = Observable
                .just(1, 2, 3)
                .delay(1, TimeUnit.SECONDS)
                .test();

        testObserver.awaitCount(3);

        testObserver.assertResult(1, 2, 3);
    }

    @Test @Ignore
    public void clearTimeout() throws InterruptedException {
        TestObserver<Integer> testObserver = Observable
                .just(1, 2, 3)
                .delay(1, TimeUnit.SECONDS)
                .test();

        testObserver.isTimeout();

        testObserver.assertResult(1, 2, 3);
    }

}
