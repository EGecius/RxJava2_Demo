package com.egecius.rxjava2_demo_2.rx.maybe;

import android.support.annotation.Nullable;

import com.egecius.rxjava2_demo_2.rx.EgisException;

import java.util.List;
import java.util.NoSuchElementException;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.subjects.BehaviorSubject;

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

    public Maybe<String> filterEvenAndMapToString(List<Integer> list) {

        return Observable.fromIterable(list)
                .firstElement()
                .filter(this::isEven)
                .map(String::valueOf);
    }

    private boolean isEven(Integer integer) {
        return integer % 2 == 0;
    }

    /** flatMapSingle fails with {@link NoSuchElementException} when it receives no value, i.e.
     * when Maybe completes rather than calls onSuccess() */
    public Single<String> flatMapSingle(List<Integer> list) {

        return Observable.fromIterable(list)
                .firstElement()
                .flatMapSingle(integer -> Single.just(String.valueOf(integer)));
    }

    Completable maybeIgnoreElementFromSuccess() {
        return Maybe.just(5).ignoreElement();
    }

    Completable maybeIgnoreElementFromEmpty() {
        return Maybe.empty().ignoreElement();
    }

    Completable maybeIgnoreElementFromError() {
        return Maybe.error(new EgisException())
                .ignoreElement();
    }

    Completable flatMapCompletable() {
        BehaviorSubject<Boolean> behaviorSubject = getBehaviorSubjectWithDefault();
        return behaviorSubject.flatMapCompletable(aBoolean -> Completable.complete());
    }

    Completable flatMapCompletableWithEmission() {
        BehaviorSubject<Boolean> behaviorSubject = getBehaviorSubjectWithDefault();
        behaviorSubject.onNext(true);
        Completable flatMapCompletable = behaviorSubject.flatMapCompletable(
                aBoolean -> Completable.complete());

        behaviorSubject.onNext(true);

        return flatMapCompletable;
    }

    BehaviorSubject<Boolean> getBehaviorSubjectWithDefault() {
        return BehaviorSubject.createDefault(true);
    }

    Completable firstElementIgnoreElement() {
        BehaviorSubject<Boolean> behaviorSubject = getBehaviorSubjectWithDefault();
        return behaviorSubject
                .firstElement()
                .ignoreElement();
    }
}
