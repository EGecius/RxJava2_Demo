package com.egecius.rxjava2_demo_2.rx.map

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.Single


class MapExamples {

    /** Takes list of string and concatenates it into one long string  */
    fun concatenate(list: List<String>): Observable<String> {
        val justObservable = Observable.just(list)
        return justObservable.map( {
            concatenateList(it)
        })
    }

    @SuppressLint("NewApi")
    private fun concatenateList(list: List<String>): String {

        val stringBuilder = StringBuilder()
        list.forEach({ stringBuilder.append(it) })

        return stringBuilder.toString()
    }

    /** Achieves same as above but only with much simpler map()  */
    fun mapOnSingle(integerOuter: Int?): Single<String> {
        return Single.just(integerOuter!!)
                .map({ it.toString() })
    }

    /**
     * How does the stream terminate if null value occurs in stream?
     * It terminates with NullPointerException. Null value does not get emitted.
     */
    fun mapToNull(integer: Int?): Single<String> {
        return Single.just(integer!!)
                .map { null }
    }

    fun mapToNullEvenNumbers(list: List<Int>): Observable<String> {
        fromIterable@ return Observable.fromIterable(list)
                .map({function(it)})
    }

    private fun function ( integer: Int) : String? {
            if (integer % 2 == 0) {
               return null
            }

            return integer.toString()
    }
}
