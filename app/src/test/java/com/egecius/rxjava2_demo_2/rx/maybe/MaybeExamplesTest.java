package com.egecius.rxjava2_demo_2.rx.maybe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

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

}