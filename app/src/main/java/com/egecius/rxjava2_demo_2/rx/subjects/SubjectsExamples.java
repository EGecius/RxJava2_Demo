package com.egecius.rxjava2_demo_2.rx.subjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.ReplaySubject;

public class SubjectsExamples {

    /** Returned subject limits its cash to 2 */
    ReplaySubject<Integer> createWithSize2(List<Integer> list1, List<Integer> list2) {

        Observable<Integer> observable1 = Observable.fromIterable(list1);
        Observable<Integer> observable2 = Observable.fromIterable(list2);

        ReplaySubject<Integer> subject = ReplaySubject.createWithSize(2);

        observable1.subscribe(subject);
        observable2.subscribe(subject);

        return subject;
    }

}
