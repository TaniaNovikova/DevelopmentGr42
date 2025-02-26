package de.ait.javalessons.multithreadig;
//Для начала мы не создаем никаких потоков,
// а попробуем усыпить поток main
public class Example7_sleep_main {
    public static void main(String[] args) throws InterruptedException {

        for (int i = 5; i >0 ; i--) {
            System.out.println(i);
            Thread.sleep(1000);
        }
        System.out.println("Поехали!");
    }
}
