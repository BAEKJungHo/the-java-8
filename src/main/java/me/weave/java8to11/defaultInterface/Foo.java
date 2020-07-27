package me.weave.java8to11.defaultInterface;

public interface Foo {

    void printName();

    /**
     * 만약 여기서 printNameUpperCase() 라는 추상메서드가 필요해서 만들게되면
     * Foo 인터페이스를 구현한 구현체에서, 새롭게 추가한 추상메서드를 모두 구현해야한다.
     * 이런 문제를 해결하기 위한 것이 디폴트 메서드이다.
     */

    /**
     * @implSpec
     * 이 구현체는 getName() 으로 가져온 문자열을 대문자로 바꿔 출력한다.
     */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    }

    String getName();

}
