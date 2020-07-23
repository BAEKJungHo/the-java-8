package me.weave.java8to11;

import me.weave.java8to11.functionalInterfaces.PureFunction;
import me.weave.java8to11.functionalInterfaces.RunSomething;

public class LambdaAndAnonymousFunctionTest {

    public static void main(String[] args) {
        // 자바 8 이전 : 익명함수
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Hello");
            }
        };

        // 자바 8 이후 : 람다
        // 중괄호 { } 사용하는 경우는 바디 내부 코드가 2줄 이상인 경우
        // 내부 코드가 1줄이면 생략해도 상관없다.
        RunSomething runSomething1 = () -> System.out.println("Hello");
        runSomething1.doIt();
    }

}
