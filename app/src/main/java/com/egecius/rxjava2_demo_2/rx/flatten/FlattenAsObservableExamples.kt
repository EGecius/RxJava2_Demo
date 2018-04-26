package com.egecius.rxjava2_demo_2.rx.flatten

import io.reactivex.Observable
import io.reactivex.Single
import java.util.*

class FlattenAsObservableExamples {

    internal fun flattenAsObservable(array: Array<String> ): Observable<String> {

        return Single.just<Array<String>>(array)
                .flattenAsObservable {Arrays.asList(*it) }


    }
}
