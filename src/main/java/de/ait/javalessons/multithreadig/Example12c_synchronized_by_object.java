package de.ait.javalessons.multithreadig;

//Рассмотрим пример. Нам могут звонить на мобильный телефон по трем
// разным каналам: whatsAp, Skype и просто по мобильной сети.
//Правило: Если мы уже говорим по одному из каналов и нам при
// этом звонят по другому каналу, то этот звонок должен ждать,
// пока не мы не закончим начатый разговор.
public class Example12c_synchronized_by_object {
    //static final Car car = new Car();
    static final Object lock = new Object();

    void mobileCall() {
        synchronized (lock) {
            System.out.println(this);
            System.out.println("Mobile call starts");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Mobile call ends");
        }
    }

    void skypeCall() {
        synchronized (lock) {
            System.out.println(this);
            System.out.println("Skype call starts");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Skype call ends");
        }
    }

    void whatsAppCall() {
        synchronized (lock) {

            System.out.println(this);
            System.out.println("WhatsApp call starts");
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("WhatsApp call ends");
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new RunnableImplMobile());
        Thread thread2 = new Thread(new RunnableImplSkype());
        Thread thread3 = new Thread(new RunnableImplWhatsApp());
        thread1.start();
        thread2.start();
        thread3.start();

        //Без добавления ключевого слова synchronized к сигнатуре метода
    /*output:
Mobile call starts
Skype call starts
WhatsApp call starts
Mobile call ends
Skype call ends
WhatsApp call ends*/

//С добавлением ключевого слова synchronized к сигнатуре методов
   /*output:
Mobile call starts
Skype call starts
WhatsApp call starts
Mobile call ends
Skype call ends
WhatsApp call ends
*/
        //т. е. мы видим, что ничего не изменилось. Почему?
        //когда мы используем synchronized в сигнатуре метода
        // а метод у нас нестатичный, то по умолчанию используется синхронизация на объекте this
        //Если вывести на экран этот объект из каждого метода,
        //то пoлучим такой output:
        /*de.ait.javalessons.multithreadig.Example12c@d9a13e6
Mobile call starts
de.ait.javalessons.multithreadig.Example12c@19ed5536
Skype call starts
de.ait.javalessons.multithreadig.Example12c@79673c51
WhatsApp call starts
Mobile call ends
Skype call ends
WhatsApp call ends*/
//Тут мы видим, что синхронизация идет по трем разным объектам,
//а нам нужна синхронизация по одному объекту, если мы хотим
//решить ту задачу, которую поставили в начале.
//Объект при этом может быть любым
//Убираем synchronized из сигнатуры метода и добавляем в тело метода
// synchronized (car){}. Вот теперь наша задача решена:
        /*output:
de.ait.javalessons.multithreadig.Example12c@24224129
Mobile call starts
Mobile call ends
de.ait.javalessons.multithreadig.Example12c@6dd11338
WhatsApp call starts
WhatsApp call ends
de.ait.javalessons.multithreadig.Example12c@ccbb1a
Skype call starts
Skype call ends*/
//Для того, чтобы не использовать выдуманный класс, используют обычно
//объект lock:     static final Object lock = new Object();


    }
}

class RunnableImplMobile implements Runnable {
    public void run() {
        //создаем объект класса
        new Example12c_synchronized_by_object().mobileCall();
    }
}

class RunnableImplSkype implements Runnable {
    public void run() {
        //создаем объект класса
        new Example12c_synchronized_by_object().skypeCall();
    }
}

class RunnableImplWhatsApp implements Runnable {
    public void run() {
        //создаем объект класса
        new Example12c_synchronized_by_object().whatsAppCall();
    }
}

//создали произвольный класс Car для того,
// чтобы синхронизировать по обекту этого класса все три метода
/*
class Car {
}*/
