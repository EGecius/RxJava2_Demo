package com.egecius.rxjava2_demo_2.rx.conditional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.observers.TestObserver;

@RunWith(MockitoJUnitRunner.class)
public class ConditionalExamplesTest {

    private ConditionalExamples mSut;

    @Before
    public void setUp() {
        mSut = new ConditionalExamples();
    }

    @Test
    public void ambTakesEmissionsOnlyFromTheSourceThatStartsEmittingFirst() {

        TestObserver<Integer> testObserver = mSut.ambExample().test();

        testObserver.assertResult(1, 2);
    }

}