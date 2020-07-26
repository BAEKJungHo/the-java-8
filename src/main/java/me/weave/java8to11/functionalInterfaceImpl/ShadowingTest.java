package me.weave.java8to11.functionalInterfaceImpl;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * 쉐도잉 테스트
 */
public class ShadowingTest {

    public static void main(String[] args) {
        ShadowingTest shadowingTest = new ShadowingTest();
        shadowingTest.run();
    }

    private void run() {
        /**
         * 자바 8 부터는 final 을 생략해도 되는 경우가 있는데
         * 변수가 사실상 final 인 경우를 의미한다. 즉, 어디서도 변경이 안되는 경우를 의미한다.
         * 이런 final 을 effective final 이라고 한다.
         * final int baseNumber = 10;
         *
         * effective final 인 경우 로컬 클래스, 익명 클래스, 람다 모두 참조가 가능하다.
         *
         * 람다는 로컬 클래스, 익명 클래스와 달리 쉐도윙을 하지 않는다.
         * */
        int baseNumber = 10;

        // 로컬 클래스 : 로컬 클래스를 선언한 메서드의 Scope 와 로컬 클래스 내부의 Scope 는 다르다.
        class LocalClass {
            void printBaseNumber() {
                int baseNumber = 11;
                System.out.println(baseNumber); // 11 이 찍힌다. 즉, 쉐도잉
            }
        }

        // 익명 클래스 : 익명 클래스를 선언한 메서드의 Scope 와 익명 클래스 내부 메서드의 Scope 는 다르다.
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                int baseNumber = 11;
                System.out.println(baseNumber); // 11 이 찍힌다. 즉, 쉐도잉
            }
        };

        // 익명 클래스
        Consumer<Integer> integerConsumer2 = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNumber) {
                System.out.println(baseNumber); // baseNumber 는 지역변수가 아닌 accept 의 파라미터 변수인 baseNumber 를 참조한다.
            }
        };

        // 람다 : 람다는 람다를 선언한 메서드의 Scope 와 람다 바디의 Scope 가 동일하다.
        IntConsumer printInt = (i) -> {
            /**
             * int baseNumber = 11; 선언하려고 하면 아래와 같은 에러가 발생한다.
             * 쉐도잉 하지 않는다.
             * Variable 'baseNumber' is already defined in the scope
             */
            System.out.println(baseNumber);
        };
        printInt.accept(10);

        /**
         * 만약 effective final 이 아닌경우 람다 바디 내에서 참조할 수 없다.
         * 즉, 위에 effective final 을 선언해놓고 아래에서 그 값을 변경 하게되면 더 이상 final 이 아니기 때문에
         * 람다에서 사용할 수 없다.
         *
         * Variable used in lambda expression should be final or effectively final 이런 에러 문구가 발생한다.
         */
    }

}
