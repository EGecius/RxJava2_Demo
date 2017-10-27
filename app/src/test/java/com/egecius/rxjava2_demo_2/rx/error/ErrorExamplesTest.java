package com.egecius.rxjava2_demo_2.rx.error;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;

@RunWith(MockitoJUnitRunner.class)
public class ErrorExamplesTest {

    @SuppressWarnings("ThrowableInstanceNeverThrown")
    private static final Exception EXCEPTION = new Exception();

    private ErrorExamples mSut;

    @Before
    public void setUp() {
        mSut = new ErrorExamples();
    }

    @Test
    public void error() {
        TestSubscriber<Object> testSubscriber = Flowable.error(EXCEPTION).test();

        testSubscriber
                .assertTerminated()
                .assertNotComplete()
                .assertNoValues()
                .assertError(EXCEPTION);
    }

}