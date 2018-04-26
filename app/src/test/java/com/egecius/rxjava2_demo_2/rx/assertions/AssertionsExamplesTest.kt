package com.egecius.rxjava2_demo_2.rx.assertions

import com.egecius.rxjava2_demo_2.rx.EgisException
import io.reactivex.Observable
import io.reactivex.functions.Predicate
import io.reactivex.observers.TestObserver
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.Arrays.asList

@RunWith(MockitoJUnitRunner::class)
class AssertionsExamplesTest {
    private var mSut: AssertionsExamples? = null

    @Before
    fun setUp() {
        mSut = AssertionsExamples()
    }

    @Test
    fun assertNotSubscribed() {
        val testObserver = TestObserver<Void>()

        testObserver.assertNotSubscribed()
    }

    @Test
    fun assertSubscribed() {
        val testObserver = mSut!!.just.test()

        testObserver.assertSubscribed()
    }

    @Test
    fun assertNoValues() {
        val testObserver = Observable.empty<Any>().test()

        testObserver.assertNoValues()
    }

    /** assertEmpty() assertion check for any events, including onComplete  */
    @Test(expected = AssertionError::class)
    fun assertEmpty1() {
        val testObserver = Observable.empty<Any>().test()

        testObserver.assertEmpty()
    }

    /** assertEmpty() assertion check for any events, including onError  */
    @Test(expected = AssertionError::class)
    fun assertEmpty2() {
        val testObserver = Observable.error<Any>(Exception()).test()

        testObserver.assertEmpty()
    }

    @Test
    fun assertErrorOfClass() {
        val testObserver = Observable.error<Any>(EgisException("message")).test()

        testObserver.assertError(EgisException::class.java)
    }

    @Test(expected = AssertionError::class)
    fun assertErrorOfClass2() {
        val testObserver = Observable.error<Any>(EgisException("message")).test()

        testObserver.assertError(IllegalArgumentException::class.java)
    }

    /** assertError compares using equals, so passing same instance satisfies assertion  */
    @Test
    fun assertErrorOfObject() {
        val exception = EgisException("message")
        val testObserver = Observable.error<Any>(exception).test()

        testObserver.assertError(exception)
    }

    /**
     * assertError compares using equals, so instance with same equals() value satisfies
     * assertion
     */
    @Test
    fun assertErrorOfObject2() {
        val testObserver = Observable.error<Any>(EgisException("message")).test()

        testObserver.assertError(EgisException("message"))
    }

    /** This assertion fails since Exception does not implement equals()  */
    @Test(expected = AssertionError::class)
    fun assertErrorOfObject3() {
        val testObserver = Observable.error<Any>(Exception("message")).test()

        testObserver.assertError(Exception("message"))
    }

    /**
     * This is a workaround for an Exception that does not implement equals. We can get access
     * to it via Predicate and run our own assertions on it
     */
    @Test
    fun assertErrorOfObject4() {
        val testObserver = Observable.error<Any>(
                Exception(EXCEPTION_MESSAGE)).test()

        testObserver.assertError { throwable -> throwable.message == EXCEPTION_MESSAGE }
    }

    @Test
    fun assertErrorMessage() {
        val testObserver = Observable.error<Any>(
                Exception(EXCEPTION_MESSAGE)).test()

        testObserver.assertErrorMessage(EXCEPTION_MESSAGE)
    }

    @Test
    fun assertFailure() {
        val testObserver = mSut!!.emitThreeIntegersAndFail().test()

        testObserver.assertFailure(EgisException::class.java, 0, 1, 2)
    }

    @Test
    fun assertFailure2() {
        val testObserver = mSut!!.emitThreeIntegersAndFail().test()

        testObserver.assertFailure(Predicate { throwable -> throwable === mSut!!.exception }, 0, 1, 2)
    }

    @Test
    fun assertFailureAndMessage() {
        val testObserver = mSut!!.emitThreeIntegersAndFail().test()

        testObserver.assertFailureAndMessage(EgisException::class.java, mSut!!.exceptionMessage, 0, 1,
                2)
    }

    @Test
    fun assertNever() {
        val testObserver = mSut!!.emitThreeIntegersAndFail().test()

        testObserver.assertNever(-1)
    }

    @Test(expected = AssertionError::class)
    fun assertNever2() {
        val testObserver = mSut!!.emitThreeIntegersAndFail().test()

        testObserver.assertNever(0)
    }

    @Test
    fun assertNoTimeout() {
        val testObserver = Observable.empty<Any>().test()

        testObserver.assertNoTimeout()
    }


    @Test
    fun assertResult() {
        val testObserver = Observable.just(1, 2).test()

        testObserver.assertResult(1, 2)
    }

    @Test(expected = AssertionError::class)
    fun assertResult2() {
        val testObserver = mSut!!.emitWithoutCompletion(1, 2).test()

        testObserver.assertResult(1, 2)
    }

