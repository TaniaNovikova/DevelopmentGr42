package de.ait.javalessons.multithreadig;

//Тред нужно запускать с помощью метода start,
// а он уже сам под капотом запускает метод run
// Здесь мы поэкспериментируем с запуском метода run
//руками
public class Example6_run_vs_start implements Runnable {
    public void run() {
        //Thread.currentThread(): currentThread() - статический метод класса Thread,
        // он показывает, в каком текущем потоке мы находимся.
        System.out.println("Method run. Thread name: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Example6_run_vs_start());
      //  thread1.start();
        //запустим этот поток ля начала с помощью метода start,
        // и одновременно выведем на консоль такую же информацию о потоке main
        System.out.println("Method main. Thread name: " + Thread.currentThread().getName());
    //output:
    // Method run. Thread name: Thread-0 (дефолтное имя)
    //Method main. Thread name: main
        //Теперь запустим наш поток с помощью метода start
    thread1.run();
    /*output:
    Method main. Thread name: main
    Method run. Thread name: main*/
        /*Т.о. мы видим, что метод run не запустил отдельный поток,
        он просто выполнился в потоке main, т.е. потоки нужно запускать только с помощью метода start*/
    }
}
