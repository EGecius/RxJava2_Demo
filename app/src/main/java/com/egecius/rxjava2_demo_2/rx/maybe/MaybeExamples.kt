package com.egecius.rxjava2_demo_2.rx.maybe

import com.egecius.rxjava2_demo_2.rx.EgisException
import io.reactivex.*
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.*

class MaybeExamples {

    var isFlatMapCompletableExecuted: Boolean = false
    var isAndThenExecuted: Boolean = false

    private val completable: CompletableSource
        get() {
            isFlatMapCompletableExecuted = true
            return Completable.complete()
        }

    val behaviorSubjectWithDefault: BehaviorSubject<Boolean>
        get() = BehaviorSubject.createDefault(true)

    fun maybeToSingle(param: Int?): Single<Int> {

        return Maybe.just(param!!)
                .filter { integer -> integer < 5 }
                .toSingle()
    }

    /**
     * defaultIfEmpty() surprisingly returns Empty, even though it could return Single, since it
     * won't be empty now.
     */
    fun defaultIfEmpty(param: Int?): Single<Int> {

        val maybe: Maybe<Int>

        if (param == null) {
            maybe = Maybe.empty()
        } else {
            maybe = Maybe.just(param)
        }

        return maybe
                .defaultIfEmpty(-1)
                .toSingle()
    }

    /** flatMapCompletable will not get executed if origin Maybe is empty  */
    fun maybeFlatMapCompletable(integers: Iterable<Int>): Completable {
        return Observable.fromIterable(integers)
                .firstElement()
                .flatMapCompletable { integer -> completable }
    }

    /** andThen() gets executed even if Function in flatMapCompletable() before it did not get
     * executed   */
    fun maybeFlatMapCompletableAndThen(integers: Iterable<Int>): Completable {
        return Observable.fromIterable(integers)
                .firstElement()
                .flatMapCompletable { integer -> completable }
                .andThen(myAndThen())
    }

    private fun myAndThen(): CompletableSource {
        isAndThenExecuted = true
        return Completable.complete()
    }

    fun filterEvenAndMapToString(list: List<Int>): Maybe<String> {

        return Observable.fromIterable(list)
                .firstElement()
                .filter({ this.isEven(it) })
                .map({ it.toString() })
    }

    private fun isEven(integer: Int?): Boolean {
        return integer!! % 2 == 0
    }

    /** flatMapSingle fails with [NoSuchElementException] when it receives no value, i.e.
     * when Maybe completes rather than calls onSuccess()  */
    fun flatMapSingle(list: List<Int>): Single<String> {

        return Observable.fromIterable(list)
                .firstElement()
                .flatMapSingle { integer -> Single.just(integer.toString()) }
    }

    fun maybeIgnoreElementFromSuccess(): Completable {
        return Maybe.just(5).ignoreElement()
    }

    fun maybeIgnoreElementFromEmpty(): Completable {
        return Maybe.empty<Any>().ignoreElement()
    }

    fun maybeIgnoreElementFromError(): Completable {
        return Maybe.error<Any>(EgisException())
                .ignoreElement()
    }

    fun flatMapCompletable(): Completable {
        val behaviorSubject = behaviorSubjectWithDefault
        return behaviorSubject.flatMapCompletable { aBoolean -> Completable.complete() }
    }

    fun flatMapCompletableWithEmission(): Completable {
        val behaviorSubject = behaviorSubjectWithDefault
        behaviorSubject.onNext(true)
        val flatMapCompletable = behaviorSubject.flatMapCompletable { aBoolean -> Completable.complete() }

        behaviorSubject.onNext(true)

        return flatMapCompletable
    }

    fun firstElementIgnoreElement(): Completable {
        val behaviorSubject = behaviorSubjectWithDefault
        return behaviorSubject
                .firstElement()
                .ignoreElement()
    }
}
