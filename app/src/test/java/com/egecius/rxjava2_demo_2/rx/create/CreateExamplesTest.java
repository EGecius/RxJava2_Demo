package com.egecius.rxjava2_demo_2.rx.create;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.TestObserver;

import static org.assertj.core.api.Assertions.assertThat;

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

	@Test
	public void doOnDispose() {
		List<Integer> list = Arrays.asList(3, 2, 1);
		Disposable disposable = mSut.doOnDispose(list).subscribe();
		assertThat(mSut.isCalledDoOnDispose()).isFalse();

		disposable.dispose();

		assertThat(mSut.isCalledDoOnDispose()).isTrue();
	}

	/* is OnComplete called when we dispose Observable? */

	@Test
	public void doOnComplete_notCalledOnDispose() {
		List<Integer> list = Arrays.asList(3, 2, 1);
		Disposable disposable = mSut.doOnComplete(list).subscribe();

		disposable.dispose();

		assertThat(mSut.isCalledDoOnComplete()).isFalse();
	}

	@Test
	public void doOnComplete_calledOnComplete() {
		List<Integer> list = Arrays.asList(3, 2, 1);
		mSut.doOnComplete(list).subscribe();
		assertThat(mSut.isCalledDoOnComplete()).isFalse();

		mSut.callOnComplete();

		assertThat(mSut.isCalledDoOnComplete()).isTrue();
	}

}