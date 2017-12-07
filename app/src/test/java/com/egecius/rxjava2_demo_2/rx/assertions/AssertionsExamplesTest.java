package com.egecius.rxjava2_demo_2.rx.assertions;

import static org.assertj.core.api.Assertions.assertThat;

import static java.util.Arrays.asList;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

@RunWith(MockitoJUnitRunner.class)
public class AssertionsExamplesTest {

    private static final String EXCEPTION_MESSAGE = "EXCEPTION_MESSAGE";
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
    @Test(expected = AssertionError.class)
    public void assertEmpty1() {
        TestObserver<Object> testObserver = Observable.empty().test();

        testObserver.assertEmpty();
    }

    /** assertEmpty() assertion check for any events, including onError */
    @Test(expected = AssertionError.class)
    public void assertEmpty2() {
        TestObserver<Object> testObserver = Observable.error(new Exception()).test();

        testObserver.assertEmpty();
    }

    @Test
    public void assertErrorOfClass() {
        TestObserver<Object> testObserver = Observable.error(new EgisException("message")).test();

        testObserver.assertError(EgisException.class);
    }

    @Test(expected = AssertionError.class)
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

    /**
     * assertError compares using equals, so instance with same equals() value satisfies
     * assertion
     */
    @Test
    public void assertErrorOfObject2() {
        TestObserver<Object> testObserver = Observable.error(new EgisException("message")).test();

        testObserver.assertError(new EgisException("message"));
    }

    /** This assertion fails since Exception does not implement equals() */
    @Test(expected = AssertionError.class)
    public void assertErrorOfObject3() {
        TestObserver<Object> testObserver = Observable.error(new Exception("message")).test();

        testObserver.assertError(new Exception("message"));
    }

