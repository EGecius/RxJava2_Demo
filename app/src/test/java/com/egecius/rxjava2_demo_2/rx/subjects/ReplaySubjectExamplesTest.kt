package com.egecius.rxjava2_demo_2.rx.subjects

import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.Arrays.asList
import java.util.concurrent.TimeUnit.MILLISECONDS

@RunWith(MockitoJUnitRunner::class)
class ReplaySubjectExamplesTest {

    private var mSut: ReplaySubjectExamples? = null

    @Before
    fun setUp() {
        mSut = ReplaySubjectExamples()
    }

    @Test
    fun subscribeToDefault() {
        val subject = mSut!!.defaultReplaySubject(asList(11, 12, 13))

        val testObserver = subject.test()

        testObserver
                .assertComplete()
                .assertValues(11, 12, 13)
    }

    @Test
    fun sizedReplaySubjectEmitsLimitedNumberOfValues() {
        val subject = mSut!!.createWithSize2(asList(1, 2, 3),
                asList(11, 12, 13))

        val testObserver = subject.test()

        testObserver
                .assertComplete()
                .assertValueCount(2)
    }

    /** For some reason Subject emits only last values from the first subscription - seems
     * counter-intuitive  */
    @Test
    fun replaySubjectEmitsLastValues() {
        val subject = mSut!!.createWithSize2(asList(1, 2, 3),
                asList(11, 12, 13))

        val testObserver = subject.test()

        testObserver
                .assertValues(2, 3)
    }

    @Test
    @Ignore
    @Throws(InterruptedException::class)
    fun createWithTime() {
        val subject = mSut!!.createWithTime()

        val testObserver = subject.test()

        testObserver
                .assertComplete()
                .assertValues("two", "three")
    }

    /** For some reason I can't understand how to use advanceTimeBy  */
    @Test
    @Ignore
    fun createWithTimeWithoutThreadSleep() {

        val testScheduler = TestScheduler()

        val subject = mSut!!.createWithTime20Millis()

        val testObserver = subject
                .subscribeOn(testScheduler)
                .observeOn(testScheduler)
                .test()

        // since our replay is limited to 20 millis, after only 10 millis we should still all 3
        // expected emissions
        testScheduler.advanceTimeBy(10, MILLISECONDS)

        testObserver
                .assertNoErrors()
                .assertValues("one", "two", "three")

        // after 100 millis there should be no emissions left
        testScheduler.advanceTimeBy(100, MILLISECONDS)

        testObserver
                .assertNoErrors()
                .assertNoValues()
    }

}