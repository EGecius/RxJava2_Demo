package com.egecius.rxjava2_demo_2.rx.sorted;


import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public class SortedExample {

	public Observable<Integer> sort(final Integer[] integers) {
		return Observable.just(integers)
				.map(Arrays::asList)
				.flatMap(Observable::fromIterable)
				.sorted();
	}

	public Single<List<Integer>> toList(final Integer[] integers) {
		return Observable.just(integers)
				.map(Arrays::asList)
				.flatMap(Observable::fromIterable)
				.toList();
	}
}