    @Test
    fun assertValues() {
        val testObserver = mSut!!.emitWithoutCompletion(1, 2).test()

        testObserver.assertValues(1, 2)
    }


    @Test
    fun assertValueAt() {
        val testObserver = mSut!!.emitWithoutCompletion(1, 2).test()

        testObserver.assertValueAt(1, 2)
    }

    @Test(expected = AssertionError::class)
    fun assertValueAt2() {
        val testObserver = mSut!!.emitWithoutCompletion(1, 2).test()

        testObserver.assertValueAt(0, 0)
    }

    @Test(expected = AssertionError::class)
    fun assertValueAt3() {
        val testObserver = mSut!!.emitWithoutCompletion(1, 2).test()

        testObserver.assertValueAt(2, 2)
    }

    @Test
    fun assertValueSequence() {
        val testObserver = mSut!!.emitWithoutCompletion(1, 2).test()

        testObserver.assertValueSequence(asList(1, 2))
    }

    // fails when same order is not maintained
    @Test(expected = AssertionError::class)
    fun assertValueSequence2() {
        val testObserver = mSut!!.emitWithoutCompletion(1, 2).test()

        testObserver.assertValueSequence(asList(2, 1))
    }

    @Test
    fun assertValueSet() {
        val testObserver = mSut!!.emitWithoutCompletion(1, 2).test()

        testObserver.assertValueSet(asList(1, 2))
    }

    // assertValueSet takes Collection in any order
    @Test
    fun assertValueSet2() {
        val testObserver = mSut!!.emitWithoutCompletion(1, 2).test()

        testObserver.assertValueSet(asList(2, 1))
    }

    @Test
    fun assertValuesOnly() {
        val testObserver = mSut!!.emitWithoutCompletion(1, 2).test()

        testObserver.assertValuesOnly(1, 2)
    }

    @Test(expected = AssertionError::class)
    fun assertValuesOnly2() {
        val testObserver = Observable.just(1, 2).test()

        testObserver.assertValuesOnly(1, 2)
    }

    @Test
    fun completions() {
        val testObserver = Observable.just(1, 2).test()

        assertThat(testObserver.completions()).isEqualTo(1)
    }

    // TODO: 06/12/2017 can one Obseerver subscribe to multiple Observables?
    @Test
    @Ignore // I thought this would work but it does not.
    fun completion2() {
        val observable1 = Observable.just(1)
        val observable2 = Observable.just(2)
        val observable3 = Observable.just(3)

        val testObserver = TestObserver<Int>()
        observable1.subscribe(testObserver)
        observable2.subscribe(testObserver)
        observable3.subscribe(testObserver)

        assertThat(testObserver.completions()).isEqualTo(2)
    }

    @Test
    fun errorCount() {
        val testObserver = Observable.error<Any>(Exception()).test()

        assertThat(testObserver.errorCount()).isEqualTo(1)
    }

    @Test
    fun errors() {
        val exception = Exception()
        val testObserver = Observable.error<Any>(exception).test()

        val errors = testObserver.errors()
        assertThat(errors.size).isEqualTo(1)
        assertThat(errors[0]).isEqualTo(exception)
    }

    //todo make you work
    @Test
    fun getEvents() {
        val testObserver = Observable.just(33, 44).test()

        val events = testObserver.events
        assertThat(events.size).isEqualTo(3)
        assertThat(events[0]).isEqualTo(asList(33, 44))
        assertThat(events[1]).isEqualTo(emptyList<Any>())
        // last one is list of OnCompleteNotification - compiler can't find it
        //        assertThat(events.get(2)).isEqualTo(asList(new OnCompleteNotification()));
    }

    @Test
    fun getEvents2() {
        val exception = Exception()
        val testObserver = Observable.error<Any>(exception).test()

        val events = testObserver.events
        assertThat(events.size).isEqualTo(3)
        assertThat(events[0]).isEqualTo(emptyList<Any>())
        assertThat(events[1]).isEqualTo(listOf(exception))
        // last one is list of OnCompleteNotification - compiler can't find it
        //        assertThat(events.get(2)).isEqualTo(asList(new OnCompleteNotification()));
    }

    @Test
    @Ignore // called from thread 'Test worker' when called from Gradle
    fun lastThread() {
        val testObserver = Observable.just(1).test()

        val thread = testObserver.lastThread()
        assertThat(thread.name).isEqualTo("main")
    }

    @Ignore // when this test fails, you can see tag in its logs
    @Test
    fun withTag() {
        val testObserver = Observable.just(1).test()

        testObserver
                .withTag("egis_tag")
                .assertValue(2)
    }

    companion object {

        private val EXCEPTION_MESSAGE = "EXCEPTION_MESSAGE"
    }

}