package com.egecius.rxjava2_demo_2.rx.zip

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.Arrays.asList

@RunWith(MockitoJUnitRunner::class)
class ZipExamplesTest {

    private val integers = asList(1, 2, 3, 4)
    private val strings = asList("one", "two", "three")

    private var mSut: ZipExamples? = null

    @Before
    fun setUp() {
        mSut = ZipExamples()
    }

    @Test
    fun zipWith() {
        val testObserver = mSut!!.zipWith(integers, strings).test()

        testObserver
                .assertComplete()
                .assertValues("1_one", "2_two", "3_three")
    }

    @Test
    fun zip() {
        val testObserver = mSut!!.zip(integers, strings).test()

        testObserver
                .assertComplete()
                .assertValues("1_one", "2_two", "3_three")
    }

}