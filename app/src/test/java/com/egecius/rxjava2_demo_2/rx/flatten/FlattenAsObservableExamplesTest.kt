package com.egecius.rxjava2_demo_2.rx.flatten

import org.junit.Test

class FlattenAsObservableExamplesTest {

    var mSut = FlattenAsObservableExamples()

    @Test
    fun flattenAsObservable() {
        //WHEN
        val array = arrayOf("one", "two", "three")
        val testObserver = mSut.flattenAsObservable(array).test()
        //THEN
        testObserver.assertValues("one", "two", "three")
        testObserver.assertComplete()
    }

}