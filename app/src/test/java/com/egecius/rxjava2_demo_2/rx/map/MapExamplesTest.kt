package com.egecius.rxjava2_demo_2.rx.map

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import java.util.Arrays.asList

@RunWith(MockitoJUnitRunner::class)
class MapExamplesTest {

    private var mSut: MapExamples? = null
    private val list0to2 = object : ArrayList<String>() {
        init {
            add("zero")
            add("one")
            add("two")
        }
    }


    @Before
    fun setup() {
        mSut = MapExamples()
    }

    @Test
    fun concatenatesWithMap() {

        val testObserver = mSut!!.concatenate(list0to2).test()

        testObserver.assertNoErrors()
        testObserver.assertComplete()
        val values = testObserver.values()
        assertThat(values.size).isEqualTo(1)
        assertThat(values[0]).isEqualTo("zeroonetwo")
    }

    @Test
    fun maps_onSingle() {
        val testObserver = mSut!!.mapOnSingle(69).test()

        testObserver.assertNoErrors()
        testObserver.assertComplete()
        val values = testObserver.values()
        assertThat(values.size).isEqualTo(1)
        assertThat(values[0]).isEqualTo("69")
    }

    @Test
    fun whenMapsToNullStreamFailsWithNoValuesEmitted() {
        val testObserver = mSut!!.mapToNull(69).test()

        testObserver
                .assertNoValues()
                .assertError(NullPointerException::class.java)
    }

    /** Stream only fails when it maps to null. All values emitted prior to null still reach
     * Observer  */
    @Test
    fun whenMapsToNullPriorValidValuesGetEmitted() {
        val list = asList(1, 2, 3)
        val testObserver = mSut!!.mapToNullEvenNumbers(list).test()

        testObserver
                .assertValue("1")
                .assertError(NullPointerException::class.java)
    }

}