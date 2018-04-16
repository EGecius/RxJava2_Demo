package com.egecius.rxjava2_demo_2.rx.subjects

import com.egecius.rxjava2_demo_2.rx.EgisException
import com.egecius.rxjava2_demo_2.rx.utils.TestingUtils.setUncaughtExceptionsHandlerAsStrict
import com.egecius.rxjava2_demo_2.rx.utils.UncaughtException
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CompletableSubjectExamplesTest {

    private var mSut: CompletableSubjectExamples? = null

    @Before
    fun setUp() {
        setUncaughtExceptionsHandlerAsStrict()
        mSut = CompletableSubjectExamples()
    }

    @Test(expected = UncaughtException::class)
    fun failsWhenTwoErrorsEmitted() {
        val testObserver = mSut!!.completableSubject.test()
        mSut!!.emit2Errors()

        testObserver.assertError(EgisException::class.java)
        //has failed with UndeliverableException
    }

    @Test
    fun succeedsWhenOnly1ErrorEmitted() {
        val testObserver = mSut!!.completableSubject.test()
        mSut!!.emit1Error()

        testObserver.assertError(EgisException::class.java)
    }

    @Test
    fun completableSubjectSubscribesToAnotherCompletable() {
        val testObserver = mSut!!.completableSubject.test()
        mSut!!.subscribeToAnother()

        testObserver.assertError(EgisException::class.java)
    }

}