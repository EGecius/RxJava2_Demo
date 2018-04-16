package com.egecius.rxjava2_demo_2.rx.blockable


import io.reactivex.Observable
import io.reactivex.Single

class BlockableExamples {

    fun blockingGetForSingle(): Int? {
        return Single.just(doNetworkCall())
                .blockingGet()
    }

    private fun doNetworkCall(): Int {

        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        return 1
    }

    fun blockingGetForObservable(): Int? {
        return Observable.just(doNetworkCall())
                .blockingFirst()
    }

}
