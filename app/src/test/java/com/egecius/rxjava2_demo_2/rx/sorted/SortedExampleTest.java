package com.egecius.rxjava2_demo_2.rx.sorted;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.observers.TestObserver;

import static org.assertj.core.api.Assertions.assertThat;

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

	@Test
	public void toList() {
		Integer[] integers = {4, 3, 1, 2};
		TestObserver<List<Integer>> testObserver = mSut.toList(integers).test();

		testObserver.assertNoErrors()
				.assertComplete()
				.assertValueCount(1);

		List<Integer> values = testObserver.values().get(0);
		assertThat(values.get(0)).isEqualTo(4);
		assertThat(values.get(1)).isEqualTo(3);
		assertThat(values.get(2)).isEqualTo(1);
		assertThat(values.get(3)).isEqualTo(2);
	}

}