package com.egecius.rxjava2_demo_2.rx.subjects;

import com.egecius.rxjava2_demo_2.rx.EgisException;

import io.reactivex.subjects.MaybeSubject;

public class MaybeSubjectExamples {

    private final MaybeSubject<String> mMaybeSubject = MaybeSubject.create();

    public MaybeSubject<String> getMaybeSubject() {
        return mMaybeSubject;
    }

    void emitSingleError() {
        mMaybeSubject.onError(new EgisException());
    }
}
