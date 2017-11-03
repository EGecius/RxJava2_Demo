package com.egecius.rxjava2_demo_2.rx.subjects;

import java.util.List;

import io.reactivex.subjects.BehaviorSubject;

public class BehaviorSubjectExamples {

    BehaviorSubject<Integer> emit(List<Integer> list) {

        BehaviorSubject<Integer> subject = BehaviorSubject.create();

        for (final Integer integer : list) {
            subject.onNext(integer);
        }

        return subject;
    }

    BehaviorSubject<Integer> emitWithOnComplete(List<Integer> list) {

        BehaviorSubject<Integer> subject = BehaviorSubject.create();

        for (final Integer integer : list) {
            subject.onNext(integer);
        }

        subject.onComplete();

        return subject;
    }

    BehaviorSubject<Integer> emitWithError(List<Integer> list, Exception exception) {
        BehaviorSubject<Integer> subject = BehaviorSubject.create();

        for (final Integer integer : list) {
            subject.onNext(integer);
        }

        subject.onError(exception);

        return subject;
    }
}
