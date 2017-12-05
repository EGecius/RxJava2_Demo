package com.egecius.rxjava2_demo_2.rx.assertions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

@RunWith(MockitoJUnitRunner.class)
public class AssertionsExamplesTest {

    private static final String EXCEPTION_MESSAGE = "message";
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

    @Test
    public void assertErrorOfClass() {
        TestObserver<Object> testObserver = Observable.error(new EgisException("message")).test();

        testObserver.assertError(EgisException.class);
    }

    @Test (expected = AssertionError.class)
    public void assertErrorOfClass2() {
        TestObserver<Object> testObserver = Observable.error(new EgisException("message")).test();

        testObserver.assertError(IllegalArgumentException.class);
    }

    /** assertError compares using equals, so passing same instance satisfies assertion */
    @Test
    public void assertErrorOfObject() {
        EgisException exception = new EgisException("message");
        TestObserver<Object> testObserver = Observable.error(exception).test();

        testObserver.assertError(exception);
    }

    /** assertError compares using equals, so instance with same equals() value satisfies
     * assertion */
    @Test
    public void assertErrorOfObject2() {
        TestObserver<Object> testObserver = Observable.error(new EgisException("message")).test();

        testObserver.assertError(new EgisException("message"));
    }

    /** This assertion fails since Exception does not implement equals() */
    @Test (expected = AssertionError.class)
    public void assertErrorOfObject3() {
        TestObserver<Object> testObserver = Observable.error(new Exception("message")).test();

        testObserver.assertError(new Exception("message"));
    }

    /** This is a workaround for an Exception that does not implement equals. We can get access
     * to it via Predicate and run our own assertions on it */
    @Test
    public void assertErrorOfObject4() {
        TestObserver<Object> testObserver = Observable.error(new Exception(EXCEPTION_MESSAGE)).test();

        testObserver.assertError(new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) throws Exception {
                return throwable.getMessage().equals(EXCEPTION_MESSAGE);
            }
        });
    }

}