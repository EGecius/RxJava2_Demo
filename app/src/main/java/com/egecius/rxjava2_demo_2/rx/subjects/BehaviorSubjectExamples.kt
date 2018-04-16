package com.egecius.rxjava2_demo_2.rx.subjects

import io.reactivex.subjects.BehaviorSubject

class BehaviorSubjectExamples {

    fun emit(list: List<Int>): BehaviorSubject<Int> {

        val subject = BehaviorSubject.create<Int>()

        for (integer in list) {
            subject.onNext(integer)
        }

        return subject
    }

    fun emitWithOnComplete(list: List<Int>): BehaviorSubject<Int> {

        val subject = BehaviorSubject.create<Int>()

        for (integer in list) {
            subject.onNext(integer)
        }

        subject.onComplete()

        return subject
    }

    fun emitWithError(list: List<Int>, exception: Exception): BehaviorSubject<Int> {
        val subject = BehaviorSubject.create<Int>()

        for (integer in list) {
            subject.onNext(integer)
        }

        subject.onError(exception)

        return subject
    }

    fun callsToSerialized() {
        val subject = BehaviorSubject.create<Any>().toSerialized()
    }
}
