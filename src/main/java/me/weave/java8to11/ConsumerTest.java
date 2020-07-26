package me.weave.java8to11;

import java.util.function.Consumer;

/**
 * Consumer<T> 테스트
 */
public class ConsumerTest {

    public static void main(String[] args) {
        Consumer<Integer> printT = (i) -> System.out.println(i);
        printT.accept(10); // 10 출력
    }

}
