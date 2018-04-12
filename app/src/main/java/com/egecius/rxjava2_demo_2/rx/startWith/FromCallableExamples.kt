package com.egecius.rxjava2_demo_2.rx.startWith

import io.reactivex.Observable

class FromCallableExamples {
    var isCallableExecuted: Boolean = false
        private set

    private val stringForCallable: String
        get() = STRING_FOR_CALLABLE

    fun fromCallableThreadName(): Observable<String> {
        return Observable.fromCallable {
            isCallableExecuted = true
            stringForCallable
        }
    }

    companion object {

        internal val STRING_FOR_CALLABLE = "string for callable"
    }
}
