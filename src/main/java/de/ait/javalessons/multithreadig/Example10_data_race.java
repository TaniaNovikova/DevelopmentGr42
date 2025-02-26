package de.ait.javalessons.multithreadig;
//DATA RACE
public class Example10_data_race {
    public static void main(String[] args) {
        MyRunnableImpl1 runnable = new MyRunnableImpl1();
        //создаем 3 потока, используя этот runnable
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
       //после старта в каждом из трех потоков по
        // 3 раза вызвается метод increment
        /*output
    3 4 5 2 6 7 2 8 9
         */
        //Видим, что данные выводятся в случайном порядке (рассинхронизация),
        // при этом если мы сделаем переменную count volatile,
        // это никак не поможет, тк.volatile помогает только
        // если только один поток может изменять эту переменную,
        // а другие потоки ее только читают
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class Counter {
   volatile static int count = 0;
}

class MyRunnableImpl1 implements Runnable {
    public void increment() {
        Counter.count++;
        System.out.print(Counter.count + " ");
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            increment();
        }
    }
}
