package com.egecius.rxjava2_demo_2.rx.range;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

@RunWith(MockitoJUnitRunner.class)
public class RangeExampleTest {

    private RangeExample mSut;

    @Before
    public void setUp() {
        mSut = new RangeExample();
    }

    @Test
    public void rangeObservable() {

        TestObserver<Integer> testObserver = Observable.range(5, 3).test();

        testObserver
                .assertComplete()
                .assertComplete()
                .assertValues(5, 6, 7);

    }

    @Test
    public void rangeFlowable() {

        TestSubscriber<Integer> testSubscriber = Flowable.range(5, 3).test();

        testSubscriber
                .assertComplete()
                .assertComplete()
                .assertValues(5, 6, 7);

    }

}