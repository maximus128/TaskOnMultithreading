package org.example_1_extends_Thread;

public class Main {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        //myThread.run(); // не будет параллельности, просто вызовет последовательно метод run
        myThread.start(); // запустит имеено второй поток
        MyThread myThread2 = new MyThread();
        myThread2.start();


        System.out.println("Hello from main thread!");
    }
}
class MyThread extends Thread {
    public void run(){
        for (int i = 0; i< 10; i++){
            System.out.println("Hello my first Thread! i=" + i );
        }
    }
}