package com.egecius.rxjava2_demo_2.rx.map;

import android.annotation.SuppressLint;

import java.util.List;

import io.reactivex.Observable;

public class MapExamples2 {

    /** Takes list of string and concatenates it into one long string */
    public Observable<String> concatenate(final List<String> list) {
        Observable<List<String>> justObservable = Observable.just(list);
        return justObservable.map(this::concatenateList);
    }


    @SuppressLint("NewApi")
    private String concatenateList(final List<String> list) {

        StringBuilder stringBuilder = new StringBuilder();
        list.forEach(stringBuilder::append);

        return stringBuilder.toString();
    }
}
