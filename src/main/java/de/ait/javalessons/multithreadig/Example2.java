package de.ait.javalessons.multithreadig;

public class Example2 {
    public static void main(String[] args) {
        MyThread1 myThread1=new MyThread1();
        MyThread2 myThread2=new MyThread2();
        myThread1.start();//метод start запускает поток,
        // при этом начинает работать метод run(),
        // руками мы его при этом не запускаем
        myThread2.start();
    }
}
//первый способ созания потока - через расширение
// класса Thread, но если класс уже имеет какое-то
// другое расширение, то тогда используем интерфейс runnable
class MyThread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
    //если не оверрайдить метод ран, он будет пустым.
}

class MyThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 1000; i > 0; i--) {
            System.out.println(i);
        }
    }
}
