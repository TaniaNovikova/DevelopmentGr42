package de.ait.javalessons.multithreadig;

public class Example9a_ThreadStatesDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000); // TIMED_WAITING
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Состояние после создания: " + thread.getState()); // NEW
        thread.start();
        System.out.println("Состояние после start(): " + thread.getState()); // RUNNABLE

        Thread.sleep(100); // Ждем, чтобы поток успел перейти в другое состояние
        System.out.println("Состояние во время работы: " + thread.getState()); // RUNNABLE / TIMED_WAITING

        thread.join();
        System.out.println("Состояние после завершения: " + thread.getState()); // TERMINATED
        /*output:
    Состояние после создания: NEW
    Состояние после start(): RUNNABLE
    Состояние во время работы: TIMED_WAITING
    Состояние после завершения: TERMINATED
    * */
    }
}
