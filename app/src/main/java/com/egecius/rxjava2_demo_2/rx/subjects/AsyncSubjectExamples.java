package com.egecius.rxjava2_demo_2.rx.subjects;

import java.util.List;

import io.reactivex.subjects.AsyncSubject;

public class AsyncSubjectExamples {

    static final Exception ASYNC_EXCEPTION = new Exception();

    AsyncSubject<Integer> emitEventsWithOnComplete(List<Integer> list) {

        AsyncSubject<Integer> subject = AsyncSubject.create();

        for (final Integer integer : list) {
            subject.onNext(integer);
        }

        subject.onComplete();

        return subject;
    }

    AsyncSubject<Integer> emitEventsWithError(List<Integer> list) {

        AsyncSubject<Integer> subject = AsyncSubject.create();

        for (final Integer integer : list) {
            subject.onNext(integer);
        }

        subject.onError(ASYNC_EXCEPTION);

        return subject;
    }

    AsyncSubject<Integer> emitEventsWithoutTermination(List<Integer> list) {
        AsyncSubject<Integer> subject = AsyncSubject.create();

        for (final Integer integer : list) {
            subject.onNext(integer);
        }

        // no terminate event

        return subject;
    }
}
