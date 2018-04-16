package com.egecius.rxjava2_demo_2.rx.subjects

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.ReplaySubject
import java.util.concurrent.TimeUnit

class ReplaySubjectExamples {

    fun defaultReplaySubject(list: List<Int>): ReplaySubject<Int> {

        val observable = Observable.fromIterable(list)
        val subject = ReplaySubject.create<Int>()
        observable.subscribe(subject)

        return subject
    }

    /** Returned subject limits its cash to 2  */
    fun createWithSize2(list1: List<Int>, list2: List<Int>): ReplaySubject<Int> {

        val observable1 = Observable.fromIterable(list1)
        val observable2 = Observable.fromIterable(list2)

        val subject = ReplaySubject.createWithSize<Int>(2)

        observable1.subscribe(subject)
        observable2.subscribe(subject)

        return subject
    }

    /** This subject cashes events from last 21 millis only. Since thread will sleep for 30
     * millis, only last events will be received: "two" "three"  */
    fun createWithTime(): ReplaySubject<String> {
        val subject = ReplaySubject.createWithTime<String>(20, TimeUnit.MILLISECONDS,
                Schedulers.trampoline())

        subject.onNext("one")

        Thread.sleep(10)

        subject.onNext("two")

        Thread.sleep(10)

        subject.onNext("three")

        Thread.sleep(1)

        return subject
    }

    fun createWithTime20Millis(): ReplaySubject<String> {
        val subject = ReplaySubject.createWithTime<String>(20, TimeUnit.MILLISECONDS,
                Schedulers.trampoline())

        subject.onNext("one")
        subject.onNext("two")
        subject.onNext("three")

        return subject
    }
}
