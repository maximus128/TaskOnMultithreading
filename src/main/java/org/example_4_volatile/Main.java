package org.example_4_volatile;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       MyThread myThread = new MyThread();
       myThread.start();

       Scanner scanner = new Scanner(System.in);
       scanner.nextLine();

       myThread.shutdown(); //  не всегда останавливает, из-за плохой когерентности кэша
    }
}
class MyThread extends Thread {
    private volatile boolean running = true;
    // эту переменную без "volatile" может себе забрать один поток/ядро в свой кэш
    // и другой поток изменения не увидет, "volatile" не будет кешироваться ядром,
    // а её значение будет хранится в главной памяти, переменная будет видна всем
    public void run() {
        while (running) {
            System.out.println("Hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void shutdown() {
        this.running = false;
    }
}

