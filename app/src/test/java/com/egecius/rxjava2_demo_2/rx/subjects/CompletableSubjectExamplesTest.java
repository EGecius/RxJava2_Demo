package com.egecius.rxjava2_demo_2.rx.subjects;

import static com.egecius.rxjava2_demo_2.rx.utils.TestingUtils.setUncaughtExceptionsHandlerAsStrict;

import com.egecius.rxjava2_demo_2.rx.EgisException;
import com.egecius.rxjava2_demo_2.rx.utils.UncaughtException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.observers.TestObserver;

@RunWith(MockitoJUnitRunner.class)
public class CompletableSubjectExamplesTest {

    private CompletableSubjectExamples mSut;

    @Before
    public void setUp() {
        setUncaughtExceptionsHandlerAsStrict();
        mSut = new CompletableSubjectExamples();
    }

    @Test (expected = UncaughtException.class)
    public void failsWhenTwoErrorsEmitted() {
        TestObserver<Void> testObserver = mSut.getCompletableSubject().test();
        mSut.emit2Errors();

        testObserver.assertError(EgisException.class);
        //has failed with UndeliverableException
    }

    @Test
    public void succeedsWhenOnly1ErrorEmitted() {
        TestObserver<Void> testObserver = mSut.getCompletableSubject().test();
        mSut.emit1Error();

        testObserver.assertError(EgisException.class);
    }

}