package me.weave.java8to11;

import me.weave.java8to11.domain.OnlineClass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPITest {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api", false));

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "java test", true));
        javaClasses.add(new OnlineClass(7, "java code manipulation", true));
        javaClasses.add(new OnlineClass(8, "java stream", false));

        List<List<OnlineClass>> weaveEvents = new ArrayList<>();
        weaveEvents.add(springClasses);
        weaveEvents.add(javaClasses);

        System.out.println("spring 으로 시작하는 수업");
        // TODO
        List<OnlineClass> springLecture = springClasses.stream()
                .filter(onlineClass -> onlineClass.getTitle().indexOf("spring") == 0)
                .collect(Collectors.toList());
        springLecture.forEach(onlineClass -> System.out.println(onlineClass.getTitle()));

        springClasses.stream()
                .filter(onlineClass -> onlineClass.getTitle().startsWith("spring"))
                .forEach(onlineClass -> System.out.println(onlineClass.getTitle()));

        System.out.println("closed 되지 않은 수업");
        // TODO
        weaveEvents.stream()
                .forEach(onlineClasses -> {
                    onlineClasses.stream()
                            .filter(Predicate.not(OnlineClass::isClosed))
                            .forEach(clazz -> System.out.println(clazz.getTitle()));
                });

        System.out.println("수업 이름만 모아서 스트림 만들기");
        // TODO
        weaveEvents.stream()
                .forEach(onlineClasses -> {
                    onlineClasses.stream()
                            .map(OnlineClass::getTitle)
                            .forEach(System.out::println);
                });

        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        // TODO flatMap 으로 각각의 List 안의 데이터들을 평면화 시키기
        weaveEvents.stream()
                .flatMap(Collection::stream) // 평면화
                .forEach(onlineClass -> System.out.println(onlineClass.getId()));

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        // TODO
        Stream.iterate(10, integer -> integer + 1) // 무제한 스트림
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("자바 수업 중에 Test 가 들어있는 수업이 있는지 확인");
        // TODO
        boolean result = javaClasses.stream().anyMatch(onlineClass -> onlineClass.getTitle().contains("test"));
        System.out.println(result);

        System.out.println("spring 수업 중에 제목에 spring 이 들어간 제목만 모아서 List 로 만들기");
        // TODO
        List<String> spring = springClasses.stream()
                .filter(onlineClass -> onlineClass.getTitle().contains("spring"))
                .map(OnlineClass::getTitle)
                .collect(Collectors.toList());
        spring.forEach(System.out::println);
    }

}
