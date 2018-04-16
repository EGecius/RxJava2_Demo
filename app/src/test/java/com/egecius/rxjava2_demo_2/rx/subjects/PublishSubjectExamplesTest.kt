package com.egecius.rxjava2_demo_2.rx.subjects

import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.Arrays.asList

@RunWith(MockitoJUnitRunner::class)
class PublishSubjectExamplesTest {
    private var mSut: PublishSubjectExamples? = null

    @Before
    fun setUp() {
        mSut = PublishSubjectExamples()
    }

    @Test
    fun emitsNoEventsWhenTheyEmittedBeforeSubscription() {
        val subject = mSut!!.emitsEventsAndOnComplete(asList(1, 2, 3))

        val testObserver = subject.test()

        testObserver
                .assertComplete()
                .assertNoValues()
    }

    @Test
    fun emitsValuesReceivedAfterSubscription() {
        val subject = PublishSubject.create<Int>()

        val testObserver = subject.test()

        subject.onNext(1)
        subject.onNext(2)

        testObserver
                .assertNotTerminated()
                .assertValues(1, 2)
    }

    @Test
    fun emitsValuesReceivedAfterSubscriptionAndTerminates() {
        val subject = PublishSubject.create<Int>()

        val testObserver = subject.test()

        subject.onNext(1)
        subject.onNext(2)
        subject.onComplete()

        testObserver
                .assertComplete()
                .assertValues(1, 2)
    }

    @Test
    fun emitsValueReceivedBeforeError() {
        val subject = PublishSubject.create<Int>()

        val testObserver = subject.test()

        subject.onNext(1)
        subject.onNext(2)
        subject.onError(EXCEPTION)

        testObserver
                .assertError(EXCEPTION)
                .assertValues(1, 2)
    }

    companion object {

        private val EXCEPTION = Exception()
    }

}