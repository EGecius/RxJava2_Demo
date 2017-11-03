package com.egecius.rxjava2_demo_2.rx.subjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.ReplaySubject;

class SubjectsExamples {

    ReplaySubject<Integer> defaultReplaySubject(List<Integer> list) {

        Observable<Integer> observable = Observable.fromIterable(list);
        ReplaySubject<Integer> subject = ReplaySubject.create();
        observable.subscribe(subject);

        return subject;
    }

    /** Returned subject limits its cash to 2 */
    ReplaySubject<Integer> createWithSize2(List<Integer> list1, List<Integer> list2) {

        Observable<Integer> observable1 = Observable.fromIterable(list1);
        Observable<Integer> observable2 = Observable.fromIterable(list2);

        ReplaySubject<Integer> subject = ReplaySubject.createWithSize(2);

        observable1.subscribe(subject);
        observable2.subscribe(subject);

        return subject;
    }


    /** This subject cashes events from last 21 millis only. Since thread will sleep for 30
     * millis, only last events will be received: "two" "three" */
    ReplaySubject<String> createWithTime() throws InterruptedException {
        ReplaySubject<String> subject = ReplaySubject.createWithTime(20, TimeUnit.MILLISECONDS,
                Schedulers.trampoline());

        subject.onNext("one");

        Thread.sleep(10);

        subject.onNext("two");

        Thread.sleep(10);

        subject.onNext("three");

        Thread.sleep(1);

        return subject;
    }
}
