package com.egecius.rxjava2_demo_2.rx.MapExamples;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class FlatMapExamples {

	/** Takes list & slices it into its elements */
	public Observable<String> flatmap(final List<String> list) {
		return Observable.just(list)
				.flatMap(new Function<List<String>, ObservableSource<String>>() {
					@Override
					public ObservableSource<String> apply(@NonNull final List<String> strings)
							throws
							Exception {
						return Observable.fromIterable(strings);
					}
				});
	}

	/** Takes list & slices it into its elements. Same effect as using flatmap above */
	public Observable<String> fromIterable(final List<String> list) {
		return Observable.fromIterable(list);
	}

	public Single<String> flatmapOnSingle(final Integer integerOuter) {
		return Single.just(integerOuter)
				.flatMap(new Function<Integer, SingleSource<String>>() {
					@Override
					public SingleSource<String> apply(@NonNull final Integer integerInner) throws
							Exception {
						return Single.just(String.valueOf(integerInner));
					}
				});
	}

	public Observable<String> flatMapOneToOne(final Integer integer) {
		return Observable.just(integer)
				.flatMap(new Function<Integer, Observable<String>>() {
					@Override
					public Observable<String> apply(@NonNull final Integer integer) throws
							Exception {
						return Observable.just(String.valueOf(integer));
					}
				});
	}
}
