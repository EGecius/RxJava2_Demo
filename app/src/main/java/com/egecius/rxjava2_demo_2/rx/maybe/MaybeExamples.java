package com.egecius.rxjava2_demo_2.rx.maybe;

import android.support.annotation.Nullable;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class MaybeExamples {

    public boolean isFlatMapCompletableExecuted;
    public boolean isAndThenExecuted;

    public Single<Integer> maybeToSingle(Integer param) {

        return Maybe.just(param)
                .filter(integer -> integer < 5)
                .toSingle();
    }

    /**
     * defaultIfEmpty() surprisingly returns Empty, even though it could return Single, since it
     * won't be empty now.
     */
    public Single<Integer> defaultIfEmpty(@Nullable Integer param) {

        Maybe<Integer> maybe;

        if (param == null) {
            maybe = Maybe.empty();
        } else {
            maybe = Maybe.just(param);
        }

        return maybe
                .defaultIfEmpty(-1)
                .toSingle();
    }

    /** flatMapCompletable will not get executed if origin Maybe is empty */
    public Completable maybeFlatMapCompletable(Iterable<Integer> integers) {
        return Observable.fromIterable(integers)
                .firstElement()
                .flatMapCompletable(integer -> getCompletable());
    }

    private CompletableSource getCompletable() {
        isFlatMapCompletableExecuted = true;
        return Completable.complete();
    }

    /** andThen() gets executed even if Function in flatMapCompletable() before it did not get
     * executed  */
    public Completable maybeFlatMapCompletableAndThen(Iterable<Integer> integers) {
        return Observable.fromIterable(integers)
                .firstElement()
                .flatMapCompletable(integer -> getCompletable())
                .andThen(myAndThen());
    }

    private CompletableSource myAndThen() {
        isAndThenExecuted = true;
        return Completable.complete();
    }

}
