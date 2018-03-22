package com.egecius.rxjava2_demo_2.rx.compose;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

public class ComposeExamples {

    Observable<String> doubleAndConvertToString(int item) {
        return Observable
                .just(item)
                .compose(doubleAndConvertToString());
    }

    private ObservableTransformer<Integer, String> doubleAndConvertToString() {
        return new ObservableTransformer<Integer, String>() {
            @Override
            public ObservableSource<String> apply(Observable<Integer> upstream) {
                return upstream
                        .map(integer -> integer * 2)
                        .map(String::valueOf);
            }
        };
    }

    Observable<String> doubleAndConvertToStringRepeatedThrice(int item) {
        return Observable
                .just(item)
                .compose(doubleAndConvertToString())
                .compose(repeatThrice());
    }

    @NonNull
    private ObservableTransformer<String, String> repeatThrice() {
        return new ObservableTransformer<String, String>() {
            @Override
            public ObservableSource<String> apply(Observable<String> upstream) {
                return upstream
                        .map(string -> string + string + string);
            }
        };
    }

}
