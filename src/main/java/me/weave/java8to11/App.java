package me.weave.java8to11;

import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;

public class App {

    public static void main(String[] args) {
        DefaultInterfaceTest defaultInterfaceTest = new DefaultInterfaceTest("Foo");
        defaultInterfaceTest.printName();
        defaultInterfaceTest.printNameUpperCase();

        List<String> name = List.of("a", "b", "c", "d", "e");

        // == name.forEach(System.out::println);
        name.forEach((s) -> {
            System.out.println(s);
        });

        System.out.println("--------------");
        Spliterator<String> stringSpliterator = name.spliterator();
        // 쪼개는 기능
        Spliterator<String> spliterator = stringSpliterator.trySplit();
        while(stringSpliterator.tryAdvance(System.out::println));
        System.out.println("--------------");
        while(spliterator.tryAdvance(System.out::println));

        // a 로 시작하는거 제외
        name.removeIf(s -> s.startsWith("a"));

        /**
         * removeIf 나 forEach 등 매개변수로 함수형 인터페이스를 받는 곳에는
         * 메서드 참조나 람다식을 매개변수로 넘길 수 있다.
         */

        /**
         * thenComparing 을 써서 추가 정렬 조건을 줄 수 있다.
         */
        Comparator<String> comparator = String::compareToIgnoreCase;
        name.sort(comparator.reversed());

    }
}
