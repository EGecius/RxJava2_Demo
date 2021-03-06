package com.egecius.rxjava2_demo_2.rx.retry;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

@SuppressWarnings("SameParameterValue")
public class RetryExamples {

    private int subscribeCalled;

    public int getSubscribeCalled() {
        return subscribeCalled;
    }

    /** retryWhen resubscribes to original source when Publisher (returned by Function) signals a
     *  value. Uses Flowable.range */
    Single<Integer> retryWhen(int retryCount) {

        return Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<Integer> emitter) throws Exception {
                subscribeCalled++;
                emitter.onError(new RecoverableException());
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
                        return Flowable.range(1, retryCount);
                    }
                });
    }

    /** retryWhen resubscribes to original source when Publisher (returned by Function) signals a
     *  value. Uses Flowable.range */
    Single<Integer> retryWhenWithDelay(int retryCount) {

        return Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<Integer> emitter) throws Exception {
                subscribeCalled++;
                emitter.onError(new RecoverableException());
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
                        return Flowable.range(1, retryCount)
                                .delay(10, TimeUnit.MILLISECONDS);
                    }
                });
    }

    /** If retryWhen Flowable does not complete, the whole stream never terminates  */
    Single<Integer> retryWithoutOnComplete(int retryCount) {

        return Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<Integer> emitter) throws Exception {
                subscribeCalled++;
                emitter.onError(new RecoverableException());
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
                        return Flowable.create(new FlowableOnSubscribe<Long>() {
                            @Override
                            public void subscribe(FlowableEmitter<Long> emitter) throws Exception {
                                for (int i = 0; i < retryCount; i++) {
                                    emitter.onNext(0L);
                                }
                                //intentionally not calling onComplete
                            }
                        }, BackpressureStrategy.BUFFER);
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

    Observable<String> retryWhenWithZipTimes(int retryCount) {
        Observable<String> stringObservable = Observable.create(
                new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter)
                            throws Exception {

                        if (subscribeCalled < 10) {
                            emitter.onError(new RecoverableException());
                        } else {
                            emitter.onError(new IrrecoverableException());
                        }

                        subscribeCalled++;
                    }
                });

        return stringObservable.retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(@NonNull Observable<Throwable> throwableObservable)
                    throws Exception {
                return buildRetryWhenObservable(throwableObservable, retryCount);
            }
        });
    }

    private ObservableSource<?> buildRetryWhenObservable(Observable<Throwable> throwableObservable,
            int retryCount) {
        return throwableObservable.zipWith(Observable.range(0, retryCount + 1),
                new BiFunction<Throwable, Integer, Throwable>() {
                    @Override
                    public Throwable apply(@NonNull Throwable throwable, @NonNull Integer integer)
                            throws Exception {
                        // simply re-emit throwable - here we are limiting to 5 times set in
                        // Observable.range
                        return throwable;
                    }
                })
                .flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Throwable throwable)
                            throws Exception {
                        if (throwable instanceof RecoverableException) {
                            //signal value rather than fail, since exception is recoverable
                            return Observable.just(0);
                        } else if (throwable instanceof IrrecoverableException) {
                            Observable.error(throwable);
                        }
                        throw new IllegalStateException();
                    }
                });

    }


    private static class RecoverableException extends Throwable {
    }

    static class IrrecoverableException extends Throwable {
    }
}
