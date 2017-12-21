package com.egecius.rxjava2_demo_2.rx.retry;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
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
        TestObserver<Integer> testObserver = mSut.retryWhenTimes(5).test();

        assertThat(mSut.getSubscribeCalled()).isEqualTo(5);
        testObserver.assertError(NoSuchElementException.class);
    }

    @Test
    public void retry() {
        mSut.retryTimes(5).test();

        assertThat(mSut.getSubscribeCalled()).isEqualTo(6);
    }

    @Test
    public void retryWhen_withZip() {

        mSut.retryWhenWithZipTimes(5).test();

        assertThat(mSut.getSubscribeCalled()).isEqualTo(6);

    }



}