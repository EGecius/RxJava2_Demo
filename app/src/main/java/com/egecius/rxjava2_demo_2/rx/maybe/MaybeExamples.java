package com.egecius.rxjava2_demo_2.rx.maybe;

import android.support.annotation.Nullable;

import io.reactivex.Maybe;
import io.reactivex.Single;

public class MaybeExamples {

	public Single<Integer> maybeToSingle(Integer param) {

		return Maybe.just(param)
				.filter(integer -> integer < 5)
				.toSingle();
	}

	/** defaultIfEmpty() surprisingly returns Empty, even though it could return Single, since it
     * won't be empty now. */
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
}
