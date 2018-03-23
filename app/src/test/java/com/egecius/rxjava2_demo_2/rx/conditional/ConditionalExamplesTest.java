package com.egecius.rxjava2_demo_2.rx.conditional;

import static java.util.Arrays.asList;

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

    @Test
    public void switchIfEmptyProvidesDefaultForEmptyStream() {

        TestObserver<Integer> testObserver
                = mSut.switchIfEmptyForEmptyStream(asList(1, 2, 3)).test();

        testObserver.assertResult(1, 2, 3);
    }

    @Test
    public void switchIfEmptyDefaultIsIgnoredForNonEmptyStream() {
        TestObserver<Integer> testObserver
                = mSut.switchIfEmptyForNonEmptyStream(asList(1, 2, 3)).test();

        testObserver.assertResult(1);

    }


}