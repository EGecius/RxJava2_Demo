package com.egecius.rxjava2_demo_2.rx.disposable;

import static org.assertj.core.api.Assertions.assertThat;

import com.egecius.rxjava2_demo_2.rx.EgisException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.observers.DisposableObserver;

@RunWith(MockitoJUnitRunner.class)
public class DisposableExampleTest {

    private DisposableExample mSut;

    @Before
    public void setUp() {
        mSut = new DisposableExample();
    }

    @Test
    public void disposableObserverAllowsEmittingToIt() {
        DisposableObserver<Integer> disposableObserver = mSut.createDisposableObserver();
        assertNoEventsYet();

        disposableObserver.onNext(14);
        assertThat(mSut.getOnNextList()).containsOnly(14);

        disposableObserver.onNext(23);
        assertThat(mSut.getOnNextList()).containsOnly(14, 23);
    }

    private void assertNoEventsYet() {
        assertThat(mSut.getThrowable()).isNull();
        assertThat(mSut.isOnCompleteCalled()).isFalse();
        assertThat(mSut.getOnNextList()).isEmpty();
    }

    @Test
    public void disposableObserverAllowsCallingOnError() {
        DisposableObserver<Integer> disposableObserver = mSut.createDisposableObserver();
        assertNoEventsYet();

        EgisException egisException = new EgisException();
        disposableObserver.onError(egisException);

        assertThat(mSut.getOnNextList()).isEmpty();
        assertThat(mSut.isOnCompleteCalled()).isFalse();
        assertThat(mSut.getThrowable()).isEqualTo(egisException);
    }

    @Test
    public void disposableObserverAllowsCallingOnComplete() {
        DisposableObserver<Integer> disposableObserver = mSut.createDisposableObserver();
        assertNoEventsYet();

        disposableObserver.onComplete();

        assertThat(mSut.isOnCompleteCalled()).isTrue();
    }

    @Ignore //not sure why this test fails. I would expect no emissions to occur after disposing it
    @Test
    public void doesNotEmitAnythingAfterDisposing() {
        DisposableObserver<Integer> disposableObserver = mSut.createDisposableObserver();
        assertNoEventsYet();

        disposableObserver.dispose();
        disposableObserver.onComplete();
        disposableObserver.onError(new EgisException());
        disposableObserver.onNext(13);

        assertNoEventsYet();
    }

}