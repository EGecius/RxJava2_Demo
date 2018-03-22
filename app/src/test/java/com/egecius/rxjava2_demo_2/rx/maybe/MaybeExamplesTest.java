package com.egecius.rxjava2_demo_2.rx.maybe;

import static org.assertj.core.api.Assertions.assertThat;

import static java.util.Arrays.asList;

import com.egecius.rxjava2_demo_2.rx.EgisException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.NoSuchElementException;

import io.reactivex.Completable;
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
				.assertComplete()
				.assertValues(1);

		Completable.complete();
	}

	@Test
	public void maybeToSingleWithoutValues() {
		TestObserver<Integer> testObserver = mSut.maybeToSingle(5).test();

		testObserver
				.assertNoValues()
				.assertError(NoSuchElementException.class);
	}

	@Test
	public void defaultIfEmptyReturnsExpectedValue() {
        TestObserver<Integer> testObserver = mSut.defaultIfEmpty(5).test();

        testObserver
                .assertComplete()
                .assertValues(5);
	}

    @Test
    public void defaultIfEmptyReturnsDefaultValue() {
        TestObserver<Integer> testObserver = mSut.defaultIfEmpty(null).test();

        testObserver
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

    // TODO: 03/11/2017 fix text
    @Ignore
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

    @Test
    public void filterAndMap() {

        TestObserver<String> testObserver = mSut.filterEvenAndMapToString(asList(2)).test();

        testObserver
                .assertComplete()
                .assertValue("2");
    }

    @Test
    public void filterAndMap2() {

        TestObserver<String> testObserver = mSut.filterEvenAndMapToString(asList(1)).test();

        testObserver
                .assertComplete()
                .assertNoValues();
    }

    @Test
    public void filterAndMap3() {

        @SuppressWarnings("unchecked")
        TestObserver<String> testObserver = mSut.filterEvenAndMapToString(Collections.EMPTY_LIST).test();

        testObserver
                .assertComplete()
                .assertNoValues();
    }

    @Test (expected = AssertionError.class)
    public void flatMapSingleFailsWhenNoElementsReceived() {

        @SuppressWarnings("unchecked")
        TestObserver<String> testObserver = mSut.flatMapSingle(Collections.EMPTY_LIST).test();

        testObserver
                .assertComplete()
                .assertNoValues();
    }

    @Test
    public void maybeIgnoreElementFromSuccess() {
        TestObserver<Void> testObserver = mSut.maybeIgnoreElementFromSuccess().test();

        testObserver.assertComplete();
    }

    @Test
    public void maybeIgnoreElementFromEmpty() {
        TestObserver<Void> testObserver = mSut.maybeIgnoreElementFromEmpty().test();

        testObserver.assertComplete();
    }

    @Test
    public void maybeIgnoreElementFromError() {
        TestObserver<Void> testObserver = mSut.maybeIgnoreElementFromError().test();

        testObserver.assertError(EgisException.class);
    }

    @Test
    public void getBehaviorSubjectWithDefault() {
        TestObserver<Boolean> testObserver = mSut.getBehaviorSubjectWithDefault().test();

        testObserver.assertValue(true);
        testObserver.assertNotComplete();
    }

    @Ignore // I would expect this test to pass - both methods should produce same result
    @Test
    public void flatMapCompletable_replaces_MaybeFirstElementIgnoreElement() {

        TestObserver<Void> testObserver = mSut.flatMapCompletable().test();

        testObserver.assertError(EgisException.class);

        TestObserver<Void> testObserver2 = mSut.firstElementIgnoreElement().test();

        testObserver2.assertComplete();
    }


    @Ignore // I would expect this test to pass - both methods should produce same result
    @Test
    public void flatMapCompletableWithEmission() {

        TestObserver<Void> testObserver = mSut.flatMapCompletableWithEmission().test();

        testObserver.assertComplete();
    }

}