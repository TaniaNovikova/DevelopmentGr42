package de.ait.javalessons.multithreadig;
//volatile хорошо справляется со своим назначением, когда только один поток
// изменяет значение, а другие потоки его только читают
public class VolatileExample extends Thread{
volatile boolean b =  true;//после добавления ключевого
// слова volatile потоки мэйн и тред1 не будут обращаться за значением этой
// переменной к своему кэшу, а будут брать его значение только из
// main memory - основной памяти. И не возникнет ситуация,
// когда переменная поменяла значение, но поток об этом не успел узнать.
// то есть не будет рассинхронизации значений этой переменной среди потоков.

@Override
public void run(){
    long counter=0;
    while(b){
        counter++;
    }
    System.out.println("Loop is finished. counter="+counter);
}
    public static void main(String[] args) throws InterruptedException {
      VolatileExample thread1 = new VolatileExample();
      thread1.start();
      Thread.sleep(3000);//метод sleep вызван НЕ на потоке
        // thread1, он вызван для потока MAIN, чтобы поток thread1
        // поработал 3 сек. Когда он три секунды поработал, выводим sout
        System.out.println("After 3 seconds it is time to wake up!");
        thread1.b = false;
        thread1.join();
        System.out.println("End of programm");
        /*output with volatile:
    After 3 seconds it is time to wake up!
    Loop is finished. counter=4105683656
    End of programm */
        //Без volatile код приходилось останавливать вручную

    }

}
