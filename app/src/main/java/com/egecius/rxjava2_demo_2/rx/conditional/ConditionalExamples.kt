package com.egecius.rxjava2_demo_2.rx.conditional

import io.reactivex.Observable
import java.util.Arrays.asList

class ConditionalExamples {

    fun ambExample(): Observable<Int> {

        val sources = asList(
                Observable.just(1, 2),
                Observable.just(11, 12))

        return Observable.amb(sources)
    }

    fun switchIfEmptyForEmptyStream(integers: List<Int>): Observable<Int> {
        return Observable.empty<Int>()
                .switchIfEmpty(Observable.fromIterable(integers))
    }

    fun switchIfEmptyForNonEmptyStream(integers: List<Int>): Observable<Int> {
        return Observable.just(1)
                .switchIfEmpty(Observable.fromIterable(integers))
    }
}
