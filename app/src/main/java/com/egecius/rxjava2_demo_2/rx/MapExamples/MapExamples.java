package com.egecius.rxjava2_demo_2.rx.MapExamples;

import java.util.List;

import io.reactivex.Observable;


public class MapExamples {

	public Observable<String> concatenate(final List<String> list) {
		Observable<List<String>> justObservable = Observable.just(list);
		return justObservable.map(this::concatenateList);
	}

	private String concatenateList(final List<String> list) {

		StringBuilder stringBuilder = new StringBuilder();
		list.forEach(stringBuilder::append);

		return stringBuilder.toString();
	}
}
