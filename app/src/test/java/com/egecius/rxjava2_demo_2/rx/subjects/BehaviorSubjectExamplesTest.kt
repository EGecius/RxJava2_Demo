package com.egecius.rxjava2_demo_2.rx.subjects

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.Arrays.asList

@RunWith(MockitoJUnitRunner::class)
class BehaviorSubjectExamplesTest {

    private var mSut: BehaviorSubjectExamples? = null

    @Before
    fun setUp() {
        mSut = BehaviorSubjectExamples()
    }

    @Test
    fun cachesLastKnownValue() {
        val subject = mSut!!.emit(asList(1, 2, 3))

        val testObserver = subject.test()

        testObserver
                .assertNotTerminated()
                .assertValue(3)
    }

    @Test
    fun whenSubscribedCompletedSubjectNoEventsAreEmitted() {
        val subject = mSut!!.emitWithOnComplete(asList(1, 2, 3))

        val testObserver = subject.test()

        testObserver
                .assertComplete()
                .assertNoValues()
    }

    @Test
    fun whenSubscribedToFailedSubjectNoEventsAreEmitted() {
        val subject = mSut!!.emitWithError(asList(1, 2, 3), EXCEPTION)

        val testObserver = subject.test()

        testObserver
                .assertError(EXCEPTION)
                .assertNoValues()
    }

    @Test
    fun whenSubscribesBeforeOnCompleteReceivesPriorEvents() {
        val subject = mSut!!.emit(asList(1, 2, 3))

        val testObserver = subject.test()

        subject.onNext(14)
        subject.onNext(15)
        subject.onComplete()

        testObserver
                .assertComplete()
                .assertValues(3, 14, 15)
    }

    @Test
    fun whenSubscribesBeforeErrorReceivesPriorEvents() {
        val subject = mSut!!.emit(asList(1, 2, 3))

        val testObserver = subject.test()

        subject.onNext(14)
        subject.onNext(15)
        subject.onError(EXCEPTION)

        testObserver
                .assertValues(3, 14, 15)
                .assertNotComplete()
                .assertError(EXCEPTION)
    }

    @Test
    fun getValueReturnsCachedEvent() {
        val subject = mSut!!.emit(asList(1, 2, 3))

        assertThat(subject.value).isEqualTo(3)

        subject.onNext(14)

        assertThat(subject.value).isEqualTo(14)

        subject.onNext(15)

        assertThat(subject.value).isEqualTo(15)
    }

    companion object {
        private val EXCEPTION = Exception()
    }

}