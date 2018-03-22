package com.egecius.rxjava2_demo_2.rx.compose;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.observers.TestObserver;

@RunWith(MockitoJUnitRunner.class)
public class ComposeExamplesTest {

    private ComposeExamples mSut;

    @Before
    public void setUp() {
        mSut = new ComposeExamples();
    }

    @Test
    public void composeOperatorAllowsEasyReuseOfRxChains() {

        TestObserver<String> testObserver = mSut.doubleAndConvertToString(3).test();

        testObserver.assertResult("6");
    }

    @Test
    public void composeOperatorAllowsEasyReuseOfRxChains2() {

        TestObserver<String> testObserver = mSut.doubleAndConvertToStringRepeatedThrice(4).test();

        testObserver.assertResult("888");
    }

}