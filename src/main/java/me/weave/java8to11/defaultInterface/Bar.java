package me.weave.java8to11.defaultInterface;

public interface Bar extends Foo {

    /**
     * Bar 에서 Foo 에서 제공한 디폴트 메서드를 제공하고 싶지 않은 경우
     * 추상 메서드로 선언하면된다.
     */
    void printNameUpperCase();

    /**
     * 만약, Bar 에서 Foo 에서 제공하는 똑같은 디폴트 메서드를 구현한 경우
     * 그리고 다른 클래스에서 implements Foo, Bar 로 사용하는 경우 컴파일 에러가 발생한다.
     */

}
