package com.egecius.rxjava2_demo_2.rx.flatten

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FlattenAsObservableExamplesTest {

    lateinit var mSut: FlattenAsObservableExamples

    @Before
    fun setup() {
        mSut = FlattenAsObservableExamples()
    }

    @Test
    fun flattenAsObservable() {
        //WHEN
        val array = arrayOf("one", "two", "three")
        val testObserver = mSut.flattenAsObservable(*array).test()
        //THEN
        testObserver.assertValues("one", "two", "three")
        testObserver.assertComplete()
    }

}