package com.egecius.rxjava2_demo_2.rx.map;

import android.annotation.SuppressLint;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;


@SuppressWarnings({"SameParameterValue", "WeakerAccess"})
public class MapExamples {

	/** Takes list of string and concatenates it into one long string */
	public Observable<String> concatenate(final List<String> list) {
		Observable<List<String>> justObservable = Observable.just(list);
		return justObservable.map(this::concatenateList);
	}

	@SuppressLint("NewApi")
    private String concatenateList(final List<String> list) {

		StringBuilder stringBuilder = new StringBuilder();
		list.forEach(stringBuilder::append);

		return stringBuilder.toString();
	}

	/** Achieves same as above but only with much simpler map() */
	public Single<String> mapOnSingle(final Integer integerOuter) {
		return Single.just(integerOuter)
				.map(String::valueOf);
	}

	/**
     * How does the stream terminate if null value occurs in stream?
     * It terminates with NullPointerException. Null value does not get emitted.
	 */
	public Single<String> mapToNull(final Integer integer) {
		return Single.just(integer)
				.map(integer1 -> null);
	}

    public Observable<String> mapToNullEvenNumbers(List<Integer> list) {
        return Observable.fromIterable(list)
                .map(integer -> {

                    if (integer % 2 == 0) {
                        return null;
                    }

                    return String.valueOf(integer);
                });
    }
}
