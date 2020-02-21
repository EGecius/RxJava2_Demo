package com.egecius.rxjava2_demo_2.rx.combining

import io.reactivex.Observable
import io.reactivex.functions.Function3
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CombiningExampleTest {


    @Test
    fun `combineLatest does not emit duplicate items`() {

        val observableDigits = Observable.just(1, 1, 2)
        val observableColours = Observable.just("red", "green", "black")
        val observableSpecies = Observable.just("dog", "dog", "cat")

        val observableCombineLatest = Observable.combineLatest(observableDigits, observableColours, observableSpecies, Function3<Int, String,
                String, String> { digit,
                                  specie,
                                  colour ->
            "$digit $specie $colour"

        })
        val result: MutableList<String> = observableCombineLatest.test().values()

        assertThat(result.size).isEqualTo(3)
        assertThat(result[0]).isEqualTo("2 black dog")
        assertThat(result[1]).isEqualTo("2 black dog")
        assertThat(result[2]).isEqualTo("2 black cat")
    }
}