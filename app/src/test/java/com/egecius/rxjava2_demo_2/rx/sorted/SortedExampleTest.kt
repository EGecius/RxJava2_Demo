package com.egecius.rxjava2_demo_2.rx.sorted

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Tests for [SortedExample]
 */
@RunWith(MockitoJUnitRunner::class)
class SortedExampleTest {

    lateinit var mSut: SortedExample

    @Before
    fun setup() {
        mSut = SortedExample()
    }

    @Test
    fun sorted() {
        val integers = arrayOf(4, 3, 1, 2)
        val testObserver = mSut.sort(integers).test()

        testObserver.assertNoErrors()
                .assertComplete()
                .assertValueCount(4)
                .assertValues(1, 2, 3, 4)
    }

    @Test
    fun toList() {
        val integers = arrayOf(4, 3, 1, 2)
        val testObserver = mSut.toList(integers).test()

        testObserver.assertNoErrors()
                .assertComplete()
                .assertValueCount(1)

        val values = testObserver.values()[0]
        assertThat(values[0]).isEqualTo(4)
        assertThat(values[1]).isEqualTo(3)
        assertThat(values[2]).isEqualTo(1)
        assertThat(values[3]).isEqualTo(2)
    }

}