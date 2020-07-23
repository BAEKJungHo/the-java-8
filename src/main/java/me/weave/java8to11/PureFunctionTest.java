package me.weave.java8to11;

import me.weave.java8to11.functionalInterfaces.PureFunction;

/**
 * 순수 함수 테스트
 */
public class PureFunctionTest {

    public static void main(String[] args) {

        /**
         * 순수 함수(Pure Function)
         * ○ 사이드 이펙트가 없다. (함수 밖에 있는 값을 변경하지 않는다.)
         * ○ 상태가 없다. (함수 밖에 있는 값을 사용하지 않는다.)
         *
         * 즉, 함수 밖에 있는 값을 사용하거나 변경한다면 순수 함수가 아니기 때문에 함수형 프로그래밍이 아니다.
         */
        PureFunction pureFunction = (number) -> {
            return number + 10;
        };

        // 아래의 모든 결과는 동일하게 11이다.
        // 같은 값을 넣었을 때 같은 값이 나오지 않는 여지가 생기면 함수형 프로그래밍이 아니다.
        System.out.println(pureFunction.doIt(1));
        System.out.println(pureFunction.doIt(1));
        System.out.println(pureFunction.doIt(1));

        /**
         * 아래 예제는 순수함수가 아니다.
         * 함수 밖의 값을 사용하는 경우 (즉, 상태값에 의존하는 경우)
         */
        PureFunction pureFunction1 = new PureFunction() {
            int statusNumber = 10;
            @Override
            public int doIt(int number) {
                return number + statusNumber;
            }
        };

        // 아래 결과도 값은 같지만, 상태값에 의존하기 때문에 함수형 프로그래밍이 아니다.
        System.out.println(pureFunction1.doIt(1));
        System.out.println(pureFunction1.doIt(1));
    }

}
