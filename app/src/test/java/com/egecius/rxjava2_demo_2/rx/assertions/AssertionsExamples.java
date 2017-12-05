package com.egecius.rxjava2_demo_2.rx.assertions;

import io.reactivex.Observable;

public class AssertionsExamples {
    Observable<Integer> getJust() {
        return Observable.just(1);
    }
}
