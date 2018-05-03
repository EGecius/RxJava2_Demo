package com.egecius.rxjava2_demo_2.rx.other

import com.egecius.rxjava2_demo_2.rx.utils.TestingUtils
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.junit.Test

class ExceptionExamplesTest {

    @Test(expected = RuntimeException::class)
    fun failsWhenExceptionIsThrownInOnSuccess() {

        TestingUtils.setUncaughtExceptionsHandlerAsStrict()

        Completable.complete()
                .subscribe { throw RuntimeException() }
    }

    @Test
    fun succeedsWhenExceptionIsThrownInOnError() {

        TestingUtils.setUncaughtExceptionsHandlerAsStrict()

        Completable.complete()
                .subscribe({
                    //do nothing on complete
                }, { throw RuntimeException() })
    }

    @Test(expected = RuntimeException::class)
    fun onSubscribeFailure() {

        Observable.just(1)
                .subscribe(object : Observer<Int> {
                    override fun onSubscribe(d: Disposable) {
                        throw RuntimeException()
                    }

                    override fun onNext(integer: Int) {

                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {}
                })
    }

    @Test(expected = RuntimeException::class)
    fun onNextFailure() {

        Observable.just(1)
                .subscribe(object : Observer<Int> {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onNext(integer: Int) {
                        throw RuntimeException()
                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {}
                })
    }

    @Test
    fun onErrorFailure() {

        Observable.just(1)
                .subscribe(object : Observer<Int> {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onNext(integer: Int) {}

                    override fun onError(e: Throwable) {
                        throw RuntimeException()
                    }

                    override fun onComplete() {}
                })
    }

    @Test(expected = RuntimeException::class)
    fun onCompleteFailure() {

        Observable.just(1)
                .subscribe(object : Observer<Int> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(integer: Int) {

                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {
                        throw RuntimeException()
                    }
                })
    }
}
