package com.egecius.rxjava2_demo_2.rx.maybe;

import static org.assertj.core.api.Assertions.assertThat;

import static java.util.Arrays.asList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

import io.reactivex.observers.TestObserver;

@RunWith(MockitoJUnitRunner.class)
public class MaybeExamplesTest {

	MaybeExamples mSut;

	@Before
	public void setup() {
		mSut = new MaybeExamples();
	}

	@Test
	public void maybeToSingle() {
		TestObserver<Integer> testObserver = mSut.maybeToSingle(1).test();

		testObserver
				.assertNoErrors()
				.assertComplete()
				.assertValues(1);
	}

	@Test
	public void maybeToSingleWithoutValues() {
		TestObserver<Integer> testObserver = mSut.maybeToSingle(5).test();

		testObserver
				.assertNoValues()
				.assertError(NoSuchElementException.class);
	}

	@Test
	public void defaultIfEmptyRetunrsExpectedValue() {
        TestObserver<Integer> testObserver = mSut.defaultIfEmpty(5).test();

        testObserver
                .assertNoErrors()
                .assertComplete()
                .assertValues(5);
	}

    @Test
    public void defaultIfEmptyReturnsDefaultValue() {
        TestObserver<Integer> testObserver = mSut.defaultIfEmpty(null).test();

        testObserver
                .assertNoErrors()
                .assertComplete()
                .assertValues(-1);
    }

    @Test
    public void maybeFlatMapCompletable_withEmpty() {
        assertThat(mSut.isFlatMapCompletableExecuted).isFalse();

        mSut.maybeFlatMapCompletable(Collections.EMPTY_LIST).test();

        assertThat(mSut.isFlatMapCompletableExecuted).isFalse();
    }

    @Test
    public void maybeFlatMapCompletable_withNonEmpty() {
        assertThat(mSut.isFlatMapCompletableExecuted).isFalse();

        mSut.maybeFlatMapCompletable(asList(1)).test();

        assertThat(mSut.isFlatMapCompletableExecuted).isTrue();
    }

    @Test
    public void maybeFlatMapCompletableAndThen_withEmpty() {
        assertThat(mSut.isAndThenExecuted).isFalse();

        mSut.maybeFlatMapCompletableAndThen(Collections.EMPTY_LIST).test();

        assertThat(mSut.isAndThenExecuted).isFalse();
    }

    @Test
    public void maybeFlatMapCompletableAndThen_withNonEmpty() {
        assertThat(mSut.isAndThenExecuted).isFalse();

        mSut.maybeFlatMapCompletableAndThen(asList(1)).test();

        assertThat(mSut.isAndThenExecuted).isTrue();
    }

}