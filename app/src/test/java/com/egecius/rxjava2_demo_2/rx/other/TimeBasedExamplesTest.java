package com.egecius.rxjava2_demo_2.rx.other;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

@RunWith(MockitoJUnitRunner.class)
public class TimeBasedExamplesTest {

    private TimeBasedExamples mSut;

    @Before
    public void setUp() {
        mSut = new TimeBasedExamples();
    }

    @Test
    public void timer() throws InterruptedException {
        TestObserver<Long> testObserver = Observable.timer(10, MILLISECONDS).test();

        testObserver.await(20, MILLISECONDS);
        testObserver.assertResult(0L);
    }

    @Test
    public void delay() throws InterruptedException {

        TestObserver<String> testObserver = Observable.just("one")
                .delay(10, MILLISECONDS).test();

        testObserver.await(20, MILLISECONDS);
        testObserver.assertResult("one");
    }

}