package me.weave.java8to11;

import me.weave.java8to11.defaultInterface.Foo;

public class DefaultInterfaceTest implements Foo {

    String name;

    public DefaultInterfaceTest(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
