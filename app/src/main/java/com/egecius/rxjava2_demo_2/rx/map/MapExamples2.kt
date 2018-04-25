package com.egecius.rxjava2_demo_2.rx.map

import android.annotation.SuppressLint
import io.reactivex.Observable
import java.util.function.Consumer

class MapExamples2 {

    /** Takes list of string and concatenates it into one long string  */
    fun concatenate(list: List<String>): Observable<String> {
        val justObservable = Observable.just(list)
        return justObservable.map({
            concatenateList(it)
        })
    }

    @SuppressLint("NewApi")
    private fun concatenateList(list: List<String>): String {
        val stringBuilder = StringBuilder()
        list.forEach(Consumer<String>() { stringBuilder.append(it) })
        return stringBuilder.toString()
    }

}
