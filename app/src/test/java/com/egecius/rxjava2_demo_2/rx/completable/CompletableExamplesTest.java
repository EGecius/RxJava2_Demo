package com.egecius.rxjava2_demo_2.rx.completable;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.observers.TestObserver;

@RunWith(MockitoJUnitRunner.class)
public class CompletableExamplesTest {

    private CompletableExamples mSut;

    @Before
    public void setUp() {
        mSut = new CompletableExamples();
    }

    @Test
    public void andThen() {
        TestObserver<Void> testObserver = mSut.andThen().test();

        testObserver
                .assertComplete();

        List<String> list = mSut.list;
        assertThat(list.size()).isEqualTo(3);
        assertThat(list.get(0)).isEqualTo(CompletableExamples.FIRST_COMPLETABLE);
        assertThat(list.get(1)).isEqualTo(CompletableExamples.SECOND_COMPLETABLE);
        assertThat(list.get(2)).isEqualTo(CompletableExamples.THIRD_COMPLETABLE);
    }

    @Test
    public void doOnCompleteAndThen() {
        TestObserver<Void> testObserver = mSut.doOnCompleteAndThen().test();

        testObserver
                .assertComplete();
        List<String> list = mSut.list;
        assertThat(list.size()).isEqualTo(5);
        assertThat(list.get(0)).isEqualTo(CompletableExamples.FIRST_COMPLETABLE);
        assertThat(list.get(1)).isEqualTo(CompletableExamples.DO_ON_COMPLETE_A);
        assertThat(list.get(2)).isEqualTo(CompletableExamples.SECOND_COMPLETABLE);
        assertThat(list.get(3)).isEqualTo(CompletableExamples.THIRD_COMPLETABLE);
        assertThat(list.get(4)).isEqualTo(CompletableExamples.DO_ON_COMPLETE_B);
    }

    @Test
    public void divergingPaths_A() {
        TestObserver<Void> testObserver = mSut.divergingPaths(CompletableExamples.Path.A).test();

        testObserver
                .assertComplete();

        List<String> list = mSut.list;
        assertThat(list.size()).isEqualTo(3);
        assertThat(list.get(0)).isEqualTo(CompletableExamples.FIRST_COMPLETABLE);
        assertThat(list.get(1)).isEqualTo(CompletableExamples.SECOND_COMPLETABLE);
        assertThat(list.get(2)).isEqualTo(CompletableExamples.PATH_A_COMPLETABLE);
    }

    @Test
    public void divergingPaths_B() {
        TestObserver<Void> testObserver = mSut.divergingPaths(CompletableExamples.Path.B).test();

        testObserver
                .assertComplete();
        List<String> list = mSut.list;
        assertThat(list.size()).isEqualTo(3);
        assertThat(list.get(0)).isEqualTo(CompletableExamples.FIRST_COMPLETABLE);
        assertThat(list.get(1)).isEqualTo(CompletableExamples.SECOND_COMPLETABLE);
        assertThat(list.get(2)).isEqualTo(CompletableExamples.PATH_B_COMPLETABLE);
    }

    @Test
    public void divergingPathWithMaybeA() {
        TestObserver<Void> testObserver = mSut.divergingPathWithMaybeA().test();

        testObserver
                .assertComplete();
        List<String> list = mSut.list;
        assertThat(list.size()).isEqualTo(3);
        assertThat(list.get(0)).isEqualTo(CompletableExamples.FIRST_COMPLETABLE);
        assertThat(list.get(1)).isEqualTo(CompletableExamples.SECOND_COMPLETABLE);
        assertThat(list.get(2)).isEqualTo(CompletableExamples.PATH_A_COMPLETABLE);
    }

    @Test
    public void divergingPathWithMaybeB() {
        TestObserver<Void> testObserver = mSut.divergingPathWithMaybeB().test();

        testObserver
                .assertComplete();
        List<String> list = mSut.list;
        assertThat(list.size()).isEqualTo(3);
        assertThat(list.get(0)).isEqualTo(CompletableExamples.FIRST_COMPLETABLE);
        assertThat(list.get(1)).isEqualTo(CompletableExamples.SECOND_COMPLETABLE);
        assertThat(list.get(2)).isEqualTo(CompletableExamples.PATH_B_COMPLETABLE);
    }

    @Test
    public void andThenWithErrors() {
        TestObserver<Void> testObserver = mSut.andThenWithErrors().test();

        testObserver
                .assertNotComplete()
                .assertNoValues()
                .assertError(CompletableExamples.ERROR_1);
        List<String> list = mSut.list;
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0)).isEqualTo(CompletableExamples.FIRST_COMPLETABLE);
    }

    @Test
    public void doesNotExecutePathAWhenNotSubscribed() {
        mSut.divergingPathWithMaybeA();

        List<String> list = mSut.list;
        assertThat(list).isEmpty();
    }

    @Test
    public void doesNotExecutePathBWhenNotSubscribed() {
        mSut.divergingPathWithMaybeB();

        List<String> list = mSut.list;
        assertThat(list).isEmpty();
    }

}