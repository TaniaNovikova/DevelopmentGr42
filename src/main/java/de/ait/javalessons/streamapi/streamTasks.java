package de.ait.javalessons.streamapi;

import java.util.List;
import java.util.stream.Collectors;

public class streamTasks {
    //1) Из списка чисел оставить только четные
    public static List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    //2) Преобразовать список строк в их длины.
    public static List<String> words = List.of("Java", "Stream", "API", "Practice");

    //3) Преобразовать список списков чисел в один список.
    public static List<List<Integer>> listOfLists = List.of(
            List.of(1, 2, 3),
            List.of(4, 5),
            List.of(6, 7, 8, 9)
    );

    //4)Из списка чисел оставить только уникальные.
    public static List<Integer> numbersNotUnique = List.of(1, 2, 2, 3, 4, 4, 5, 5, 6);

    //5) Отсортировать список имен по алфавиту и по длине.
    public static List<String> names = List.of("John", "Anna", "Alex", "Bob");

    public static void main(String[] args) {
//        List<Integer> evenNumbers = numbers.stream()
//                .filter(n -> n % 2 == 0)
//                .collect(Collectors.toList());
//        System.out.println(evenNumbers);

//        List<Integer> lengsOfWords=words.stream()
//                .map(w->w.length())
//                .collect(Collectors.toList());
//        System.out.println(lengsOfWords);

//        List<Integer> numb = listOfLists.stream()
//                .flatMap(list -> list.stream())
//                .peek(System.out::println)
//                .collect(Collectors.toList());
//        System.out.println(numb);

//        List<Integer> numbersUnique = numbersNotUnique.stream()
//                .distinct().collect(Collectors.toList());
//        System.out.println(numbersUnique);

        //List<String> namesInAlphabeticalOrder = names.stream()
        names = names.stream()
                .sorted().toList();
       // System.out.println(namesInAlphabeticalOrder);
        System.out.println(names);

    }

}
