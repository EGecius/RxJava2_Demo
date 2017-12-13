package com.egecius.rxjava2_demo_2;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

public class MyRxDelegate {
    private CallbackFramework callbackFramework = new CallbackFramework();

    public MyRxDelegate() {
    }

    void emitStreamWithFailure() {
        createFailingStream()
                .doOnNext(this::printDoOnNext)
                .doOnComplete(this::printDoOnComplete)
                .doOnError(this::printDoOnError)
                .subscribe(this::handleOnNext, this::handleError);
    }

    Observable<Integer> createFailingStream() {
        return Observable.create(e -> {
            for (int i = 0; i < 3; i++) {
                e.onNext(i);
            }
            e.onError(new Exception("Egis"));
        });
    }

    private void handleError(Throwable throwable) {
        Log.w("Eg:MainActivity:32", "handleError throwable " + throwable);
    }

    private void handleOnNext(Integer integer) {
        Log.i("Eg:MainActivity:36", "handleOnNext integer " + integer);
    }

    private int printDoOnError(Throwable throwable) {
        return Log.w("Eg:MainActivity:32", "printDoOnError throwable " + throwable);
    }

    private int printDoOnComplete() {
        return Log.i("Eg:MainActivity:35", "doOnComplete ");
    }

    private void printDoOnNext(Integer integer) {
        Log.i("Eg:MainActivity:34", "printDoOnNext integer " + integer);
    }

    /**
     * This will crash the app because rxJava 2 not only prints the stracktrace but also
     * forwards uncaught exception to current thread's error handler. On Android this is very
     * strict and crashes the app
     */
    private void emitErrorWithoutOnErrorHandling() {
        Observable.error(new RuntimeException())
                .subscribe();
    }

    /** This demonstrates that only the first call subscribeOn() has any effect */
    void useMultipleSubscribeOn() {
        Observable
                .create((ObservableOnSubscribe<Integer>) e -> {
                    printThread("Observable.create");
                    e.onNext(1);
                    e.onComplete();
                })
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        printThread("donOnNext after Schedulers.io()");
                    }
                })
                .subscribeOn(Schedulers.computation())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        printThread("donOnNext after Schedulers.computation()");
                    }
                })
                .subscribe();
    }

    private void printThread(String methodName) {
        String threadName = Thread.currentThread().getName();
        Log.i("Eg:MyRxDelegate:80", methodName + " thread: " + threadName);
    }

    /** This demonstrates that subscribeOn works with BehaviorSubject */
    void subscribeOnSubject() {
        BehaviorSubject.createDefault(1)
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        printThread("donOnNext after BehaviorSubject.createDefault");
                    }
                })
                .subscribe();
    }

    /**
     * This shows that subscribeOn() does not guarantee that this subscriber will be used for
     * subscribe operations. Thread can be changed during execution, which makes thread control
     * difficult
     */
    void changeThreadsWithinSingleCreate() {
        printThread("start of changeThreadsWithinSingleCreate()");

        Single
                .create(new SingleOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(SingleEmitter<Integer> emitter) throws Exception {

                        printThread("start of subscribe()");

                        callbackFramework.returnOnBackgroundThread(new CallbackFramework.Callback() {
                            @Override
                            public void onSuccess() {
                                printThread("onSuccess");

                                emitter.onSuccess(1);
                            }
                        });
                    }
                })
                // from now one, once thread has been changed to 'main' it will stay 'main' after
                // every subsequent call
                .doOnEvent(new BiConsumer<Integer, Throwable>() {
                    @Override
                    public void accept(Integer integer, Throwable throwable) throws Exception {
                        printThread("after Single.create");
                    }
                })
                .subscribeOn(Schedulers.io())
                .doOnEvent(new BiConsumer<Integer, Throwable>() {
                    @Override
                    public void accept(Integer integer, Throwable throwable) throws Exception {
                        printThread("after subscribeOn");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEvent(new BiConsumer<Integer, Throwable>() {
                    @Override
                    public void accept(Integer integer, Throwable throwable) throws Exception {
                        printThread("after observeOn");
                    }
                })
                .subscribe();
    }

    void applyMultipleObservOn() {
        printThread("start of applyMultipleObservOn()");

        Single
                .create(new SingleOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(SingleEmitter<Integer> emitter) throws Exception {

                        printThread("start of subscribe()");

                        callbackFramework.returnOnBackgroundThread(new CallbackFramework.Callback() {
                            @Override
                            public void onSuccess() {
                                printThread("onSuccess");

                                emitter.onSuccess(1);
                            }
                        });
                    }
                })
                .doOnEvent(new BiConsumer<Integer, Throwable>() {
                    @Override
                    public void accept(Integer integer, Throwable throwable) throws Exception {
                        printThread("after Single.create with thread change");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEvent(new BiConsumer<Integer, Throwable>() {
                    @Override
                    public void accept(Integer integer, Throwable throwable) throws Exception {
                        printThread("after observeOn(AndroidSchedulers.mainThread()");
                    }
                })
                .flatMap(new Function<Integer, SingleSource<Integer>>() {
                    @Override
                    public SingleSource<Integer> apply(Integer integer) throws Exception {
                        return Single.create(new SingleOnSubscribe<Integer>() {
                            @Override
                            public void subscribe(SingleEmitter<Integer> emitter) throws Exception {
                                callbackFramework.returnOnBackgroundThread(new CallbackFramework.Callback() {
                                    @Override
                                    public void onSuccess() {
                                        emitter.onSuccess(1);
                                    }
                                });
                            }
                        });
                    }
                })
                .doOnEvent(new BiConsumer<Integer, Throwable>() {
                    @Override
                    public void accept(Integer integer, Throwable throwable) throws Exception {
                        printThread("after changing thread to background");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEvent(new BiConsumer<Integer, Throwable>() {
                    @Override
                    public void accept(Integer integer, Throwable throwable) throws Exception {
                        printThread("after observeOn(AndroidSchedulers.mainThread()");
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}