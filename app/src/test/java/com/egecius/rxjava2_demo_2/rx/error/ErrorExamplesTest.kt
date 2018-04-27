package com.egecius.rxjava2_demo_2.rx.error

import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

class ErrorExamplesTest {

    private var mSut: ErrorExamples? = null

    @Before
    fun setUp() {
        mSut = ErrorExamples()
    }

    @Test
    fun error() {
        val testSubscriber = Flowable.error<Any>(EXCEPTION).test()

        testSubscriber
                .assertTerminated()
                .assertNotComplete()
                .assertNoValues()
                .assertError(EXCEPTION)
    }

    companion object {

        private val EXCEPTION = Exception()
    }

}