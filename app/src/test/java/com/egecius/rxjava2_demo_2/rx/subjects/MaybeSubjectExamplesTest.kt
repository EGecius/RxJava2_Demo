package com.egecius.rxjava2_demo_2.rx.subjects

import com.egecius.rxjava2_demo_2.rx.EgisException
import com.egecius.rxjava2_demo_2.rx.utils.TestingUtils.setUncaughtExceptionsHandlerAsStrict
import com.egecius.rxjava2_demo_2.rx.utils.UncaughtException
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MaybeSubjectExamplesTest {

    private var mSut: MaybeSubjectExamples? = null

    @Before
    fun setUp() {
        setUncaughtExceptionsHandlerAsStrict()
        mSut = MaybeSubjectExamples()
    }

    @Test
    fun succeedsWhenOnlyOneErrorEmitted() {
        val testObserver = mSut!!.maybeSubject.test()

        mSut!!.emitSingleError()

        testObserver.assertError(EgisException::class.java)
    }


    @Test(expected = UncaughtException::class)
    fun succeedsWhen2ErrorEmitted() {
        val testObserver = mSut!!.maybeSubject.test()

        mSut!!.emit2Errors()

        testObserver.assertError(EgisException::class.java)
    }

}