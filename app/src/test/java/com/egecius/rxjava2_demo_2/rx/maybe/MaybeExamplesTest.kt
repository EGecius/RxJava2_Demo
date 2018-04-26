package com.egecius.rxjava2_demo_2.rx.maybe

import com.egecius.rxjava2_demo_2.rx.EgisException
import io.reactivex.Completable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test
import java.util.*
import java.util.Arrays.asList

class MaybeExamplesTest {

    internal var mSut = MaybeExamples()

    @Test
    fun maybeToSingle() {
        val testObserver = mSut.maybeToSingle(1).test()

        testObserver
                .assertComplete()
                .assertValues(1)

        Completable.complete()
    }

    @Test
    fun maybeToSingleWithoutValues() {
        val testObserver = mSut.maybeToSingle(5).test()

        testObserver
                .assertNoValues()
                .assertError(NoSuchElementException::class.java)
    }

    @Test
    fun defaultIfEmptyReturnsExpectedValue() {
        val testObserver = mSut.defaultIfEmpty(5).test()

        testObserver
                .assertComplete()
                .assertValues(5)
    }

    @Test
    fun defaultIfEmptyReturnsDefaultValue() {
        val testObserver = mSut.defaultIfEmpty(null).test()

        testObserver
                .assertComplete()
                .assertValues(-1)
    }

    @Test
    fun maybeFlatMapCompletable_withEmpty() {
        assertThat(mSut.isFlatMapCompletableExecuted).isFalse()

        mSut.maybeFlatMapCompletable(emptyList()).test()

        assertThat(mSut.isFlatMapCompletableExecuted).isFalse()
    }

    @Test
    fun maybeFlatMapCompletable_withNonEmpty() {
        assertThat(mSut.isFlatMapCompletableExecuted).isFalse()

        mSut.maybeFlatMapCompletable(asList(1)).test()

        assertThat(mSut.isFlatMapCompletableExecuted).isTrue()
    }

    // TODO: 03/11/2017 fix text
    @Ignore
    @Test
    fun maybeFlatMapCompletableAndThen_withEmpty() {
        assertThat(mSut.isAndThenExecuted).isFalse()

        mSut.maybeFlatMapCompletableAndThen(emptyList()).test()

        assertThat(mSut.isAndThenExecuted).isFalse()
    }

    @Test
    fun maybeFlatMapCompletableAndThen_withNonEmpty() {
        assertThat(mSut.isAndThenExecuted).isFalse()

        mSut.maybeFlatMapCompletableAndThen(asList(1)).test()

        assertThat(mSut.isAndThenExecuted).isTrue()
    }

    @Test
    fun filterAndMap() {

        val testObserver = mSut.filterEvenAndMapToString(asList(2)).test()

        testObserver
                .assertComplete()
                .assertValue("2")
    }

    @Test
    fun filterAndMap2() {

        val testObserver = mSut.filterEvenAndMapToString(asList(1)).test()

        testObserver
                .assertComplete()
                .assertNoValues()
    }

    @Test
    fun filterAndMap3() {

        val testObserver = mSut.filterEvenAndMapToString(emptyList()).test()

        testObserver
                .assertComplete()
                .assertNoValues()
    }

    @Test(expected = AssertionError::class)
    fun flatMapSingleFailsWhenNoElementsReceived() {

        val testObserver = mSut.flatMapSingle(emptyList()).test()

        testObserver
                .assertComplete()
                .assertNoValues()
    }

    @Test
    fun maybeIgnoreElementFromSuccess() {
        val testObserver = mSut.maybeIgnoreElementFromSuccess().test()

        testObserver.assertComplete()
    }

    @Test
    fun maybeIgnoreElementFromEmpty() {
        val testObserver = mSut.maybeIgnoreElementFromEmpty().test()

        testObserver.assertComplete()
    }

    @Test
    fun maybeIgnoreElementFromError() {
        val testObserver = mSut.maybeIgnoreElementFromError().test()

        testObserver.assertError(EgisException::class.java)
    }

    @Test
    fun getBehaviorSubjectWithDefault() {
        val testObserver = mSut.behaviorSubjectWithDefault.test()

        testObserver.assertValue(true)
        testObserver.assertNotComplete()
    }

    @Ignore // I would expect this test to pass - both methods should produce same result
    @Test
    fun flatMapCompletable_replaces_MaybeFirstElementIgnoreElement() {

        val testObserver = mSut.flatMapCompletable().test()

        testObserver.assertError(EgisException::class.java)

        val testObserver2 = mSut.firstElementIgnoreElement().test()

        testObserver2.assertComplete()
    }


    @Ignore // I would expect this test to pass - both methods should produce same result
    @Test
    fun flatMapCompletableWithEmission() {

        val testObserver = mSut.flatMapCompletableWithEmission().test()

        testObserver.assertComplete()
    }

}