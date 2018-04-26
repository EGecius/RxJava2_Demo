package com.egecius.rxjava2_demo_2.rx.retry

import org.assertj.core.api.Assertions.assertThat
import org.junit.ComparisonFailure
import org.junit.Test
import java.util.*

class RetryExamplesTest {

    private var mSut = RetryExamples()

    /**
     * Notice here 'subscribe' was called one fewer time than in retryWhenWithDelay
     * The reason probably is that here after last emission in Flowable onComplete() is called
     * immediately, which immediately errors the stream with NoSuchElementException and thus
     * preventing the last subscription
     */
    @Test
    fun retryWhen() {
        val testObserver = mSut.retryWhen(5).test()

        assertThat(mSut.subscribeCalled).isEqualTo(5)
        testObserver
                .assertNoValues()
                .assertError(NoSuchElementException::class.java)
    }

    @Test
    @Throws(InterruptedException::class)
    fun retryWhenWithDelay() {
        val testObserver = mSut.retryWhenWithDelay(5).test()

        testObserver.await()

        assertThat(mSut.subscribeCalled).isEqualTo(6)
        testObserver
                .assertNoValues()
                .assertError(NoSuchElementException::class.java)
    }

    /** Test same as above but without 'await' fails without 'await' call  */
    @Test(expected = ComparisonFailure::class)
    @Throws(InterruptedException::class)
    fun retryWhenWithDelayFailsWithoutAwait() {
        val testObserver = mSut.retryWhenWithDelay(5).test()

        //        testObserver.await();

        assertThat(mSut.subscribeCalled).isEqualTo(6)
        testObserver
                .assertNoValues()
                .assertError(NoSuchElementException::class.java)
    }

    @Test
    fun retryWhenWithoutOnComplete() {
        val testObserver = mSut.retryWithoutOnComplete(5).test()

        assertThat(mSut.subscribeCalled).isEqualTo(6)
        testObserver
                .assertNoValues()
                .assertNoErrors()
                .assertNotComplete()
    }

    @Test
    fun retry() {
        val testObserver = mSut.retryTimes(5).test()

        // subscribed 6 times because the first subscribe is before any retry
        assertThat(mSut.subscribeCalled).isEqualTo(6)
        testObserver
                .assertNoValues()
                .assertError(Exception::class.java)
    }

    @Test
    fun retryWhen_withZip() {

        val testObserver = mSut.retryWhenWithZipTimes(5).test()

        // subscribed 6 times because the first subscribe is before any retry
        assertThat(mSut.subscribeCalled).isEqualTo(6)
        testObserver
                .assertNoValues()

        // TODO: 21/12/2017 assertError
    }


}