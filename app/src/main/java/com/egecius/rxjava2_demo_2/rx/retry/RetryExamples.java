package com.egecius.rxjava2_demo_2.rx.retry;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class RetryExamples {

    private int subscribeCalled;

    public int getSubscribeCalled() {
        return subscribeCalled;
    }

    /** retryWhen resubscribes to original source when Publisher (returned by Function) signals a
     *  value   */
    Single<Integer> retryWhenTimes(int retryCount) {

        return Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<Integer> emitter) throws Exception {
                subscribeCalled++;
                emitter.onError(new Exception());
            }
        })
                .retryWhen(new Function<Flowable<Throwable>, Publisher<?>>() {
                    @Override
                    public Publisher<?> apply(@NonNull Flowable<Throwable> throwableFlowable)
                            throws Exception {
                        // throwableFlowable is a stream of exceptions, which we can use for
                        // further logic of
                        // 1) how many times we should retry
                        // 2) whether to add delay
                        return Flowable.range(1, retryCount + 1);
                    }
                });
    }

    Single<Integer> retryTimes(int retryCount) {
        return Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<Integer> emitter) throws Exception {
                subscribeCalled++;
                emitter.onError(new Exception());
            }
        }).retry(retryCount);
    }
}
