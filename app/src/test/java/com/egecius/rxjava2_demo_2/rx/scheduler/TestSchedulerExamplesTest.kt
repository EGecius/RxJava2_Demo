package com.egecius.rxjava2_demo_2.rx.scheduler

import io.reactivex.schedulers.TestScheduler
import io.reactivex.subjects.PublishSubject
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit.MILLISECONDS

@RunWith(MockitoJUnitRunner::class)
class TestSchedulerExamplesTest {

    @Test
    fun advancesTime() {
        val publishSubject = PublishSubject.create<String>()
        val scheduler = TestScheduler()
        val testObserver = publishSubject.delay(1000, MILLISECONDS,
                scheduler).test()

        testObserver.assertEmpty()

        // no emissions since there is delay
        publishSubject.onNext("egis")
        testObserver.assertEmpty()

        // still no emissions since delay is 1ms longer than we are advancing time by
        scheduler.advanceTimeBy(999, MILLISECONDS)
        testObserver.assertEmpty()

        // after finally advancing time to same as delay, we are getting our emission
        scheduler.advanceTimeBy(1, MILLISECONDS)
        testObserver.assertValue("egis")
    }


}