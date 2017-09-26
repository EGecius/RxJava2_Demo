package com.egecius.rxjava2_demo_2.rx.startWith;

import java.util.List;

import io.reactivex.Observable;

public class StartWithExamples {

    public Observable<Integer> startWith(List<Integer> list) {
        return Observable.just(1, 2, 3)
                .startWith(Observable.fromIterable(list));
    }
}
