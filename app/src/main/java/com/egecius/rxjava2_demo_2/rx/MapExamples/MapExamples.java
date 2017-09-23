package com.egecius.rxjava2_demo_2.rx.MapExamples;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


public class MapExamples {

	/** Takes list of string and concatenates it into one long string */
	public Observable<String> concatenate(final List<String> list) {
		Observable<List<String>> justObservable = Observable.just(list);
		return justObservable.map(this::concatenateList);
	}

	private String concatenateList(final List<String> list) {

		StringBuilder stringBuilder = new StringBuilder();
		list.forEach(stringBuilder::append);

		return stringBuilder.toString();
	}

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

	/** Achieves same as above but only with much simpler map() */
	public Single<String> mapOnSingle(final Integer integerOuter) {
		return Single.just(integerOuter)
				.map(String::valueOf);
	}

	public Single<String> mapToNull(final Integer integer) {
		return Single.just(integer)
				.map(new Function<Integer, String>() {
					@Override
					public String apply(@NonNull final Integer integer) throws Exception {
						return null;
					}
				});
	}
}
