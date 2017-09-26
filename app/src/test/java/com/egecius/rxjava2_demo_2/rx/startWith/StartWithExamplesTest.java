package com.egecius.rxjava2_demo_2.rx.startWith;

import static android.R.id.list;

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
    public void startWithObservable() {
        List<Integer> list = Arrays.asList(3, 14);

        TestObserver<Integer> testObserver = mSut.startWithObservable(list).test();

        testObserver
                .assertNoErrors()
                .assertComplete()
                .assertValues(3, 14, 1, 2, 3);
    }

    @Test
    public void startWithIterable() {
        List<Integer> list = Arrays.asList(3, 14);

        TestObserver<Integer> testObserver = mSut.startWithIterable(list).test();

        testObserver
                .assertNoErrors()
                .assertComplete()
                .assertValues(3, 14, 1, 2, 3);
    }

    @Test
    public void startWithArray() {
        Integer[] array = {3, 14};

        TestObserver<Integer> testObserver = mSut.startWithArray(array).test();

        testObserver
                .assertNoErrors()
                .assertComplete()
                .assertValues(3, 14, 1, 2, 3);
    }

    @Test
    public void startWithElement() {
        TestObserver<Integer> testObserver = mSut.startWithElement(14).test();

        testObserver
                .assertNoErrors()
                .assertComplete()
                .assertValues(14, 1, 2, 3);
    }

}