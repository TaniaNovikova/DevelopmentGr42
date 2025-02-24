package de.ait.javalessons.streamapi;

import java.util.Arrays;
import java. util. List;
import java.util.ArrayList;

public class TestMethodSorted {
    public static void main(String[] args) {
        int[] array = {3, 8, 1, 5, 9, 12, 4, 21, 81, 7, 18};
        array = Arrays.stream(array).sorted().toArray();
        System.out.println(Arrays.toString(array));

        List<String> names = List.of("John", "Anna", "Alex", "Bob");

    }
}
