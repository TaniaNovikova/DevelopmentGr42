package de.ait.javalessons.lesson_07.homework_07;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;

class HW07Test {

    // 1. Тестирование метода сложения
    //Использовать аннотацию `@ParameterizedTest` и `@CsvSource`
    @ParameterizedTest
    @CsvSource({
            "5, 6, 11",
            "-11, 5, -6",
            "10, -10, 0",
            "0, 0, 0"
    })
    void testAdd(int a, int b, int expected) {
        HW07.Calculator calculator = new HW07.Calculator();
        assertEquals(expected, calculator.add(a, b));
    }

    //2. Проверка чётности числа.
    // Использовать аннотацию `@ParameterizedTest` и `@ValueSource`

    @ParameterizedTest
    @ValueSource(ints = {2, 0, -4})
        // Тестируем только чётные числа
    void testIsEvenWithValueSource(int number) {
        HW07.Parity_check checker = new HW07.Parity_check();
        assertTrue(checker.isEven(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, -3, -5, 7, 19})
        // Тестируем только нечётные числа
    void testIsOddWithValueSource(int number) {
        HW07.Parity_check checker = new HW07.Parity_check();
        assertFalse(checker.isEven(number));
    }


    // 3. Тестирование метода деления
    //Использовать `@CsvSource` для проверки различных наборов входных данных.
    @ParameterizedTest
    @CsvSource({
            "10, 2, 5.0",
            "9, 2, 4.5",
            "10, 3, 3.3333333333333335",
            "5, 0, 5.0",//деление на 0
            "-6, 3, -2.0",
            "-8, -4, 2.0"
    })
    void testDivide(int a, int b, double expected) {
        HW07.Divider divider = new HW07().new Divider();
        assertEquals(expected, divider.divide(a, b));
    }


    // 4.Тестирование метода получения длины строки
    //Использовать `@CsvSource` для передачи строк разной длины.
    //Проверить корректность вычисления длины строки,
    // включая пустую строку и `null`.
    @ParameterizedTest
    @CsvSource({
            "'Гейдельберг', 11",
            "'Heidelberg', 10",
            "'', 0",//empty string
            "'123()@/ß=<*+#!   ?', 18",
            ", 0"//null
    })
    void testGetLength(String str, int expected) {
        HW07.StringsLength stringsLength = new HW07().new StringsLength();
        assertEquals(expected, stringsLength.getStringsLength(str));
    }

    // 5.Тестирование метода containsWord
    //Использовать `@CsvSource` для передачи различных комбинаций `text` и `word`.
    //Проверить, что метод корректно определяет наличие слова в строке.
    @ParameterizedTest
    @CsvSource({
            "'Мама мыла раму', 'мыла', true",
            "'Мама мыла раму', 'мыло', false",
            "'Mamamylaramu', 'ramu', true",
            "'Mamamylaramu', 'mura', false",
            ", 'lara', false",//null
            "'', 'lara', false",//empty string
            "'lara', , false",//null
            "'lara', '', false"//empty string
    })
    void testContainsWord(String text, String word, boolean expected) {
        HW07.CheckingStrings checker = new HW07().new CheckingStrings();
        assertEquals(expected, checker.containsWord(text, word));
    }
}

