package com.egecius.rxjava2_demo_2.rx.blockable;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BlockableExamplesTest {

    private BlockableExamples mSut;

    @Before
    public void setUp() {
        mSut = new BlockableExamples();
    }

    /** Blocking get turns asynchronous operation into synchronous */
    @Test
    public void blockingGetForSingle() {
        Integer integer = mSut.blockingGetForSingle();

        assertThat(integer).isEqualTo(1);
    }

    /** Blocking get turns asynchronous operation into synchronous */
    @Test
    public void blockingGetForObservable() {
        Integer integer = mSut.blockingGetForObservable();

        assertThat(integer).isEqualTo(1);
    }


}