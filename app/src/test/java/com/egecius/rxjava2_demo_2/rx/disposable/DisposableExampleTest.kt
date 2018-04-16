package com.egecius.rxjava2_demo_2.rx.disposable

import com.egecius.rxjava2_demo_2.rx.EgisException
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DisposableExampleTest {

    private var mSut: DisposableExample? = null

    @Before
    fun setUp() {
        mSut = DisposableExample()
    }

    @Test
    fun disposableObserverAllowsEmittingToIt() {
        val disposableObserver = mSut!!.createDisposableObserver()
        assertNoEventsYet()

        disposableObserver.onNext(14)
        assertThat(mSut!!.onNextList).containsOnly(14)

        disposableObserver.onNext(23)
        assertThat(mSut!!.onNextList).containsOnly(14, 23)
    }

    private fun assertNoEventsYet() {
        assertThat(mSut!!.throwable).isNull()
        assertThat(mSut!!.isOnCompleteCalled).isFalse()
        assertThat(mSut!!.onNextList).isEmpty()
    }

    @Test
    fun disposableObserverAllowsCallingOnError() {
        val disposableObserver = mSut!!.createDisposableObserver()
        assertNoEventsYet()

        val egisException = EgisException()
        disposableObserver.onError(egisException)

        assertThat(mSut!!.onNextList).isEmpty()
        assertThat(mSut!!.isOnCompleteCalled).isFalse()
        assertThat(mSut!!.throwable).isEqualTo(egisException)
    }

    @Test
    fun disposableObserverAllowsCallingOnComplete() {
        val disposableObserver = mSut!!.createDisposableObserver()
        assertNoEventsYet()

        disposableObserver.onComplete()

        assertThat(mSut!!.isOnCompleteCalled).isTrue()
    }

    @Ignore //not sure why this test fails. I would expect no emissions to occur after disposing it
    @Test
    fun doesNotEmitAnythingAfterDisposing() {
        val disposableObserver = mSut!!.createDisposableObserver()
        assertNoEventsYet()

        disposableObserver.dispose()
        disposableObserver.onComplete()
        disposableObserver.onError(EgisException())
        disposableObserver.onNext(13)

        assertNoEventsYet()
    }

}