package org.example.twosums;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class TwoSumsTest {
    @MethodSource
    @ParameterizedTest
    public void checkThatMethodReturnsCorrectIndeces(int[] initArray, int[] expectedArray, int target) {
        TwoSums twoSums = new TwoSums();
        assertThat(expectedArray).contains(twoSums.calculate(initArray, target));
    }

    public static Stream<Arguments> checkThatMethodReturnsCorrectIndeces() {
        return Stream.of(
                Arguments.of(new int[]{1,2,3,4,5}, new int[]{1,3}, 6));
    }
}
