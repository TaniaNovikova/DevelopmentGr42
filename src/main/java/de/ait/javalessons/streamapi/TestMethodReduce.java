package de.ait.javalessons.streamapi;
import java.util.List;
import java.util.ArrayList;

public class TestMethodReduce {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(8);
        list.add(2);
        list.add(4);
        list.add(3);

        int result = list.stream().reduce((accumulator, element) -> accumulator * element).get();
        System.out.println(result);


        //5, 8, 2, 4, 3
        //accumulator=5 40 80 320 960
        //element=    8 2  4   3
        //Если мы уберем get(), то метод сразу покраснеет.
        // Это происходит потому, что reduce возвращает
        //optional. Что это такое - поговорим позже.
        // Но надо знать, что для того, чтобы получить ответ
        // от optional, надо использовать метод get().

        // Объект типа optional делает wrap, то есть оборачивает
        // собой объект в данном случае Integer
        //или, говоря иными словами, является контейнером для возврацаемого объекта
        //optional может содержать null и not null значения.

        //Для того, чтобы не использовать "на ура" метод get(),
        // потому что он может вернуть null, мы можем
        //проверить значение на присутствие методом isPresent

        //создадим новый пустой ArrayList list100,
        // в который мы не добавим никаких элементов.
        // Т.о. он вернет null

  //      List<Integer> list100 = new ArrayList<>();
//        int result100 = list100.stream()
//        .reduce((accumulator, element) -> accumulator * element).get();
//        System.out.println(result100);

        //получили в терминале NoSuchElementException:
        /*Exception in thread "main" java.util.NoSuchElementException: No value present
	at java.base/java.util.Optional.get(Optional.java:143)
	at abschnitt_7_Stream.Test4.main(Test4.java:35)*/

//     Чтобы этого не происходило, нужно убрать метод get()
//     и вернуть метод в переменную
//     типа Optional, а затем сделать проверку


//        Optional<Integer> o = list100.stream()
//        .reduce((accumulator, element) -> accumulator * element);
//        if (o.isPresent()) {
//            System.out.println(o.get());
//        } else {
//            System.out.println("Not present");
//        }
   /* ************Второй вариант метода reduce: с начальным значением аккумулятора******* */

        int result2 = list.stream()
                .reduce(1,(accumulator, element) -> accumulator * element);
        //5, 8, 2, 4, 3
        //accumulator=1 5 40 80 320 960
        //element=    5 8 2  4   3

         /*В этом случае у нас нет шанса получить null, т.к. всегда будет непустой элемент - аккумулятор,
         * поэтому метод get не нужен: reduce сразу вернет нам Integer*/
        System.out.println(result2);

/* ******************вернем из List<String> конкатинацию всех его элементов**************** */

        List<String> list3 = new ArrayList<>();
        list3.add("privet");
        list3.add("kak dela?");
        list3.add("OK");
        list3.add("poka");

        String result3 =list3.stream().reduce((a,e)->a+" "+e).get();
        System.out.println(result3);
    }

}
