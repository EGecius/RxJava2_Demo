package com.egecius.rxjava2_demo_2.rx.subjects

import com.egecius.rxjava2_demo_2.rx.EgisException

import io.reactivex.subjects.MaybeSubject

class MaybeSubjectExamples {

    val maybeSubject = MaybeSubject.create<String>()

    fun emitSingleError() {
        maybeSubject.onError(EgisException())
    }

    fun emit2Errors() {
        maybeSubject.onError(EgisException())
        maybeSubject.onError(EgisException())
    }
}
