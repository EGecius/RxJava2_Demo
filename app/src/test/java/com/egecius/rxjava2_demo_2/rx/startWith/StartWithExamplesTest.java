package com.egecius.rxjava2_demo_2.rx.startWith;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import io.reactivex.observers.TestObserver;

@RunWith(MockitoJUnitRunner.class)
public class StartWithExamplesTest {

    private StartWithExamples mSut;

    @Before
    public void setUp() {
        mSut = new StartWithExamples();
    }

    @Test
    public void startWithAppendsValues() {
        List<Integer> list = Arrays.asList(3, 14);

        TestObserver<Integer> testObserver = mSut.startWith(list).test();

        testObserver
                .assertNoErrors()
                .assertComplete()
                .assertValues(3, 14, 1, 2, 3);
    }

}