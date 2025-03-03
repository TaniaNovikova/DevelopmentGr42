package de.ait.javalessons.lesson_07.homework_07;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.api.DisplayName;


class MthStringUtilTest {
    private MthStringUtil mthStringUtil;
    @BeforeEach
    public void setUp() {
        mthStringUtil = new MthStringUtil();
    }

    @ParameterizedTest
    @CsvSource({
            "5, 6, 11",
            "-11, 5, -6",
            "10, -10, 0",
            "0, 0, 0"
    })
    @DisplayName("Тестирование метода сложения")
    void testAdd(int a, int b, int expected) {
        int result= mthStringUtil.add(a, b);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 0, -4})
    @DisplayName("Тестируем только чётные числа")
    void testIsEvenWithValueSource(int number) {
        boolean result = mthStringUtil.isEven(number);
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, -3, -5, 7, 19})
    @DisplayName("Тестируем только нечётные числа")
    void testIsOddWithValueSource(int number) {
        boolean result = mthStringUtil.isEven(number);
        assertFalse(result);
    }

    @ParameterizedTest
    @CsvSource({
            "10, 2, 5.0",
            "9, 2, 4.5",
            "10, 3, 3.3333333333333335",
            "5, 0, 5.0",//деление на 0
            "-6, 3, -2.0",
            "-8, -4, 2.0"
    })
    @DisplayName("Тестирование метода деления")
    void testDivide(int a, int b, double expected) {
        double result= mthStringUtil.divide(a, b);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "'Гейдельберг', 11",
            "'Heidelberg', 10",
            "'', 0",//empty string
            "'123()@/ß=<*+#!   ?', 18",
            ", 0"//null
    })
    @DisplayName("Тестирование метода получения длины строки")
    void testGetLength(String str, int expected) {
        int result=mthStringUtil.getStringsLength(str);
        assertEquals(expected,result);
    }
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
    @DisplayName("Тестирование метода containsWord")

    void testContainsWord(String text, String word, boolean expected) {
        boolean result = mthStringUtil.containsWord(text, word);
        assertEquals(expected, result);
    }

}