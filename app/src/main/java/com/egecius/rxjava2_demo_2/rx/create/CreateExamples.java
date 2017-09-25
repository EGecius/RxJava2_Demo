package com.egecius.rxjava2_demo_2.rx.create;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

public class CreateExamples {

	public Observable<Integer> createObservable(final List<Integer> list) {
		return Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(@NonNull final ObservableEmitter<Integer> emitter) throws Exception {

				for (final Integer integer : list) {
					emitter.onNext(integer);
				}

				emitter.onComplete();
			}
		});
	}
}
