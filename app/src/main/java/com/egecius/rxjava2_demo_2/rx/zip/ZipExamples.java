package com.egecius.rxjava2_demo_2.rx.zip;

import java.util.List;

import io.reactivex.Observable;

public class ZipExamples {

    Observable<String> zipWith(List<Integer> integers, List<String> strings) {

        Observable<Integer> integerObservable = Observable.fromIterable(integers);
        Observable<String> stringObservable = Observable.fromIterable(strings);

        return integerObservable.zipWith(stringObservable,
                (integer, string) -> String.valueOf(integer) + "_" + string);
    }

    Observable<String> zip(List<Integer> integers, List<String> strings) {

        Observable<Integer> integerObservable = Observable.fromIterable(integers);
        Observable<String> stringObservable = Observable.fromIterable(strings);

        return Observable.zip(integerObservable, stringObservable,
                (integer, string) -> String.valueOf(integer) + "_" + string);
    }
}
