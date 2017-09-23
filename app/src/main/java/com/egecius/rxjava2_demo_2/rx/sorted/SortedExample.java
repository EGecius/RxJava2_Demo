package com.egecius.rxjava2_demo_2.rx.sorted;


import java.util.Arrays;

import io.reactivex.Observable;

public class SortedExample {

	public Observable<Integer> sort(final Integer[] integers) {
		return Observable.just(integers)
				.map(Arrays::asList)
				.flatMap(Observable::fromIterable)
				.sorted();

	}
}
