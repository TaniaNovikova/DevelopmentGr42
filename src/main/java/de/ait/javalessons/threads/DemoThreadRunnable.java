package de.ait.javalessons.threads;

public class DemoThreadRunnable {
    private Thread thread;
    private String threadName;

    public DemoThreadRunnable(String threadName) {
        this.threadName = threadName;
        System.out.println("Creating thread " + threadName);
            }
   public void run() {
       System.out.println("Running thread " + threadName);
       for (int i = 10; i >0 ; i--) {

       }
   }


}
