package com.egecius.rxjava2_demo_2.rx.completable

import com.egecius.rxjava2_demo_2.rx.EgisException
import io.reactivex.Completable
import io.reactivex.disposables.Disposable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.exceptions.misusing.CannotStubVoidMethodWithReturnValue
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CompletableExamplesTest {

    lateinit var mSut: CompletableExamples

    @Before
    fun setUp() {
        mSut = CompletableExamples()
    }

    @Test
    fun andThen() {
        val testObserver = mSut.demoAndThen().test()

        testObserver
                .assertComplete()

        val list = mSut.list
        assertThat(list.size).isEqualTo(3)
        assertThat(list[0]).isEqualTo(CompletableExamples.FIRST_COMPLETABLE)
        assertThat(list[1]).isEqualTo(CompletableExamples.SECOND_COMPLETABLE)
        assertThat(list[2]).isEqualTo(CompletableExamples.THIRD_COMPLETABLE)
    }

    @Test
    fun doOnCompleteAndThen() {
        val testObserver = mSut.doOnCompleteAndThen().test()

        testObserver
                .assertComplete()
        val list = mSut.list
        assertThat(list.size).isEqualTo(5)
        assertThat(list[0]).isEqualTo(CompletableExamples.FIRST_COMPLETABLE)
        assertThat(list[1]).isEqualTo(CompletableExamples.DO_ON_COMPLETE_A)
        assertThat(list[2]).isEqualTo(CompletableExamples.SECOND_COMPLETABLE)
        assertThat(list[3]).isEqualTo(CompletableExamples.THIRD_COMPLETABLE)
        assertThat(list[4]).isEqualTo(CompletableExamples.DO_ON_COMPLETE_B)
    }

    @Test
    fun divergingPaths_A() {
        val testObserver = mSut.divergingPaths(CompletableExamples.Path.A).test()

        testObserver
                .assertComplete()

        val list = mSut.list
        assertThat(list.size).isEqualTo(3)
        assertThat(list[0]).isEqualTo(CompletableExamples.FIRST_COMPLETABLE)
        assertThat(list[1]).isEqualTo(CompletableExamples.SECOND_COMPLETABLE)
        assertThat(list[2]).isEqualTo(CompletableExamples.PATH_A_COMPLETABLE)
    }

    @Test
    fun divergingPaths_B() {
        val testObserver = mSut.divergingPaths(CompletableExamples.Path.B).test()

        testObserver
                .assertComplete()
        val list = mSut.list
        assertThat(list.size).isEqualTo(3)
        assertThat(list[0]).isEqualTo(CompletableExamples.FIRST_COMPLETABLE)
        assertThat(list[1]).isEqualTo(CompletableExamples.SECOND_COMPLETABLE)
        assertThat(list[2]).isEqualTo(CompletableExamples.PATH_B_COMPLETABLE)
    }

    @Test
    fun divergingPathWithMaybeA() {
        val testObserver = mSut.divergingPathWithMaybeA().test()

        testObserver
                .assertComplete()
        val list = mSut.list
        assertThat(list.size).isEqualTo(3)
        assertThat(list[0]).isEqualTo(CompletableExamples.FIRST_COMPLETABLE)
        assertThat(list[1]).isEqualTo(CompletableExamples.SECOND_COMPLETABLE)
        assertThat(list[2]).isEqualTo(CompletableExamples.PATH_A_COMPLETABLE)
    }

    @Test
    fun divergingPathWithMaybeB() {
        val testObserver = mSut.divergingPathWithMaybeB().test()

        testObserver
                .assertComplete()
        val list = mSut.list
        assertThat(list.size).isEqualTo(3)
        assertThat(list[0]).isEqualTo(CompletableExamples.FIRST_COMPLETABLE)
        assertThat(list[1]).isEqualTo(CompletableExamples.SECOND_COMPLETABLE)
        assertThat(list[2]).isEqualTo(CompletableExamples.PATH_B_COMPLETABLE)
    }

    @Test
    fun andThenWithErrors() {
        val testObserver = mSut.andThenWithErrors().test()

        testObserver
                .assertNotComplete()
                .assertNoValues()
                .assertError(CompletableExamples.ERROR_1)
        val list = mSut.list
        assertThat(list.size).isEqualTo(1)
        assertThat(list[0]).isEqualTo(CompletableExamples.FIRST_COMPLETABLE)
    }

    @Test
    fun doesNotExecutePathAWhenNotSubscribed() {
        mSut.divergingPathWithMaybeA()

        val list = mSut.list
        assertThat(list).isEmpty()
    }

    @Test
    fun doesNotExecutePathBWhenNotSubscribed() {
        mSut.divergingPathWithMaybeB()

        val list = mSut.list
        assertThat(list).isEmpty()
    }

    @Test
    fun fromActionComplete() {
        val testObserver = mSut.fromActionComplete().test()

        testObserver
                .assertComplete()
        val list = mSut.list
        assertThat(list.size).isEqualTo(1)
        assertThat(list[0]).isEqualTo(CompletableExamples.FIRST_COMPLETABLE)
    }

    @Test
    fun fromActionError() {
        val testObserver = mSut.fromActionError().test()

        testObserver
                .assertError(EgisException::class.java)
        val list = mSut.list
        assertThat(list).isEmpty()
    }

    @Test(expected = CannotStubVoidMethodWithReturnValue::class)
    fun mockingCompletableSubscribeDoesNotWork() {

        val completable = mock(Completable::class.java)
        val disposable = mock(Disposable::class.java)

        // the reason why it does not work is that 'subscribe()' itself is final
        given(completable.subscribe()).willReturn(disposable)
    }

}