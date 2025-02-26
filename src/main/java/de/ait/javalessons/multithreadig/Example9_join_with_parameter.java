package de.ait.javalessons.multithreadig;

public class Example9_join_with_parameter {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Method main begins");
        Thread thread = new Thread(new Worker() );
        System.out.println(thread.getState());

        thread.start();
        System.out.println(thread.getState());
        //thread.join(); //main ждет завершения потока
        /*output:
    Method main begins
    Work begins
    Work ends
    Method main ends*/
        thread.join(1500);//метод мэйн будет ждать,
        // когда thread завершит свою работу
        // или же пока не пройдет полторы секунды
        //поскольку этот поток спит 2,5 секунды,
        // а мэйн ждет только полторы секунды,
        // то мэйн выполнится, не дожидаясь thread
        // в случае, когда thread.join(4000), поток
        // мэйн не будет ждать 4 секунды, а выполнится
        //сразу после завершения потока thread, те после 2,5 сек
/*output:
Method main begins
Work begins
Method main ends
Work ends*/
        System.out.println(thread.getState());
        System.out.println("Method main ends");
        /*output после того, как добавили System.out.println(thread.getState());

Method main begins
NEW
RUNNABLE
Work begins
TIMED_WAITING
Method main ends
Work ends*/
//Нужно быть аккуратным с выводом состояния потока на экран. В данном случае у нас
//все гладко сработало, потому что поток спит 2,5 секунд. А если бы поток обрабатывался
// очень быстро, информация о его состоянии, которая выводится тем временем на экран,
// могла бы не успеть за актуальным состоянием потока. И поток, например, был бы
// уже TERMINATED, а на экран бы вывелось RUNNABLE
    }
}

class Worker implements Runnable{
    @Override
    public void run() {
        System.out.println("Work begins");
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work ends");

    }
}