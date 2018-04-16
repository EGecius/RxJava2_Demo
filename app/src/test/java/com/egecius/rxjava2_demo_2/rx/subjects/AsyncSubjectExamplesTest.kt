package com.egecius.rxjava2_demo_2.rx.subjects

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.Arrays.asList

@RunWith(MockitoJUnitRunner::class)
class AsyncSubjectExamplesTest {

    private var mSut: AsyncSubjectExamples? = null

    @Before
    fun setUp() {
        mSut = AsyncSubjectExamples()
    }

    @Test
    fun emitsOnlyLastEventBeforeTermination() {

        val list = asList(1, 2, 3)
        val subject = mSut!!.emitEventsWithOnComplete(list)

        val testObserver = subject.test()

        testObserver
                .assertComplete()
                .assertValue(3)
    }

    @Test
    fun emitsNoEventsWhenTerminatesWithError() {

        val list = asList(1, 2, 3)
        val subject = mSut!!.emitEventsWithError(list)

        val testObserver = subject.test()

        testObserver
                .assertNoValues()
                .assertError(AsyncSubjectExamples.ASYNC_EXCEPTION)
    }

    @Test
    fun emitsNoValuesWithoutTermination() {

        val list = asList(1, 2, 3)
        val subject = mSut!!.emitEventsWithoutTermination(list)

        val testObserver = subject.test()

        testObserver
                .assertNoValues()
                .assertNotTerminated()
    }

}