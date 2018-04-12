package com.egecius.rxjava2_demo_2.rx.assertions.await

import io.reactivex.Observable
import org.junit.Ignore
import org.junit.Test
import java.util.concurrent.TimeUnit

class AwaitExamplesTest {

    // test fails when delay is added without calling awaitX() on TestObserver
    @Test(expected = AssertionError::class)
    fun delay() {
        val testObserver = Observable
                .just(1)
                .delay(1, TimeUnit.NANOSECONDS)
                .test()

        testObserver.assertResult(1)
    }

    @Test
    @Throws(InterruptedException::class)
    fun await() {
        val testObserver = Observable
                .just(1)
                .delay(1, TimeUnit.SECONDS)
                .test()

        testObserver.await()

        testObserver.assertResult(1)
    }

    @Test
    @Throws(InterruptedException::class)
    fun await2() {
        val testObserver = Observable
                .just(1)
                .delay(1, TimeUnit.SECONDS)
                .test()

        testObserver.await(2, TimeUnit.SECONDS)

        testObserver.assertResult(1)
    }


    // awaitTerminalEvent(): await() + returns false in case of InterruptedException
    @Test
    @Throws(InterruptedException::class)
    fun awaitTerminalEvent() {
        val testObserver = Observable
                .just(1)
                .delay(1, TimeUnit.SECONDS)
                .test()

        testObserver.awaitTerminalEvent()

        testObserver.assertResult(1)
    }

    @Test
    @Throws(InterruptedException::class)
    fun awaitCount() {
        val testObserver = Observable
                .just(1, 2, 3)
                .delay(1, TimeUnit.SECONDS)
                .test()

        testObserver.awaitCount(3)

        testObserver.assertResult(1, 2, 3)
    }

    @Test
    @Ignore
    @Throws(InterruptedException::class)
    fun clearTimeout() {
        val testObserver = Observable
                .just(1, 2, 3)
                .delay(1, TimeUnit.SECONDS)
                .test()

        testObserver.isTimeout

        testObserver.assertResult(1, 2, 3)
    }

}
