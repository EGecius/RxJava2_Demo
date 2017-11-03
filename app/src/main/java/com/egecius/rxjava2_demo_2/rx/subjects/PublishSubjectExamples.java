package com.egecius.rxjava2_demo_2.rx.subjects;

import java.util.List;

import io.reactivex.subjects.PublishSubject;

public class PublishSubjectExamples {

    PublishSubject<Integer> emitsEventsAndOnComplete(List<Integer> list) {

        PublishSubject<Integer> subject = PublishSubject.create();

        for (final Integer integer : list) {
            subject.onNext(integer);
        }

        subject.onComplete();

        return subject;
    }

}
