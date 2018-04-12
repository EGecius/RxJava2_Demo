package com.egecius.rxjava2_demo_2.rx.conditional

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.Arrays.asList

@RunWith(MockitoJUnitRunner::class)
class ConditionalExamplesTest {

    private lateinit var mSut: ConditionalExamples

    @Before
    fun setUp() {
        mSut = ConditionalExamples()
    }

    @Test
    fun ambTakesEmissionsOnlyFromTheSourceThatStartsEmittingFirst() {

        val testObserver = mSut.ambExample().test()

        testObserver.assertResult(1, 2)
    }

    @Test
    fun switchIfEmptyProvidesDefaultForEmptyStream() {

        val testObserver = mSut.switchIfEmptyForEmptyStream(asList(1, 2, 3)).test()

        testObserver.assertResult(1, 2, 3)
    }

    @Test
    fun switchIfEmptyDefaultIsIgnoredForNonEmptyStream() {
        val testObserver = mSut.switchIfEmptyForNonEmptyStream(asList(1, 2, 3)).test()

        testObserver.assertResult(1)
    }


}