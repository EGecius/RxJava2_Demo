package com.egecius.rxjava2_demo_2.rx.completable;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;

public class CompletableExamples {

    public static final String FIRST_COMPLETABLE = "first_completable";
    public static final String SECOND_COMPLETABLE = "second_completable";
    public static final String THIRD_COMPLETABLE = "third_completable";

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

}
