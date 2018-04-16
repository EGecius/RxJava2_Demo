package com.egecius.rxjava2_demo_2.rx.range

import io.reactivex.Flowable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RangeExampleTest {

    private var mSut: RangeExample? = null

    @Before
    fun setUp() {
        mSut = RangeExample()
    }

    @Test
    fun rangeObservable() {

        val testObserver = Observable.range(5, 3).test()

        testObserver
                .assertComplete()
                .assertComplete()
                .assertValues(5, 6, 7)

    }

    @Test
    fun rangeFlowable() {

        val testSubscriber = Flowable.range(5, 3).test()

        testSubscriber
                .assertComplete()
                .assertComplete()
                .assertValues(5, 6, 7)

    }

}