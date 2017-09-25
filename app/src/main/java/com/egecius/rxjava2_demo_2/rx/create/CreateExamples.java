package com.egecius.rxjava2_demo_2.rx.create;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;

public class CreateExamples {

	private boolean isCalledDoOnDispose;

	public Observable<Integer> createObservable(final List<Integer> list) {
		return Observable.create(new ObservableOnSubscribe<Integer>() {
			@Override
			public void subscribe(@NonNull final ObservableEmitter<Integer> emitter) throws Exception {
				emitList(list, emitter);
				emitter.onComplete();
			}
		});
	}

	public boolean isCalledDoOnDispose() {
		return isCalledDoOnDispose;
	}

	public Observable<Integer> doOnDispose(final List<Integer> list) {
		Observable<Integer> observable = Observable.create(emitter -> {
			emitList(list, emitter);
			// intentionally not calling onComplete to call emitter subscribed to
		});
		return observable.doOnDispose(() -> isCalledDoOnDispose = true);
	}

	private void emitList(final List<Integer> list, final ObservableEmitter<Integer> emitter) {
		for (final Integer integer : list) {
			emitter.onNext(integer);
		}
	}
}
