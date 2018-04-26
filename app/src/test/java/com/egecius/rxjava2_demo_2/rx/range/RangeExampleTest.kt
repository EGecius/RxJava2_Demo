package com.egecius.rxjava2_demo_2.rx.range

import io.reactivex.Flowable
import io.reactivex.Observable
import org.junit.Test

class RangeExampleTest {

    @Test
    fun rangeObservable() {

        val testObserver = Observable.range(5, 3).test()

        testObserver
                .assertResult(5, 6, 7)
    }

    @Test
    fun rangeFlowable() {

        val testSubscriber = Flowable.range(5, 3).test()

        testSubscriber
                .assertResult(5, 6, 7)
    }

}