package com.egecius.rxjava2_demo_2.rx.errors

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.annotations.NonNull
import io.reactivex.functions.Consumer
import org.junit.Test


class ErrorExamplesTest {

    private fun setUncaughtExceptionHandlerAsStrict() {
        Thread.currentThread().setUncaughtExceptionHandler({ _, throwable ->
            throw IllegalStateException(throwable)
        })
    }

    // when error is emitted but onError does not get implemented, execution does not crash on
    // Desktop but crashes on more Strict Android
    //
    // What happens is:
    // 1) exception is wrapped in OnErrorNotImplementedException and printed at the end of
    // 2) RxJavaPlugins.onError()
    // 3) passed to UncaughtExceptionHandler
    // 4) which passes exception to current thread's UncaughtExceptionHandler. On desktop it
    // does not crash app but on Android it does

    @Test(expected = RuntimeException::class)
    fun Observable_Error() {
        setUncaughtExceptionHandlerAsStrict()

        val observable = Observable.error<Any>(RuntimeException())

        //intentionally do not implement onError to see if it will fail
        observable.subscribe { o ->
            //nothing
        }

        // if execution has not failed by this point, test has passed
    }

    @Test
    fun Observable_Error_doesNotFailWhenOnErrorImplemented() {
        setUncaughtExceptionHandlerAsStrict()

        val observable = Observable.error<Any>(RuntimeException())

        //intentionally do not implement onError to see if it will fail
        observable.subscribe({ o ->
            //nothing
        }) { throwable ->
            //implements onError
        }

        // if execution has not failed by this point, test has passed
    }


    @Test(expected = RuntimeException::class)
    fun Single_Error() {
        setUncaughtExceptionHandlerAsStrict()

        val single = Single.error<Any>(RuntimeException())

        //intentionally do not implement onError to see if it will fail
        single.subscribe { o ->
            //nothing
        }

        // if execution has not failed by this point, test has passed
    }


    @Test(expected = RuntimeException::class)
    fun Completable_Error() {
        setUncaughtExceptionHandlerAsStrict()

        val completable = Completable.error(RuntimeException())

        //intentionally do not implement onError to see if it will fail
        completable.subscribe {
            // nothing
        }

        // if execution has not failed by this point, test has passed
    }

    @Test(expected = RuntimeException::class)
    fun Maybe_Error() {
        setUncaughtExceptionHandlerAsStrict()

        val maybe = Maybe.error<Any>(RuntimeException())

        //intentionally do not implement onError to see if it will fail
        maybe.subscribe(object : Consumer<Any> {
            @Throws(Exception::class)
            override fun accept(@NonNull o: Any) {

            }
        })

        // if execution has not failed by this point, test has passed
    }

}