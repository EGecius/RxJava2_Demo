package com.egecius.rxjava2_demo_2.rx.startWith;

import java.util.List;

import io.reactivex.Observable;

public class StartWithExamples {

    public Observable<Integer> startWithObservable(List<Integer> list) {
        Observable<Integer> observable = Observable.fromIterable(list);
        return Observable.just(1, 2, 3)
                .startWith(observable);
    }

    public Observable<Integer> startWithIterable(Iterable<Integer> iterable) {
        return Observable.just(1, 2, 3)
                .startWith(iterable);
    }

    public Observable<Integer> startWithArray(Integer... integers) {
        return Observable.just(1, 2, 3)
                .startWithArray(integers);
    }

    public Observable<Integer> startWithElement(Integer integer) {
        return Observable.just(1, 2, 3)
                .startWith(integer);
    }
}
