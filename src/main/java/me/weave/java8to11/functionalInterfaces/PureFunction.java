package me.weave.java8to11.functionalInterfaces;

/**
 * 순수 함수 테스트를 위한 함수형 인터페이스
 */
@FunctionalInterface
public interface PureFunction {

    int doIt(int number);

}
