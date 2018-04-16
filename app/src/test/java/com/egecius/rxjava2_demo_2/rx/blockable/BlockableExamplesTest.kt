package com.egecius.rxjava2_demo_2.rx.blockable

import org.assertj.core.api.Assertions.assertThat

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BlockableExamplesTest {

    private lateinit var mSut: BlockableExamples

    @Before
    fun setUp() {
        mSut = BlockableExamples()
    }

    /** Blocking get turns asynchronous operation into synchronous  */
    @Test
    fun blockingGetForSingle() {
        val integer = mSut.blockingGetForSingle()

        assertThat(integer).isEqualTo(1)
    }

    /** Blocking get turns asynchronous operation into synchronous  */
    @Test
    fun blockingGetForObservable() {
        val integer = mSut.blockingGetForObservable()

        assertThat(integer).isEqualTo(1)
    }

}