package com.egecius.rxjava2_demo_2.rx.map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.TestObserver;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MapExamplesTest {

	private MapExamples mSut;
	private List<String> list0to2 = new ArrayList<String>() {{
		add("zero");
		add("one");
		add("two");
	}};


	@Before
	public void setup() {
		mSut = new MapExamples();
	}

	@Test
	public void concatenatesWithMap() {

		TestObserver<String> testObserver = mSut.concatenate(list0to2).test();

		testObserver.assertNoErrors();
		testObserver.assertComplete();
		List<String> values = testObserver.values();
		assertThat(values.size()).isEqualTo(1);
		assertThat(values.get(0)).isEqualTo("zeroonetwo");
	}

	@Test
	public void maps_onSingle() {
		TestObserver<String> testObserver = mSut.mapOnSingle(69).test();

		testObserver.assertNoErrors();
		testObserver.assertComplete();
		List<String> values = testObserver.values();
		assertThat(values.size()).isEqualTo(1);
		assertThat(values.get(0)).isEqualTo("69");
	}

	@Test
	public void mapFailsWhenEmitsNull() {
		TestObserver<String> testObserver = mSut.mapToNull(69).test();

		testObserver.assertComplete();
		List<String> values = testObserver.values();
		assertThat(values.get(0)).isNull();
		testObserver.assertError(NullPointerException.class);
	}

}