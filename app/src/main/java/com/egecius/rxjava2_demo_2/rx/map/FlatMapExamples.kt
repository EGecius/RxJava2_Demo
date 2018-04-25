package com.egecius.rxjava2_demo_2.rx.map

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.functions.Function

class FlatMapExamples {

    /** Takes list & slices it into its elements  */
    fun flatMap(list: List<String>): Observable<String> {
        return Observable.just(list)
                .flatMap { strings -> Observable.fromIterable(strings) }
    }

    /** Takes list & slices it into its elements. Same effect as using [.flatMap]}  */
    fun fromIterable(list: List<String>): Observable<String> {
        return Observable.fromIterable(list)
    }

    fun flatmapOnSingle(integerOuter: Int?): Single<String> {
        return Single.just(integerOuter!!)
                .flatMap(object : Function<Int, SingleSource<String>> {
                    override fun apply(integerInner: Int): SingleSource<String> {
                        return Single.just(integerInner.toString())
                    }
                })
    }

    fun flatMapOneToOne(integer: Int?): Observable<String> {
        return Observable.just(integer!!)
                .flatMap(object : Function<Int, Observable<String>> {
                    override fun apply(integer: Int): Observable<String> {
                        return Observable.just(integer.toString())
                    }
                })
    }

    fun flatMapOneToNone(integer: Int?): Observable<String> {
        return Observable.just(integer!!)
                .flatMap(object : Function<Int, Observable<String>> {
                    override fun apply(integer: Int): Observable<String> {
                        return Observable.empty()
                    }
                })
    }

    fun flatMapOneToSometimes(list: List<Int>): Observable<Int> {
        return Observable.fromIterable(list)
                .flatMap(object : Function<Int, Observable<Int>> {
                    override fun apply(integer: Int): Observable<Int> {

                        return if (isEven(integer)) {
                            Observable.empty()
                        } else {
                            Observable.just(integer)
                        }
                    }

                    private fun isEven(integer: Int?): Boolean {
                        return integer!! % 2 == 0
                    }
                })
    }

    fun singleFlatMapObservable(list: List<Int>): Observable<Int> {
        return Single.just(list)
                .flatMapObservable { integers -> Observable.fromIterable(integers) }
    }
}
