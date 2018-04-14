package com.egecius.rxjava2_demo_2.rx.completable

import com.egecius.rxjava2_demo_2.rx.EgisException
import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.Maybe
import io.reactivex.functions.Action
import java.util.*

class CompletableExamples {

    internal var list: MutableList<String> = ArrayList()
    private val maybeA: Maybe<Path> = Maybe.just(Path.A)
    private val maybeB: Maybe<Path> = Maybe.just(Path.B)

    internal enum class Path {
        A, B
    }

    internal fun demoAndThen(): Completable {
        return firstCompletable()
                .andThen(secondCompletable())
                .andThen(thirdCompletable())
    }

    private fun firstCompletable(): Completable {
        return Completable.create { emitter ->
            list.add(FIRST_COMPLETABLE)
            emitter.onComplete()
        }
    }

    private fun secondCompletable(): Completable {
        return Completable.create { emitter ->
            list.add(SECOND_COMPLETABLE)
            emitter.onComplete()
        }
    }

    private fun thirdCompletable(): CompletableSource {
        return Completable.create { e ->
            list.add(THIRD_COMPLETABLE)
            e.onComplete()
        }
    }

    internal fun doOnCompleteAndThen(): Completable {
        return firstCompletable()
                .doOnComplete(myDoOnCompleteA())
                .andThen(secondCompletable())
                .andThen(thirdCompletable())
                .doOnComplete(myDoOnCompleteB())
    }

    private fun myDoOnCompleteA(): Action {
        return Action {
            list.add(DO_ON_COMPLETE_A)
        }
    }

    private fun myDoOnCompleteB(): Action {
        return Action { list.add(DO_ON_COMPLETE_B) }
    }


    internal fun divergingPaths(path: Path): Completable {
        return firstCompletable()
                .andThen(secondCompletable())
                .andThen(executePath(path))
    }

    private fun executePath(path: Path): CompletableSource {
        return if (path == Path.A) {
            pathACompletable()
        } else pathBCompletable()
    }

    private fun pathACompletable(): Completable {
        return Completable.create { e ->
            list.add(PATH_A_COMPLETABLE)
            e.onComplete()
        }
    }

    private fun pathBCompletable(): Completable {
        return Completable.create { e ->
            list.add(PATH_B_COMPLETABLE)
            e.onComplete()
        }
    }

    internal fun divergingPathWithMaybeA(): Completable {
        return firstCompletable()
                .andThen(secondCompletable())
                .andThen(maybeA)
                .flatMapCompletable({ executePath(it) })
    }

    internal fun divergingPathWithMaybeB(): Completable {
        return firstCompletable()
                .andThen(secondCompletable())
                .andThen(maybeB)
                .flatMapCompletable({ executePath(it) })
    }

    internal fun andThenWithErrors(): Completable {
        return firstCompletable()
                .andThen(Completable.error(ERROR_1))
                .andThen(secondCompletable())
                .andThen(Completable.error(ERROR_2))
                .andThen(thirdCompletable())
    }

    internal fun fromActionComplete(): Completable {
        return Completable.fromAction { list.add(FIRST_COMPLETABLE) }
    }

    internal fun fromActionError(): Completable {
        return Completable.fromAction { throw EgisException() }
    }

    companion object {

        val ERROR_1 = Exception("ERROR_1")
        val ERROR_2: Throwable = Exception("ERROR_2")

        val FIRST_COMPLETABLE = "first_completable"
        val SECOND_COMPLETABLE = "second_completable"
        val THIRD_COMPLETABLE = "third_completable"
        val DO_ON_COMPLETE_A = "DO_ON_COMPLETE_A"
        val DO_ON_COMPLETE_B = "DO_ON_COMPLETE_B"
        val PATH_A_COMPLETABLE = "PATH_A_COMPLETABLE"
        val PATH_B_COMPLETABLE = "PATH_B_COMPLETABLE"
    }

}
