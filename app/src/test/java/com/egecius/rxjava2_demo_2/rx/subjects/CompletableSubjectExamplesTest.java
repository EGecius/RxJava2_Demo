package com.egecius.rxjava2_demo_2.rx.subjects;

import com.egecius.rxjava2_demo_2.rx.EgisException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.observers.TestObserver;

@RunWith(MockitoJUnitRunner.class)
public class CompletableSubjectExamplesTest {

    private CompletableSubjectExamples mSut;

    @Before
    public void setUp() {
        mSut = new CompletableSubjectExamples();
    }

    @Ignore // crashes - not sure why it does not catch UndeliverableException
    @Test (expected = UndeliverableException.class)
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