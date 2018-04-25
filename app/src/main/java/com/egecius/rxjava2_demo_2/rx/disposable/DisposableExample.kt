package com.egecius.rxjava2_demo_2.rx.disposable


import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import java.util.*

class DisposableExample {

    private lateinit var mDisposableObserver: DisposableObserver<Int>

    var throwable: Throwable? = null
        private set
    val onNextList = ArrayList<Int>()
    var isOnCompleteCalled: Boolean = false
        private set

    /** Inside subscribeWith() you can see that it returns same observer that you just passed  */
    internal fun createDisposableObserver(): DisposableObserver<Int> {

         mDisposableObserver = Observable.never<Int>()
                .subscribeWith(object : DisposableObserver<Int>() {
                    override fun onNext(t: Int) {
                        onNextList.add(t)
                    }

                    override fun onError(e: Throwable) {
                        throwable = e
                    }

                    override fun onComplete() {
                        isOnCompleteCalled = true
                    }
                })

        return mDisposableObserver
    }
}


