package com.egecius.rxjava2_demo_2.rx.startWith

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class StartWithExamplesTest {

    private var mSut: StartWithExamples? = null

    @Before
    fun setUp() {
        mSut = StartWithExamples()
    }

    @Test
    fun startWithObservable() {
        val list = Arrays.asList(3, 14)

        val testObserver = mSut!!.startWithObservable(list).test()

        testObserver
                .assertComplete()
                .assertValues(3, 14, 1, 2, 3)
    }

    @Test
    fun startWithIterable() {
        val list = Arrays.asList(3, 14)

        val testObserver = mSut!!.startWithIterable(list).test()

        testObserver
                .assertComplete()
                .assertValues(3, 14, 1, 2, 3)
    }

    @Test
    fun startWithArray() {
        val array = arrayOf(3, 14)

        val testObserver = mSut!!.startWithArray(*array).test()

        testObserver
                .assertComplete()
                .assertValues(3, 14, 1, 2, 3)
    }

    @Test
    fun startWithElement() {
        val testObserver = mSut!!.startWithElement(14).test()

        testObserver
                .assertComplete()
                .assertValues(14, 1, 2, 3)
    }

}