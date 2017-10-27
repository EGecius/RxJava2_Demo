package com.egecius.rxjava2_demo_2.rx.retry;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

// TODO: 27/10/2017 to be fixed 
public class RetryExamples {

    int subscribeCalled;

    Single<Integer> retryWhen_retries5Tmes() {

        return Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<Integer> emitter) throws Exception {
                subscribeCalled++;
                emitter.onSuccess(0);
            }
        })
                .retryWhen(new Function<Flowable<Throwable>, Publisher<?>>() {
                    @Override
                    public Publisher<?> apply(@NonNull Flowable<Throwable> throwableFlowable)
                            throws Exception {
                        return Flowable.range(1, 5);
                    }
                });
    }
}
