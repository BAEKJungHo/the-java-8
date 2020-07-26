package me.weave.java8to11;

import java.util.function.Supplier;

/**
 * Supplier<T> 테스트
 */
public class SupplierTest {

    public static void main(String[] args) {
        Supplier<Integer> get10 = () -> 10;
        System.out.println(get10.get());
    }

}
