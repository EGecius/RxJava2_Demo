package com.egecius.rxjava2_demo_2.rx.assertions;

import io.reactivex.Observable;

public class AssertionsExamples {

    private static final EgisException EGIS_EXCEPTION = new EgisException("egis");

    public EgisException getException() {
        return EGIS_EXCEPTION;
    }

    Observable<Integer> getJust() {
        return Observable.just(1);
    }

    Observable<Integer> emitThreeIntegersAndFail() {
        return Observable.create(emitter -> {
            for (int i = 0; i < 3; i++) {
                emitter.onNext(i);
            }

            emitter.onError(EGIS_EXCEPTION);
        });
    }
}
