package de.ait.javalessons.multithreadig;

//Здесь мы создаем потоки двумя способами - через extends Thread
//и через implements Runnable, и в обоих классах одинаково переписываем метод run
public class Example8_sleep_join extends Thread {
    public void run() {
        for (int i = 0; i <= 10; i++) {
            //Мы не можем с методом run бросить exeption в сигнатуру метода,
            // как в предыдущем Example,потому что мы этот метод оверрайдим
            // и должны этот exeption ловить
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new MyRunnable());
        Example8_sleep_join thread2 = new Example8_sleep_join();
        thread1.start();
        thread2.start();
       // Если мы хотим, чтобы вывод в консоль слова End произошел после того,
        // как оба потока завершат свою работу, нужно использовать метод join
        //для того, чтобы дать понять потоку main, что он должен подождать
        //завершения работы обоих потоков, и только тогда выполниться самому.

        thread1.join();//throws InterruptedException
        thread2.join();//
    //что делает join? Он вызван ВНУТРИ потока main,
        // и тогда мэйн будет ждать тот поток,
        // НА котором вызван join (поток thread1, к примеру),
        // и только тогда продолжит свою работу
        System.out.println("End");
    }
}


class MyRunnable implements Runnable {
    public void run() {
        for (int i = 0; i <= 10; i++) {
            //Мы не можем с методом run бросить exeption в сигнатуру метода,
            // как в предыдущем Example,потому что мы этот метод оверрайдим
            // поэтому мы должны этот exeption ловить
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + i);
            /*output:
            End
Thread-1 0
Thread-0 0
Thread-0 1
Thread-1 1
Thread-1 2
Thread-0 2
Thread-1 3
Thread-0 3
и т. д. до 10
            * */
            /*Что здесь происходит: поток мэйн заупстил потоки, которые отпочковались
             от него и работают самостоятельно, засыпая каждую итерацию на 1 сек, а мэйн тем временем отработал
             свой метод sout, вывел End  и продолжил работу, пока не закончат работать эти два потока*/
        }
    }
}