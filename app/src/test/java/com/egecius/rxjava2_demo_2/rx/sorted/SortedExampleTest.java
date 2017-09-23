package com.egecius.rxjava2_demo_2.rx.sorted;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.observers.TestObserver;

/**
 * Tests for {@link SortedExample}
 */
@RunWith(MockitoJUnitRunner.class)
public class SortedExampleTest {

	SortedExample mSut;

	@Before
	public void setup() {
		mSut = new SortedExample();
	}

	@Test
	public void sorted() {
		Integer[] integers = {4, 3, 1, 2};
		TestObserver<Integer> testObserver = mSut.sort(integers).test();

		testObserver.assertNoErrors()
				.assertComplete()
				.assertValueCount(4)
				.assertValues(1, 2, 3, 4);
	}

}