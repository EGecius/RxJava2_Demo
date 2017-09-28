package com.egecius.rxjava2_demo_2.rx.blockable;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public class BlockableExamples {

    public Integer blockingGetForSingle() {
        return Single.just(doNetworkCall())
                .blockingGet();
    }

    private int doNetworkCall() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 1;
    }

    public Integer blockingGetForObservable() {
        return Observable.just(doNetworkCall())
                .blockingFirst();
    }

    public static <E> List<E> makeList(Iterable<E> iter) {
        List<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }

}
