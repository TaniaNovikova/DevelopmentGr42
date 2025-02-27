package de.ait.javalessons.multithreadig;

//Монитор - это сущность/механизм, благодаря которому
// достгается коректная работа при синхронизации.
// В Java у каждого класа и обхекта есть привязанный к нему монитор.
//мониторы работают примерно так же, как и synchronized методы, но
//они приявязаны не к коду, а к объекту или классу.
public class Example12a_Monitor_Synchronized_block1 {
    public static void main(String[] args) {
        // объект MyRunnableImpl2 runnable- объект,
        // на котором будет вестись синхронизация
        MyRunnableImpl2 runnable = new MyRunnableImpl2();

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class Counter2 {
    volatile static int count = 0;
}

class MyRunnableImpl2 implements Runnable {
//главное отличие синхронизизрованного метода от синхронизированного блока в том,
// что мы можем сихронизировать не весь метод, а только его часть
    private void doWork2(){
        System.out.println("Ura!");
    }

    private void doWork1() {
        //этому блоку кода не нужна сихронизация, поэтому мы можем позволить
        // ему выполняться одновременно в нескольких потоках, а когда дойдет
        // до синхронизированного блока, он будет выполняться синхронизированно
        doWork2();
        // синхронизированный блок, синхронизация происходит
        // на объекте this. Метод doWork1 не статичный,
        // поэтому перед тем, как его вызвать, мы должны будем
        // создать объект MyRunnableImpl2
        synchronized (this) {
            Counter2.count++;
            System.out.println(Counter2.count);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            doWork1();
        }
    }
}
