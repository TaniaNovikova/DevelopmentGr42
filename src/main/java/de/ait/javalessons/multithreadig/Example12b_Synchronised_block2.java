package de.ait.javalessons.multithreadig;

public class Example12b_Synchronised_block2 {
    static int counter = 0;
 //сейчас мы будем использовать synchronized внутри СТАТИЧЕСКОГО метода,
 // а поэтому мы не можем использовать this
    public static  void increment() {
        synchronized(Example12b_Synchronised_block2.class){
        counter++;}
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new R2());
        Thread thread2 = new Thread(new R2());

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(counter);

    }
}

class R2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Example11_synchronized.increment();
        }
    }

}
