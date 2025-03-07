package de.ait.javalessons.lesson_07.homework_07;
@lombok.extern.slf4j.Slf4j
public class MthStringUtil {

    public int add(int a, int b) {
        return a + b;
    }

    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static double divide(int a, int b) {
        if (b == 0) {
            System.out.println("You can't divide by zero");
            return a;
        }
        return (double) a / b;
    }

    public int getStringsLength(String str) {
        if (str == null) {
            System.out.println("It's null");
            return 0;
        }
        return str.length();
    }

    public boolean containsWord(String text, String word) {
        if (text == null || word == null|| text.length() == 0 || word.length() == 0) {
            System.out.println("You can't check null or empty strings");
            return false;
        }
        return text.contains(word);
    }

}
