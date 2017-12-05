package com.egecius.rxjava2_demo_2.rx.assertions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

@RunWith(MockitoJUnitRunner.class)
public class AssertionsExamplesTest {

    private AssertionsExamples mSut;

    @Before
    public void setUp() {
        mSut = new AssertionsExamples();
    }

    @Test
    public void assertNotSubscribed() {
        TestObserver<Void> testObserver = new TestObserver<>();

        testObserver.assertNotSubscribed();
    }

    @Test
    public void assertSubscribed() {
        TestObserver<Integer> testObserver = mSut.getJust().test();

        testObserver.assertSubscribed();
    }

    @Test
    public void assertNoValues() {
        TestObserver<Object> testObserver = Observable.empty().test();

        testObserver.assertNoValues();
    }

    /** assertEmpty() assertion check for any events, including onComplete */
    @Test (expected = AssertionError.class)
    public void assertEmpty1() {
        TestObserver<Object> testObserver = Observable.empty().test();

        testObserver.assertEmpty();
    }

    /** assertEmpty() assertion check for any events, including onError */
    @Test (expected = AssertionError.class)
    public void assertEmpty2() {
        TestObserver<Object> testObserver = Observable.error(new Exception()).test();

        testObserver.assertEmpty();
    }



}