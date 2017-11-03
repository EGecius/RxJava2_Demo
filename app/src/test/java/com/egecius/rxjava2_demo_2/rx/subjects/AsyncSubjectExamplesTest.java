package com.egecius.rxjava2_demo_2.rx.subjects;

import static com.egecius.rxjava2_demo_2.rx.subjects.AsyncSubjectExamples.ASYNC_EXCEPTION;

import static java.util.Arrays.asList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.observers.TestObserver;
import io.reactivex.subjects.AsyncSubject;

@RunWith(MockitoJUnitRunner.class)
public class AsyncSubjectExamplesTest {

    private AsyncSubjectExamples mSut;

    @Before
    public void setUp() {
        mSut = new AsyncSubjectExamples();
    }

    @Test
    public void emitsOnlyLastEventBeforeTermination() {

        List<Integer> list = asList(1, 2, 3);
        AsyncSubject<Integer> subject = mSut.emitEventsWithOnComplete(list);

        TestObserver<Integer> testObserver = subject.test();

        testObserver
                .assertComplete()
                .assertNoErrors()
                .assertValue(3);
    }

    @Test
    public void emitsNoEventsWhenTerminatesWithError() {

        List<Integer> list = asList(1, 2, 3);
        AsyncSubject<Integer> subject = mSut.emitEventsWithError(list);

        TestObserver<Integer> testObserver = subject.test();

        testObserver
                .assertNoValues()
                .assertError(ASYNC_EXCEPTION);
    }

    @Test
    public void emitsNoValuesWithoutTermination() {

        List<Integer> list = asList(1, 2, 3);
        AsyncSubject<Integer> subject = mSut.emitEventsWithoutTermination(list);

        TestObserver<Integer> testObserver = subject.test();

        testObserver
                .assertNoValues()
                .assertNotTerminated();
    }

}