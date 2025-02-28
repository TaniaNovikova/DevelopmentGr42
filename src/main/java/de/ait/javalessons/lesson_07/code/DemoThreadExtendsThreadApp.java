package de.ait.javalessons.lesson_07.code;

public class DemoThreadExtendsThreadApp {
    public static void main(String[] args) {

        Thread threadOne = new DemoThreadExtendsThread("Demo Thread - 1");
        Thread threadTwo = new DemoThreadExtendsThread("Demo Thread - 2");

        threadOne.start();
        threadTwo.start();
    }
}
