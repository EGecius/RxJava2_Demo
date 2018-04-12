package com.egecius.rxjava2_demo_2.rx.subjects

import com.egecius.rxjava2_demo_2.rx.EgisException

import io.reactivex.Completable
import io.reactivex.subjects.CompletableSubject

class CompletableSubjectExamples {

    val completableSubject = CompletableSubject.create()

    fun emit1Error() {
        completableSubject.onError(EgisException())
    }

    fun emit2Errors() {
        completableSubject.onError(EgisException())
        completableSubject.onError(EgisException())
    }

    fun subscribeToAnother() {
        val completableToCompleteImmediately = Completable.error(EgisException())

        completableToCompleteImmediately.subscribe(completableSubject)
    }
}
