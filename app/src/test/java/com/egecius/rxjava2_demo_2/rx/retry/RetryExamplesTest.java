package com.egecius.rxjava2_demo_2.rx.retry;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RetryExamplesTest {

    private RetryExamples mSut;

    @Before
    public void setUp() {
        mSut = new RetryExamples();
    }

    @Test
    public void _() {
        mSut.retryWhen_retries5Tmes().test();

        assertThat(mSut.subscribeCalled).isEqualTo(5);
    }

}