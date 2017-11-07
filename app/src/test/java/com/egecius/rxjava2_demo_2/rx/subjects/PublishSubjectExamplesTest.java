package com.egecius.rxjava2_demo_2.rx.subjects;

import static java.util.Arrays.asList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.observers.TestObserver;
import io.reactivex.subjects.PublishSubject;

@RunWith(MockitoJUnitRunner.class)
public class PublishSubjectExamplesTest {

    private static final Exception EXCEPTION = new Exception();
    private PublishSubjectExamples mSut;

    @Before
    public void setUp() {
        mSut = new PublishSubjectExamples();
    }

    @Test
    public void emitsNoEventsWhenTheyEmittedBeforeSubscription() {
        PublishSubject<Integer> subject = mSut.emitsEventsAndOnComplete(asList(1, 2, 3));

        TestObserver<Integer> testObserver = subject.test();

        testObserver
                .assertComplete()
                .assertNoValues();
    }

    @Test
    public void emitsValuesReceivedAfterSubscription() {
        PublishSubject<Integer> subject = PublishSubject.create();

        TestObserver<Integer> testObserver = subject.test();

        subject.onNext(1);
        subject.onNext(2);

        testObserver
                .assertNotTerminated()
                .assertValues(1, 2);
    }

    @Test
    public void emitsValuesReceivedAfterSubscriptionAndTerminates() {
        PublishSubject<Integer> subject = PublishSubject.create();

        TestObserver<Integer> testObserver = subject.test();

        subject.onNext(1);
        subject.onNext(2);
        subject.onComplete();

        testObserver
                .assertComplete()
                .assertValues(1, 2);
    }

    @Test
    public void emitsValueReceivedBeforeError() {
        PublishSubject<Integer> subject = PublishSubject.create();

        TestObserver<Integer> testObserver = subject.test();

        subject.onNext(1);
        subject.onNext(2);
        subject.onError(EXCEPTION);

        testObserver
                .assertError(EXCEPTION)
                .assertValues(1, 2);
    }

}