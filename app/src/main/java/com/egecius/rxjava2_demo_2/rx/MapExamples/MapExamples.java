package com.egecius.rxjava2_demo_2.rx.MapExamples;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


public class MapExamples {

	public Observable<String> concatenate(final List<String> list) {
		Observable<List<String>> justObservable = Observable.just(list);
		return justObservable.map(new Function<List<String>, String>() {
			@Override
			public String apply(@NonNull final List<String> strings) throws Exception {
				return concatenateList(strings);
			}
		});
	}

	private String concatenateList(final List<String> list) {

		StringBuilder stringBuilder = new StringBuilder();

		for (final String string : list) {
			stringBuilder.append(string);
		}
		
		return stringBuilder.toString();
	}
}
