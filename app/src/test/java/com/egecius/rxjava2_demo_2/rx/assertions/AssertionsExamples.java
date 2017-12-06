package com.egecius.rxjava2_demo_2.rx.assertions;

import io.reactivex.Observable;

public class AssertionsExamples {

    private static final String EXCEPTION_MESSAGE = "egis";
    private static final EgisException EGIS_EXCEPTION = new EgisException(EXCEPTION_MESSAGE);

    public EgisException getException() {
        return EGIS_EXCEPTION;
    }

    public String getExceptionMessage() {
        return EXCEPTION_MESSAGE;
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

    Observable<Integer> emitWithoutCompletion(int item1, int item2) {
        return Observable.create(e -> {
            e.onNext(item1);
            e.onNext(item2);
        });
    }
}
