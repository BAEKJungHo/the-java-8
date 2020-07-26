package me.weave.java8to11;

import me.weave.java8to11.functionalInterfaceImpl.Plus10;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Function<T, R> 함수형 인터페이스 테스트
 */
public class FunctionInterfaceTest {

    public static void main(String[] args) {

        // Function<T, R> 을 구현한 클래스를 만들어 사용
        Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(10));

        // 익명 클래스로 사용
        Function<Integer, Integer> plus10_2 = (i) -> i + 10;
        Function<Integer, Integer> multiply8 = (i) -> i * 8;

        // 10 을 더하기 전에 곱하기를 하겠다.
        // multiply8 의 동작을 먼저하고 그 결과값을 plus10_2 의 파라미터로 사용
        Function<Integer, Integer> multiplyAndPlus = plus10_2.compose(multiply8);
        System.out.println(multiplyAndPlus.apply(2));

        // 10을 먼저 더하고 곱하기를 하겠다.
        Function<Integer, Integer> plusAndThenMultiply = plus10_2.andThen(multiply8);
        System.out.println(plusAndThenMultiply.apply(2));


        /**
         * UnaryOperator 는 Function 인터페이스를 사용할 때 입력과 결과값의 타입이 같은경우 사용할 수 있다.
         */
        //  Function<Integer, Integer> plus10_2 = (i) -> i + 10;
        UnaryOperator<Integer> plus8 = (i) -> i + 8;

        /**
         * BinaryOperator 는 BiFunction(두개의 매개변수를 받아 결과를 리턴) 의 매개변수와 결과값이 모두 같은 경우 사용할 수 있다.
         */

    }

}
