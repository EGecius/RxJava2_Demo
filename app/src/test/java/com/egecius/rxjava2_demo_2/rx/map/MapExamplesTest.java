package com.egecius.rxjava2_demo_2.rx.map;

import static org.assertj.core.api.Assertions.assertThat;

import static java.util.Arrays.asList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.TestObserver;

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
	public void whenMapsToNullStreamFailsWithNoValuesEmitted() {
		TestObserver<String> testObserver = mSut.mapToNull(69).test();

		testObserver
                .assertNoValues()
                .assertError(NullPointerException.class);
	}

	/** Stream only fails when it maps to null. All values emitted prior to null still reach
     * Observer */
	@Test
	public void whenMapsToNullPriorValidValuesGetEmitted() {
        List<Integer> list = asList(1, 2, 3);
        TestObserver<String> testObserver = mSut.mapToNullEvenNumbers(list).test();

        testObserver
                .assertValue("1")
                .assertError(NullPointerException.class);
    }

}