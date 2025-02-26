package de.ait.javalessons.multithreadig;

public class Example3_ways_to_create_a_thread {
    public static void main(String[] args) {
        //другой синтаксис создания потока: мы используем тут класс Thread
        Thread thread1 = new Thread(new MyThread3());
        Thread thread2 = new Thread(new MyThread4());
        //запускаем потоки той же самой командой start
        thread1.start();
        thread2.start();
    }

}

//второй способ создания потока - использование интерфейса Runnable
class MyThread3 implements Runnable {
    //метод run нужно оверрайдить и в случае,
    // когда мы имплементируем интерфейс
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}

class MyThread4 implements Runnable {
    @Override
    public void run() {
        for (int i = 1000; i > 0; i--) {
            System.out.println(i);
        }
    }
}


