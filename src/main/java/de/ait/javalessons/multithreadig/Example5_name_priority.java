package de.ait.javalessons.multithreadig;

//поговорим об имени потока и его приоритете
public class Example5_name_priority {
    public static void main(String[] args) {
        MyThread5 myThread5 = new MyThread5();
        System.out.println("Name of myThread5: " + myThread5.getName() + "  Priority of myThread5: " + myThread5.getPriority());
        //output: Name of myThread5: Thread-0  Priority of myThread5: 5

        MyThread5 myThread6 = new MyThread5();
        System.out.println("Name of myThread6: " + myThread6.getName() + "  Priority of myThread6: " + myThread6.getPriority());
        //output: Name of myThread6: Thread-1  Priority of myThread6: 5
//имена по умолчанию создаются таким образом, как их вывел sout, а приоритет по умолчанию будет 5 (по шкале от 1-10)

        MyThread5 myThread7 = new MyThread5();
        myThread7.setName("MyThread7");
        //  myThread7.setPriority(10);
        //myThread7.setPriority(Thread.MIN_PRIORITY);//Name of myThread7: MyThread7  Priority of myThread7: 1
       // myThread7.setPriority(Thread.MAX_PRIORITY);//Name of myThread7: MyThread7  Priority of myThread7: 10
        //myThread7.setPriority(Thread.NORM_PRIORITY);//Name of myThread7: MyThread7  Priority of myThread7: 5

        System.out.println("Name of myThread7: " + myThread7.getName() + "  Priority of myThread7: " + myThread7.getPriority());

    }
}

class MyThread5 extends Thread {
    public void run() {
        System.out.println("PRIVET");
    }


}
