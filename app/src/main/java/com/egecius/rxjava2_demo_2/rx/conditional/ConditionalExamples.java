package com.egecius.rxjava2_demo_2.rx.conditional;

import static java.util.Arrays.asList;

import java.util.List;

import io.reactivex.Observable;

public class ConditionalExamples {

    Observable<Integer> ambExample() {

        List<Observable<Integer>> sources = asList(
                Observable.just(1, 2),
                Observable.just(11, 12));

        return Observable.amb(sources);
    }

    Observable<Integer> switchIfEmptyForEmptyStream(List<Integer> integers) {
        return Observable.<Integer> empty()
                .switchIfEmpty(Observable.fromIterable(integers));
    }

    Observable<Integer> switchIfEmptyForNonEmptyStream(List<Integer> integers) {
        return Observable.just(1)
                .switchIfEmpty(Observable.fromIterable(integers));
    }
}
