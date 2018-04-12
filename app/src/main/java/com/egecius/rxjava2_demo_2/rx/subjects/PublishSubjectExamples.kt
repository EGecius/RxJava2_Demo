package com.egecius.rxjava2_demo_2.rx.subjects

import io.reactivex.subjects.PublishSubject

class PublishSubjectExamples {

    fun emitsEventsAndOnComplete(list: List<Int>): PublishSubject<Int> {

        val subject = PublishSubject.create<Int>()

        for (integer in list) {
            subject.onNext(integer)
        }

        subject.onComplete()

        return subject
    }

}
