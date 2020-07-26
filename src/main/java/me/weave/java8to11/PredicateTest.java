package me.weave.java8to11;

import java.util.function.Predicate;

/**
 * Predicate<T> 테스트
 */
public class PredicateTest {

    public static void main(String[] args) {
        Predicate<String> startsWithWeave = (s) -> s.startsWith("weave");
        startsWithWeave.test("weave");
    }

}
