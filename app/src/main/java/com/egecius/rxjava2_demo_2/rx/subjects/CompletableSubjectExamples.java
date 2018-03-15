package com.egecius.rxjava2_demo_2.rx.subjects;

import com.egecius.rxjava2_demo_2.rx.EgisException;

import io.reactivex.Completable;
import io.reactivex.subjects.CompletableSubject;

public class CompletableSubjectExamples {

    private final CompletableSubject mCompletableSubject = CompletableSubject.create();

    public CompletableSubject getCompletableSubject() {
        return mCompletableSubject;
    }

    void emit1Error() {
        mCompletableSubject.onError(new EgisException());
    }

    void emit2Errors() {
        mCompletableSubject.onError(new EgisException());
        mCompletableSubject.onError(new EgisException());
    }

    void subscribeToAnother() {
        Completable completableToCompleteImmediately = Completable.error(new EgisException());

        completableToCompleteImmediately.subscribe(mCompletableSubject);
    }
}
