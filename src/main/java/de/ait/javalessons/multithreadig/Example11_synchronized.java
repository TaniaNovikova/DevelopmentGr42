package de.ait.javalessons.multithreadig;
/*DATA RACE - это проблема, которая может возникнуть,
когда два и более потоков обращаются к одной и той же переменной
 и как минимум один поток ее изменяет */
public class Example11_synchronized {
    static  int counter = 0;
    public  static synchronized void increment(){counter++;}

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new R());
        Thread thread2 = new Thread(new R());

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(counter);
        /*output без synchronized:
        первый раз 2000
        второй раз 2000
        третий раз 1995*/
        //т. е. мы должны бы всегда получать на
        // выходе 2000, но этого не происходит,
        // а происходит data race т.к. работа
        // потоков не синхронизирована, они работают параллельно,
        // делают свою работу, не обращая внимание друг на друга

        //проблема решается добавлением ключевого слова
        // synchronized к методу increment. При добавлении этого слова
        // программа работает так: один поток заходит в метод и ставит замок (lock)
        //Благодаря этому замку, другие потоки должны ждать,
        // пока первый поток не выйдет из метода, закончив в нем
        // свою работу, когда поток выходит из метода. замок открывается,
        // и в метод может зайти следующий поток, который ставит в
        // свою очередь замок для других потоков. Т.о. достигается СИНХРОНИЗАЦИЯ
       // Теперь программа работает предсказуемо, и на выходе всегда дает 2000
    }
}

class R implements  Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
           Example11_synchronized.increment();
        }
    }
}
