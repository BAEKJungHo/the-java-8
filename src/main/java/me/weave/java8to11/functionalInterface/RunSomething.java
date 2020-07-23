package me.weave.java8to11.functionalInterface;

/**
 * @FunctionalInterface 와 추상 메서드가 하나이면 함수형 인터페이스이다.
 */
@FunctionalInterface
public interface RunSomething {

    // 추상 메서드
    void doIt();

    // static method
    static void printName() {
        System.out.println("weave");
    }

    // default method
    default void printAge() {
        System.out.println("27");
    }

}
