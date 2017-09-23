package com.egecius.rxjava2_demo_2.rx.flatten;

import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class FlattenAsObservableExamples {

	Observable <String> flattenAsObservable(String... strings) {

		return Single.just(strings)
				.flattenAsObservable(new Function<String[], Iterable<String>>() {
					@Override
					public Iterable<String> apply(@NonNull final String[] strings) throws Exception {
						return Arrays.asList(strings);
					}
				});


	}
}
