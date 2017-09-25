package com.egecius.rxjava2_demo_2.rx.create;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

@RunWith(MockitoJUnitRunner.class)
public class CreateExamplesTest {

	CreateExamples mSut;

	@Before
	public void setUp() {
		mSut = new CreateExamples();
	}

	@Test
	public void createObservable() {
		List<Integer> list = Arrays.asList(3, 2, 1);

		Observable<Integer> observable = mSut.createObservable(list);
		TestObserver<Integer> testObserver = observable.test();

		testObserver
				.assertComplete()
				.assertNoErrors()
				.assertValues(3, 2, 1);
	}

}