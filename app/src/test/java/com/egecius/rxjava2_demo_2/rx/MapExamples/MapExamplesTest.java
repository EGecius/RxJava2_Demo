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
	public void flatmaps() {
		TestObserver<String> testObserver = mSut.flatmap(list0to2).test();

		testObserver.assertNoErrors();
		testObserver.assertComplete();
		List<String> values = testObserver.values();
		assertThat(values.size()).isEqualTo(3);
		assertThat(values.get(0)).isEqualTo("zero");
		assertThat(values.get(1)).isEqualTo("one");
		assertThat(values.get(2)).isEqualTo("two");
	}

	@Test
	public void fromIterable() {
		TestObserver<String> testObserver = mSut.fromIterable(list0to2).test();

		testObserver.assertNoErrors();
		testObserver.assertComplete();
		List<String> values = testObserver.values();
		assertThat(values.size()).isEqualTo(3);
		assertThat(values.get(0)).isEqualTo("zero");
		assertThat(values.get(1)).isEqualTo("one");
		assertThat(values.get(2)).isEqualTo("two");
	}

	@Test
	public void flatmaps_onSingle() {
		TestObserver<String> testObserver = mSut.flatmapOnSingle(69).test();

		testObserver.assertNoErrors();
		testObserver.assertComplete();
		List<String> values = testObserver.values();
		assertThat(values.size()).isEqualTo(1);
		assertThat(values.get(0)).isEqualTo("69");
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