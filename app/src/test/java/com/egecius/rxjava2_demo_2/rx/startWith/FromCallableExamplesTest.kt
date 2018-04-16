package com.egecius.rxjava2_demo_2.rx.startWith

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FromCallableExamplesTest {

    private var mSut: FromCallableExamples? = null

    @Before
    fun setUp() {
        mSut = FromCallableExamples()
    }

    @Test
    fun fromCallableDelaysExecutionTillSubscription() {
        val observable = mSut!!.fromCallableThreadName()

        assertThat(mSut!!.isCallableExecuted).isFalse()

        observable.test()

        assertThat(mSut!!.isCallableExecuted).isTrue()
    }

}