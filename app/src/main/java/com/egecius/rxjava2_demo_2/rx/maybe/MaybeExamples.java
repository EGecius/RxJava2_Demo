package com.egecius.rxjava2_demo_2.rx.maybe;

import io.reactivex.Maybe;
import io.reactivex.Single;

public class MaybeExamples {

	public Single<Integer> maybeToSingle(Integer param) {

		return Maybe.just(param)
				.filter(integer -> integer < 5)
				.toSingle();
	}
}
