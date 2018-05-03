package com.egecius.rxjava2_demo_2.rx.error

import com.egecius.rxjava2_demo_2.rx.EgisException
import io.reactivex.Completable
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

    @Test
    fun `onErrorResumeNext swallows error and resumes chain with mapped Completable`() {
        val egisException = EgisException()

        val testObserver = Completable
                .error(egisException)
                .onErrorResumeNext { Completable.complete() }
                .test()

        testObserver.assertComplete()
    }

    companion object {

        private val EXCEPTION = Exception()
    }

}