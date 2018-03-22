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
public class MaybeSubjectExamplesTest {

    private MaybeSubjectExamples mSut;

    @Before
    public void setUp() {
        setUncaughtExceptionsHandlerAsStrict();
        mSut = new MaybeSubjectExamples();
    }

    @Test
    public void succeedsWhenOnlyOneErrorEmitted() {
        TestObserver<String> testObserver = mSut.getMaybeSubject().test();

        mSut.emitSingleError();

        testObserver.assertError(EgisException.class);
    }


    @Test (expected = UncaughtException.class)
    public void succeedsWhen2ErrorEmitted() {
        TestObserver<String> testObserver = mSut.getMaybeSubject().test();

        mSut.emit2Errors();

        testObserver.assertError(EgisException.class);
    }

}