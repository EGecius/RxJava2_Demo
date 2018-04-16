@file:Suppress("MemberVisibilityCanBePrivate")

package com.egecius.rxjava2_demo_2.rx.compose

import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class ComposeExamples {

    internal fun doubleAndConvertToString(item: Int): Observable<String> {
        return Observable
                .just(item)
                .compose(doubleAndConvertToString())
    }

    fun doubleAndConvertToString(): ObservableTransformer<Int, String> {
        return ObservableTransformer { upstream ->
            upstream
                    .map { it * 2 }
                    .map { it.toString() }
        }

        // alternative in full form
        //        return ObservableTransformer<Int, String> { upstream ->
//                  upstream
//                    .map ( Function<Int, Int>(){ integer: Int -> integer * 2 })
//                    .map ( Function<Int, String>(){ it.toString() })
//        }
    }

    internal fun doubleAndConvertToStringRepeatedThrice(item: Int): Observable<String> {
        return Observable
                .just(item)
                .compose(doubleAndConvertToString())
                .compose(repeatThrice())
    }

    private fun repeatThrice(): ObservableTransformer<String, String> {
        return ObservableTransformer { upstream ->
            upstream
                    .map { string -> string + string + string }
        }
    }

}
