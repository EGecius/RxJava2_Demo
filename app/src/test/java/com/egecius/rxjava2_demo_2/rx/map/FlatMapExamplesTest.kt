package com.egecius.rxjava2_demo_2.rx.map

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*


@RunWith(MockitoJUnitRunner::class)
class FlatMapExamplesTest {

    private var mSut: FlatMapExamples? = null

    private val list0to2 = object : ArrayList<String>() {
        init {
            add("zero")
            add("one")
            add("two")
        }
    }

    @Before
    fun setup() {
        mSut = FlatMapExamples()
    }


    @Test
    fun fromIterable() {
        val testObserver = mSut!!.fromIterable(list0to2).test()

        testObserver.assertComplete()
        val values = testObserver.values()
        assertThat(values.size).isEqualTo(3)
        assertThat(values[0]).isEqualTo("zero")
        assertThat(values[1]).isEqualTo("one")
        assertThat(values[2]).isEqualTo("two")
    }

    @Test
    fun flatmaps_onSingle() {
        val testObserver = mSut!!.flatmapOnSingle(69).test()

        testObserver.assertComplete()
        val values = testObserver.values()
        assertThat(values.size).isEqualTo(1)
        assertThat(values[0]).isEqualTo("69")
    }

    /* DEMOS DIFFERENT ONE-TO-? RELATIONSHIPS*/

    @Test
    fun flatMapOneToMany() {
        val testObserver = mSut!!.flatMap(list0to2).test()

        testObserver.assertComplete()
        val values = testObserver.values()
        assertThat(values.size).isEqualTo(3)
        assertThat(values[0]).isEqualTo("zero")
        assertThat(values[1]).isEqualTo("one")
        assertThat(values[2]).isEqualTo("two")
    }

    @Test
    fun flatMapOneToOne() {
        val testObserver = mSut!!.flatMapOneToOne(69).test()

        testObserver.assertComplete()
        testObserver.assertValues("69")
    }

    @Test
    fun flatMapOneToNone() {
        val testObserver = mSut!!.flatMapOneToNone(69).test()

        testObserver.assertComplete()
        testObserver.assertNoValues()
    }

    @Test
    fun flatMapOneToSometimes() {
        val list = listOf(0, 1, 2, 3, 4, 5)
        val testObserver = mSut!!.flatMapOneToSometimes(list).test()

        testObserver.assertComplete()
        testObserver.assertValues(1, 3, 5)
    }

    /* flatMapToObservable */

    @Test
    fun singleFlatMapObservable() {
        val list = listOf(1, 2, 3, 4)
        val testObserver = mSut!!.singleFlatMapObservable(list).test()

        testObserver.assertValues(1, 2, 3, 4)

        testObserver.assertComplete()
    }

}