package com.egecius.rxjava2_demo_2.rx.subjects;

import static org.assertj.core.api.Assertions.assertThat;

import static java.util.Arrays.asList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.observers.TestObserver;
import io.reactivex.subjects.BehaviorSubject;

@RunWith(MockitoJUnitRunner.class)
public class BehaviorSubjectExamplesTest {

    private BehaviorSubjectExamples mSut;
    private static final Exception EXCEPTION = new Exception();

    @Before
    public void setUp() {
        mSut = new BehaviorSubjectExamples();
    }

    @Test
    public void cachesLastKnownValue() {
        BehaviorSubject<Integer> subject = mSut.emit(asList(1, 2, 3));

        TestObserver<Integer> testObserver = subject.test();

        testObserver
                .assertNotTerminated()
                .assertValue(3);
    }

    @Test
    public void whenSubscribedCompletedSubjectNoEventsAreEmitted() {
        BehaviorSubject<Integer> subject = mSut.emitWithOnComplete(asList(1, 2, 3));

        TestObserver<Integer> testObserver = subject.test();

        testObserver
                .assertComplete()
                .assertNoValues();
    }

    @Test
    public void whenSubscribedToFailedSubjectNoEventsAreEmitted() {
        BehaviorSubject<Integer> subject = mSut.emitWithError(asList(1, 2, 3), EXCEPTION);

        TestObserver<Integer> testObserver = subject.test();

        testObserver
                .assertError(EXCEPTION)
                .assertNoValues();
    }

    @Test
    public void whenSubscribesBeforeOnCompleteReceivesPriorEvents() {
        BehaviorSubject<Integer> subject = mSut.emit(asList(1, 2, 3));

        TestObserver<Integer> testObserver = subject.test();

        subject.onNext(14);
        subject.onNext(15);
        subject.onComplete();

        testObserver
                .assertComplete()
                .assertValues(3, 14, 15);
    }

    @Test
    public void whenSubscribesBeforeErrorReceivesPriorEvents() {
        BehaviorSubject<Integer> subject = mSut.emit(asList(1, 2, 3));

        TestObserver<Integer> testObserver = subject.test();

        subject.onNext(14);
        subject.onNext(15);
        subject.onError(EXCEPTION);

        testObserver
                .assertValues(3, 14, 15)
                .assertNotComplete()
                .assertError(EXCEPTION);
    }

    @Test
    public void getValueReturnsCachedEvent() {
        BehaviorSubject<Integer> subject = mSut.emit(asList(1, 2, 3));

        assertThat(subject.getValue()).isEqualTo(3);

        subject.onNext(14);

        assertThat(subject.getValue()).isEqualTo(14);

        subject.onNext(15);

        assertThat(subject.getValue()).isEqualTo(15);
    }

}