package me.weave.java8to11;

import me.weave.java8to11.domain.Greeting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MethodReferenceTest {

    public static void main(String[] args) {
        //  UnaryOperator<String> hi = (s) -> "hi" + s;
        UnaryOperator<String> hi = Greeting::hi; // static 메서드 참조

        Greeting greeting = new Greeting();
        UnaryOperator<String> hi2 = greeting::hello; // 인스턴스 메서드 참조

        Supplier<Greeting> newGreeting = Greeting::new; // 생성자 참조
        Greeting greeting1 = newGreeting.get(); // 객체 생성

        Function<String, Greeting> greetingFunction = Greeting::new; // 문자열을 받는 생성자 참조
        Greeting greeting2 = greetingFunction.apply("weave");

        String[] names = {"weave", "john", "cache"};
        Arrays.sort(names, new Comparator<String>() { // Comparator 가 자바 8 부터는 FunctionalInterface 로 바뀌었다.
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });

        Arrays.sort(names, String::compareToIgnoreCase);
    }

}
