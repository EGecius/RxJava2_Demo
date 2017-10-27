package com.egecius.rxjava2_demo_2.rx.zip;

import static java.util.Arrays.asList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.observers.TestObserver;

@RunWith(MockitoJUnitRunner.class)
public class ZipExamplesTest {

    private List<Integer> integers = asList(1, 2, 3, 4);
    private List<String> strings = asList("one", "two", "three");

    private ZipExamples mSut;

    @Before
    public void setUp() {
        mSut = new ZipExamples();
    }

    @Test
    public void zipWith() {
        TestObserver<String> testObserver = mSut.zipWith(integers, strings).test();

        testObserver
                .assertComplete()
                .assertNoErrors()
                .assertValues("1_one", "2_two", "3_three");
    }

    @Test
    public void zip() {
        TestObserver<String> testObserver = mSut.zip(integers, strings).test();

        testObserver
                .assertComplete()
                .assertNoErrors()
                .assertValues("1_one", "2_two", "3_three");
    }

}