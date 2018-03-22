package com.egecius.rxjava2_demo_2.rx.startWith;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

@RunWith(MockitoJUnitRunner.class)
public class FromCallableExamplesTest {

    private FromCallableExamples mSut;

    @Before
    public void setUp() {
        mSut = new FromCallableExamples();
    }

    @Test
    public void fromCallableDelaysExecutionTillSubscription() {
        Observable<String> observable = mSut.fromCallableThreadName();

        assertThat(mSut.isCallableExecuted()).isFalse();

        observable.test();

        assertThat(mSut.isCallableExecuted()).isTrue();
    }

}