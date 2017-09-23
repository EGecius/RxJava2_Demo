package com.egecius.rxjava2_demo_2.rx.MapExamples;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.TestObserver;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link MapExamples}
 */
@RunWith(MockitoJUnitRunner.class)
public class MapExamplesTest {

	private MapExamples mSut;

	@Before
	public void setup() {
		mSut = new MapExamples();
	}

	@Test
	public void concatenatesWithMap() {
		List<String> list = new ArrayList<String>() {{
			add("one");
			add("two");
			add("three");
		}};

		TestObserver<String> testObserver = mSut.concatenate(list).test();

		testObserver.assertNoErrors();
		testObserver.assertComplete();
		List<String> values = testObserver.values();
		assertThat(values.size()).isEqualTo(1);
		assertThat(values.get(0)).isEqualTo("onetwothree");
	}

}