package org.example_9_wait_notify;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws InterruptedException {
       WaitAndNotify waitAndNotify = new WaitAndNotify();

       Thread thread1 = new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   waitAndNotify.produce();
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
       });

       Thread thread2 = new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   waitAndNotify.consume();
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
       });

       thread1.start();
       thread2.start();

       thread1.join();
       thread2.join();
    }
}

class WaitAndNotify {
    public void produce() throws InterruptedException {
        synchronized (this){
            System.out.println("Producer thread started...");
            wait(); // вызывается только внутри синх блока и только на обьекте в синхрониз блоке
            // 1 - отдан intrinsic lock 2 - ждем пока будет вызван notify()
            System.out.println("Producer thread resume...");
        }
    }
    public void consume() throws InterruptedException {
        Thread.sleep(2000);
        Scanner scanner = new Scanner(System.in);

        synchronized (this){
            System.out.println("Waiting for return key pressed");
            scanner.nextLine();
            notify(); // оповещает что можно продолжить работать, но не освобождает монитор

            Thread.sleep(5000); // после выполнения освободит монитор

        }

    }
}