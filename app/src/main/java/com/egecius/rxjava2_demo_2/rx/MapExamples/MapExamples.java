package com.egecius.rxjava2_demo_2.rx.MapExamples;

import java.util.List;
import java.util.function.BiPredicate;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
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
					public ObservableSource<String> apply(@NonNull final List<String> strings) throws
							Exception {
						return Observable.fromIterable(strings);
					}
				});
	}

	/** Takes list & slices it into its elements. Same effect as using flatmap above */
	public Observable<String> fromIterable(final List<String> list) {
		return Observable.fromIterable(list);
	}


}
