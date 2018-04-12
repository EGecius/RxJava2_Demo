package com.egecius.rxjava2_demo_2.rx.create

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class CreateExamplesTest {

    internal lateinit var mSut: CreateExamples

    @Before
    fun setUp() {
        mSut = CreateExamples()
    }

    @Test
    fun createObservable() {
        val list = Arrays.asList(3, 2, 1)

        val observable = mSut.createObservable(list)
        val testObserver = observable.test()

        testObserver
                .assertComplete()
                .assertValues(3, 2, 1)
    }

    @Test
    fun doOnDispose() {
        val list = Arrays.asList(3, 2, 1)
        val disposable = mSut.doOnDispose(list).subscribe()
        assertThat(mSut.isCalledDoOnDispose).isFalse()

        disposable.dispose()

        assertThat(mSut.isCalledDoOnDispose).isTrue()
    }

    /* is OnComplete called when we dispose Observable? */

    @Test
    fun doOnComplete_notCalledOnDispose() {
        val list = Arrays.asList(3, 2, 1)
        val disposable = mSut.doOnComplete(list).subscribe()

        disposable.dispose()

        assertThat(mSut.isCalledDoOnComplete()).isFalse()
    }

    // TODO: 03/11/2017 fix text
    @Test
    @Ignore
    fun doOnComplete_calledOnComplete() {
        val list = Arrays.asList(3, 2, 1)
        mSut.doOnComplete(list).subscribe()
        assertThat(mSut.isCalledDoOnComplete()).isFalse()

        mSut.callOnComplete()

        assertThat(mSut.isCalledDoOnComplete()).isTrue()
    }

}