package com.egecius.rxjava2_demo_2.rx.assertions

import com.egecius.rxjava2_demo_2.rx.EgisException

import io.reactivex.Observable

class AssertionsExamples {

    val exception: EgisException
        get() = EGIS_EXCEPTION

    val exceptionMessage: String
        get() = EXCEPTION_MESSAGE

    val just: Observable<Int>
        get() = Observable.just(1)

    fun emitThreeIntegersAndFail(): Observable<Int> {
        return Observable.create { emitter ->
            for (i in 0..2) {
                emitter.onNext(i)
            }

            emitter.onError(EGIS_EXCEPTION)
        }
    }

    fun emitWithoutCompletion(item1: Int, item2: Int): Observable<Int> {
        return Observable.create { e ->
            e.onNext(item1)
            e.onNext(item2)
        }
    }

    companion object {

        private val EXCEPTION_MESSAGE = "egis"
        private val EGIS_EXCEPTION = EgisException(EXCEPTION_MESSAGE)
    }
}
