package com.egecius.rxjava2_demo_2.rx.flatten;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.observers.TestObserver;

@RunWith(MockitoJUnitRunner.class)
public class FlattenAsObservableExamplesTest {

	FlattenAsObservableExamples mSut;

	@Before
	public void setup() {
		mSut = new FlattenAsObservableExamples();
	}

	@Test
	public void flattenAsObservable() {
		//WHEN
		String[] array = {"one", "two", "three"};
		TestObserver<String> testObserver = mSut.flattenAsObservable(array).test();
		//THEN
		testObserver.assertValues("one", "two", "three");
		testObserver.assertComplete();
	}

}