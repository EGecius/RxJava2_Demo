package com.egecius.rxjava2_demo_2.rx.compose

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ComposeExamplesTest {

    private var mSut: ComposeExamples? = null

    @Before
    fun setUp() {
        mSut = ComposeExamples()
    }

    @Test
    fun composeOperatorAllowsEasyReuseOfRxChains() {

        val testObserver = mSut!!.doubleAndConvertToString(3).test()

        testObserver.assertResult("6")
    }

    @Test
    fun composeOperatorAllowsEasyReuseOfRxChains2() {

        val testObserver = mSut!!.doubleAndConvertToStringRepeatedThrice(4).test()

        testObserver.assertResult("888")
    }

}