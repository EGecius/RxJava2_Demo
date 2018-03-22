package com.egecius.rxjava2_demo_2.rx.scheduler;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subjects.PublishSubject;

@RunWith(MockitoJUnitRunner.class)
public class TestSchedulerExamplesTest {

    @Test
    public void advancesTime() {
        PublishSubject<String> publishSubject = PublishSubject.create();
        TestScheduler scheduler = new TestScheduler();
        TestObserver<String> testObserver = publishSubject.delay(1000, MILLISECONDS,
                scheduler).test();

        testObserver.assertEmpty();

        // no emissions since there is delay
        publishSubject.onNext("egis");
        testObserver.assertEmpty();

        // still no emissions since delay is 1ms longer than we are advancing time by
        scheduler.advanceTimeBy(999, MILLISECONDS);
        testObserver.assertEmpty();

        // after finally advancing time to same as delay, we are getting our emission
        scheduler.advanceTimeBy(1, MILLISECONDS);
        testObserver.assertValue("egis");
    }


}