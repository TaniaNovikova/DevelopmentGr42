package de.ait.javalessons.lesson_07.homework_07;
public class HW07 {
    public static void main(String[] args) {
       // System.out.println(Divider.divide(10,3));
        Calculator.add(2, 3);
        //System.out.println(StringsLength.getStringsLength(null));
        System.out.println(CheckingStrings.containsWord("Mamamylaramu", "ramu"));
    }
    /*1: 1. Создать класс с методом `add(int a, int b)`.
        *  который принимает два целых числа и возвращает их сумму*/

    public static class Calculator {
        public static int add(int a, int b) {
            return a + b;
        }
    }


    //## Задание 2: Проверка чётности числа
    public static class Parity_check {
        public boolean isEven(int number) {
            return number % 2 == 0;
        }
    }

    //## Задание 3: Проверка корректности работы деления
    public class Divider {

        public static double divide(int a, int b) {
            if (b == 0) {
                System.out.println("You can't divide by zero");
                return a;
            }
            return (double) a / b;
        }
    }

    //## Задание 4: Проверка длины строки
    public class StringsLength {

        public static int getStringsLength(String str) {
            if (str == null) {
                System.out.println("It's null");
                return 0;
            }
            return str.length();
        }
    }

    //## Задание 5: Проверка, содержит ли строка определенное слово
    public class CheckingStrings {
        public static boolean containsWord(String text, String word) {
            if (text == null || word == null|| text.length() == 0 || word.length() == 0) {
                System.out.println("You can't check null or empty strings");
                return false;
            }
            return text.contains(word);
        }
    }


}
