package com.egecius.rxjava2_demo_2.rx.subjects;

import com.egecius.rxjava2_demo_2.rx.EgisException;

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
        mSut = new MaybeSubjectExamples();
    }

    @Test
    public void succeedsWhenOnlyOneErrorEmitted() {
        TestObserver<String> testObserver = mSut.getMaybeSubject().test();

        mSut.emitSingleError();

        testObserver.assertError(EgisException.class);
    }

}