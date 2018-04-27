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

        // alternative in longer form
//        return ObservableTransformer<Int, String>() { upstream: Observable<Int> ->
//            upstream
//                    .map(Function<Int, Int>() { integer: Int -> integer * 2 })
//                    .map(Function<Int, String>() { integer: Int -> integer.toString() })
//        }

        // in full form

//        return object: ObservableTransformer<Int, String> {
//            override fun apply(upstream: Observable<Int>): ObservableSource<String> {
//                return upstream
//                        .map(object: Function<Int, Int> {
//                            override fun apply(integer: Int): Int {
//                                return integer * 2
//                            }
//                        })
//                        .map(object: Function<Int, String> {
//                            override fun apply(integer: Int): String {
//                                return integer.toString()
//                            }
//                        } )
//            }
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
