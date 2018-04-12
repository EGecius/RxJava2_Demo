package com.egecius.rxjava2_demo_2.rx.create

import io.reactivex.Observable
import io.reactivex.ObservableEmitter

class CreateExamples {

    var isCalledDoOnDispose: Boolean = false
        private set
    private var isCalledDoOnComplete: Boolean = false
    private var emitter: ObservableEmitter<Int>? = null

    fun createObservable(list: List<Int>): Observable<Int> {
        return Observable.create { emitter ->
            emitList(list, emitter)
            emitter.onComplete()
        }
    }

    fun doOnDispose(list: List<Int>): Observable<Int> {
        val observable = Observable.create<Int> { emitter ->
            emitList(list, emitter)
            // intentionally not calling onComplete to call emitter subscribed to
        }
        return observable.doOnDispose { isCalledDoOnDispose = true }
    }

    private fun emitList(list: List<Int>, emitter: ObservableEmitter<Int>) {
        for (integer in list) {
            emitter.onNext(integer)
        }
    }

    fun isCalledDoOnComplete(): Boolean {
        return false
    }

    fun doOnComplete(list: List<Int>): Observable<Int> {
        val observable = Observable.create<Int> { emitter ->
            this.emitter = emitter
            emitList(list, emitter)
            // intentionally not calling onComplete to call emitter subscribed to
        }
        return observable.doOnComplete { isCalledDoOnComplete = true }
    }

    fun callOnComplete() {
        emitter!!.onComplete()
    }
}
