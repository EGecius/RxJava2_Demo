package com.egecius.rxjava2_demo_2.rx.other

import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit.MILLISECONDS

@RunWith(MockitoJUnitRunner::class)
class TimeBasedExamplesTest {

    @Test
    fun timer() {
        val testObserver = Observable.timer(10, MILLISECONDS).test()

        testObserver.await(20, MILLISECONDS)
        testObserver.assertResult(0L)
    }

    @Test
    fun delay() {

        val testObserver = Observable.just("one")
                .delay(10, MILLISECONDS).test()

        testObserver.await(20, MILLISECONDS)
        testObserver.assertResult("one")
    }

}