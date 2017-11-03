package com.egecius.rxjava2_demo_2.rx.subjects;

import static java.util.Arrays.asList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.observers.TestObserver;
import io.reactivex.subjects.ReplaySubject;

@RunWith(MockitoJUnitRunner.class)
public class SubjectsExamplesTest {

    private SubjectsExamples mSut;

    @Before
    public void setUp() {
        mSut = new SubjectsExamples();
    }

    @Test
    public void subscribeToDefault() {
        ReplaySubject<Integer> subject = mSut.defaultReplaySubject(asList(11, 12, 13));

        TestObserver<Integer> testObserver = subject.test();

        testObserver
                .assertComplete()
                .assertNoErrors()
                .assertValues(11, 12, 13);
    }

    @Test
    public void sizedReplaySubjectEmitsLimitedNumberOfValues() {
        ReplaySubject<Integer> subject = mSut.createWithSize2(asList(1, 2, 3),
                (asList(11, 12, 13)));

        TestObserver<Integer> testObserver = subject.test();

        testObserver
                .assertComplete()
                .assertNoErrors()
                .assertValueCount(2);
    }

    /** For some reason Subject emits only last values from the first subscription - seems
     * counter-intuitive */
    @Test
    public void replaySubjectEmitsLastValues() {
        ReplaySubject<Integer> subject = mSut.createWithSize2(asList(1, 2, 3),
                (asList(11, 12, 13)));

        TestObserver<Integer> testObserver = subject.test();

        testObserver
                .assertValues(2, 3);
    }

    @Test
    public void createWithTime() throws InterruptedException {
        ReplaySubject<String> subject = mSut.createWithTime();

        TestObserver<String> testObserver = subject.test();

        testObserver
                .assertNoErrors()
                .assertValues("two", "three");
    }

}