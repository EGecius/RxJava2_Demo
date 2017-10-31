package com.egecius.rxjava2_demo_2.rx.completable;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Maybe;
import io.reactivex.functions.Action;

public class CompletableExamples {

    public static final Exception ERROR_1 = new Exception("ERROR_1");
    public static final Throwable ERROR_2 = new Exception("ERROR_2");

    enum Path {
        A, B
    }

    public static final String FIRST_COMPLETABLE = "first_completable";
    public static final String SECOND_COMPLETABLE = "second_completable";
    public static final String THIRD_COMPLETABLE = "third_completable";
    public static final String DO_ON_COMPLETE = "doOnComplete";
    public static final String PATH_A_COMPLETABLE = "PATH_A_COMPLETABLE";
    public static final String PATH_B_COMPLETABLE = "PATH_B_COMPLETABLE";

    List<String> list = new ArrayList<>();

    Completable andThen() {
        return firstCompletable()
                .andThen(secondCompletable())
                .andThen(thirdCompletable());
    }

    private Completable firstCompletable() {
        list.add(FIRST_COMPLETABLE);
        return Completable.complete();
    }

    private Completable secondCompletable() {
        list.add(SECOND_COMPLETABLE);
        return Completable.complete();
    }

    private CompletableSource thirdCompletable() {
        list.add(THIRD_COMPLETABLE);
        return Completable.complete();
    }

    /** doOnComplete is executed at the end no matter where you place it in the chain */
    Completable doOnCompleteAndThen() {
        return firstCompletable()
                .doOnComplete(myDoOnComplete())
                .andThen(secondCompletable())
                .andThen(thirdCompletable());
    }

    private Action myDoOnComplete() {
        return () -> list.add(DO_ON_COMPLETE);
    }


    Completable divergingPaths(Path path) {
        return firstCompletable()
                .andThen(secondCompletable())
                .andThen(executePath(path));
    }

    private CompletableSource executePath(Path path) {
        if (path == Path.A) {
            return pathACompletable();
        }
        return pathBCompletable();
    }

    private Completable pathACompletable() {
        list.add(PATH_A_COMPLETABLE);
        return Completable.complete();
    }

    private Completable pathBCompletable() {
        list.add(PATH_B_COMPLETABLE);
        return Completable.complete();
    }

    Completable divergingPathWithMaybeA() {
        return firstCompletable()
                .andThen(secondCompletable())
                .andThen(getMaybeA())
                .flatMapCompletable(this::executePath);
    }

    private Maybe<Path> getMaybeA() {
        return Maybe.just(Path.A);
    }

    Completable divergingPathWithMaybeB() {
        return firstCompletable()
                .andThen(secondCompletable())
                .andThen(getMaybeB())
                .flatMapCompletable(this::executePath);
    }

    private Maybe<Path> getMaybeB() {
        return Maybe.just(Path.B);
    }

    Completable andThenWithErrors() {
        return firstCompletable()
                .andThen(Completable.error(ERROR_1))
                .andThen(secondCompletable())
                .andThen(Completable.error(ERROR_2))
                .andThen(thirdCompletable());
    }

}
