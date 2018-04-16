package com.egecius.rxjava2_demo_2.rx.subjects

import io.reactivex.subjects.AsyncSubject

class AsyncSubjectExamples {

    fun emitEventsWithOnComplete(list: List<Int>): AsyncSubject<Int> {

        val subject = AsyncSubject.create<Int>()

        for (integer in list) {
            subject.onNext(integer)
        }

        subject.onComplete()

        return subject
    }

     fun emitEventsWithError(list: List<Int>): AsyncSubject<Int> {

        val subject = AsyncSubject.create<Int>()

        for (integer in list) {
            subject.onNext(integer)
        }

        subject.onError(ASYNC_EXCEPTION)

        return subject
    }

     fun emitEventsWithoutTermination(list: List<Int>): AsyncSubject<Int> {
        val subject = AsyncSubject.create<Int>()

        for (integer in list) {
            subject.onNext(integer)
        }

        // no terminate event

        return subject
    }

    companion object {

        val ASYNC_EXCEPTION = Exception()
    }
}
