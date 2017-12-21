package com.egecius.rxjava2_demo_2.rx.retry;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.NoSuchElementException;

import io.reactivex.observers.TestObserver;

@RunWith(MockitoJUnitRunner.class)
public class RetryExamplesTest {

    private RetryExamples mSut;

    @Before
    public void setUp() {
        mSut = new RetryExamples();
    }

    @Test
    public void retryWhen() {
        TestObserver<Integer> testObserver = mSut.retryWhen(5).test();

        assertThat(mSut.getSubscribeCalled()).isEqualTo(5);
        testObserver
                .assertNoValues()
                .assertError(NoSuchElementException.class);
    }

    @Test
    public void retryWhenWithDelay() throws InterruptedException {
        TestObserver<Integer> testObserver = mSut.retryWhenWithDelay(5).test();

        testObserver.await();

        assertThat(mSut.getSubscribeCalled()).isEqualTo(6);
        testObserver
                .assertNoValues()
                .assertError(NoSuchElementException.class);
    }

    /** Test same as above but without 'await' fails without 'await' call */
    @Test (expected = ComparisonFailure.class)
    public void retryWhenWithDelayFailsWithoutAwait() throws InterruptedException {
        TestObserver<Integer> testObserver = mSut.retryWhenWithDelay(5).test();

//        testObserver.await();

        assertThat(mSut.getSubscribeCalled()).isEqualTo(6);
        testObserver
                .assertNoValues()
                .assertError(NoSuchElementException.class);
    }

    @Test
    public void retryWhenWithoutOnComplete() {
        TestObserver<Integer> testObserver = mSut.retryWithoutOnComplete(5).test();

        assertThat(mSut.getSubscribeCalled()).isEqualTo(6);
        testObserver
                .assertNoValues()
                .assertNoErrors()
                .assertNotComplete();
    }

    @Test
    public void retry() {
        TestObserver<Integer> testObserver = mSut.retryTimes(5).test();

        // subscribed 6 times because the first subscribe is before any retry
        assertThat(mSut.getSubscribeCalled()).isEqualTo(6);
        testObserver
                .assertNoValues()
                .assertError(Exception.class);
    }

    @Test
    public void retryWhen_withZip() {

        TestObserver<String> testObserver = mSut.retryWhenWithZipTimes(5).test();

        // subscribed 6 times because the first subscribe is before any retry
        assertThat(mSut.getSubscribeCalled()).isEqualTo(6);
        testObserver
                .assertNoValues();

        // TODO: 21/12/2017 assertError
    }


}