    /**
     * This is a workaround for an Exception that does not implement equals. We can get access
     * to it via Predicate and run our own assertions on it
     */
    @Test
    public void assertErrorOfObject4() {
        TestObserver<Object> testObserver = Observable.error(
                new Exception(EXCEPTION_MESSAGE)).test();

        testObserver.assertError(new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) throws Exception {
                return throwable.getMessage().equals(EXCEPTION_MESSAGE);
            }
        });
    }

    @Test
    public void assertErrorMessage() {
        TestObserver<Object> testObserver = Observable.error(
                new Exception(EXCEPTION_MESSAGE)).test();

        testObserver.assertErrorMessage(EXCEPTION_MESSAGE);
    }

    @Test
    public void assertFailure() {
        TestObserver<Integer> testObserver = mSut.emitThreeIntegersAndFail().test();

        testObserver.assertFailure(EgisException.class, 0, 1, 2);
    }

    @Test
    public void assertFailure2() {
        TestObserver<Integer> testObserver = mSut.emitThreeIntegersAndFail().test();

        testObserver.assertFailure(new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) throws Exception {
                return throwable == mSut.getException();
            }
        }, 0, 1, 2);
    }

    @Test
    public void assertFailureAndMessage() {
        TestObserver<Integer> testObserver = mSut.emitThreeIntegersAndFail().test();

        testObserver.assertFailureAndMessage(EgisException.class, mSut.getExceptionMessage(), 0, 1,
                2);
    }

    @Test
    public void assertNever() {
        TestObserver<Integer> testObserver = mSut.emitThreeIntegersAndFail().test();

        testObserver.assertNever(-1);
    }

    @Test(expected = AssertionError.class)
    public void assertNever2() {
        TestObserver<Integer> testObserver = mSut.emitThreeIntegersAndFail().test();

        testObserver.assertNever(0);
    }

    @Test
    public void assertNoTimeout() {
        TestObserver<Object> testObserver = Observable.empty().test();

        testObserver.assertNoTimeout();
    }


    @Test
    public void assertResult() {
        TestObserver<Integer> testObserver = Observable.just(1, 2).test();

        testObserver.assertResult(1, 2);
    }

    @Test(expected = AssertionError.class)
    public void assertResult2() {
        TestObserver<Integer> testObserver = mSut.emitWithoutCompletion(1, 2).test();

        testObserver.assertResult(1, 2);
    }

    @Test
    public void assertValues() {
        TestObserver<Integer> testObserver = mSut.emitWithoutCompletion(1, 2).test();

        testObserver.assertValues(1, 2);
    }


    @Test
    public void assertValueAt() {
        TestObserver<Integer> testObserver = mSut.emitWithoutCompletion(1, 2).test();

        testObserver.assertValueAt(1, 2);
    }

    @Test(expected = AssertionError.class)
    public void assertValueAt2() {
        TestObserver<Integer> testObserver = mSut.emitWithoutCompletion(1, 2).test();

        testObserver.assertValueAt(0, 0);
    }

    @Test(expected = AssertionError.class)
    public void assertValueAt3() {
        TestObserver<Integer> testObserver = mSut.emitWithoutCompletion(1, 2).test();

        testObserver.assertValueAt(2, 2);
    }

    @Test
    public void assertValueSequence() {
        TestObserver<Integer> testObserver = mSut.emitWithoutCompletion(1, 2).test();

        testObserver.assertValueSequence(asList(1, 2));
    }

    // fails when same order is not maintained
    @Test(expected = AssertionError.class)
    public void assertValueSequence2() {
        TestObserver<Integer> testObserver = mSut.emitWithoutCompletion(1, 2).test();

        testObserver.assertValueSequence(asList(2, 1));
    }

    @Test
    public void assertValueSet() {
        TestObserver<Integer> testObserver = mSut.emitWithoutCompletion(1, 2).test();

        testObserver.assertValueSet(asList(1, 2));
    }

    // assertValueSet takes Collection in any order
    @Test
    public void assertValueSet2() {
        TestObserver<Integer> testObserver = mSut.emitWithoutCompletion(1, 2).test();

        testObserver.assertValueSet(asList(2, 1));
    }

    @Test
    public void assertValuesOnly() {
        TestObserver<Integer> testObserver = mSut.emitWithoutCompletion(1, 2).test();

        testObserver.assertValuesOnly(1, 2);
    }

    @Test(expected = AssertionError.class)
    public void assertValuesOnly2() {
        TestObserver<Integer> testObserver = Observable.just(1, 2).test();

        testObserver.assertValuesOnly(1, 2);
    }

    @Test
    public void completions() {
        TestObserver<Integer> testObserver = Observable.just(1, 2).test();

        assertThat(testObserver.completions()).isEqualTo(1);
    }

    // TODO: 06/12/2017 can one Obseerver subscribe to multiple Observables?
    @Test
    @Ignore // I thought this would work but it does not.
    public void completion2() {
        Observable<Integer> observable1 = Observable.just(1);
        Observable<Integer> observable2 = Observable.just(2);
        Observable<Integer> observable3 = Observable.just(3);

        TestObserver<Integer> testObserver = new TestObserver<>();
        observable1.subscribe(testObserver);
        observable2.subscribe(testObserver);
        observable3.subscribe(testObserver);

        assertThat(testObserver.completions()).isEqualTo(2);
    }

    @Test
    public void errorCount() {
        TestObserver<Object> testObserver = Observable.error(new Exception()).test();

        assertThat(testObserver.errorCount()).isEqualTo(1);
    }

    @Test
    public void errors() {
        Exception exception = new Exception();
        TestObserver<Object> testObserver = Observable.error(exception).test();

        List<Throwable> errors = testObserver.errors();
        assertThat(errors.size()).isEqualTo(1);
        assertThat(errors.get(0)).isEqualTo(exception);
    }

    @Test
    public void getEvents() {
        TestObserver<Integer> testObserver = Observable.just(33, 44).test();

        List<List<Object>> events = testObserver.getEvents();
        assertThat(events.size()).isEqualTo(3);
        assertThat(events.get(0)).isEqualTo(asList(33, 44));
        assertThat(events.get(1)).isEqualTo(Collections.emptyList());
        // last one is list of OnCompleteNotification - compiler can't find it
//        assertThat(events.get(2)).isEqualTo(asList(new OnCompleteNotification()));
    }

    @Test
    public void getEvents2() {
        Exception exception = new Exception();
        TestObserver<Object> testObserver = Observable.error(exception).test();

        List<List<Object>> events = testObserver.getEvents();
        assertThat(events.size()).isEqualTo(3);
        assertThat(events.get(0)).isEqualTo(Collections.emptyList());
        assertThat(events.get(1)).isEqualTo(Collections.singletonList(exception));
        // last one is list of OnCompleteNotification - compiler can't find it
//        assertThat(events.get(2)).isEqualTo(asList(new OnCompleteNotification()));
    }

    @Test
    public void lastThread() {
        TestObserver<Integer> testObserver = Observable.just(1).test();

        Thread thread = testObserver.lastThread();
        assertThat(thread.getName()).isEqualTo("main");
    }

    @Ignore // when this test fails, you can see tag in its logs
    @Test
    public void withTag() {
        TestObserver<Integer> testObserver = Observable.just(1).test();

        testObserver
                .withTag("egis_tag")
                .assertValue(2);
    }

}