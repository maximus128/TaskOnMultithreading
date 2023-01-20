package org.example_6_synchronized;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        new Worker().main();
    }
}
class Worker {
    Random random = new Random();

    Object lock1 = new Object();
    Object lock2 = new Object();
    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();
    public void addToList1() { // 1. public synchronized void addToList1()
        // синхронизация на методе, чтобы заполнений было =2000, но скорость выполнения 5100ms
        synchronized (lock1){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list1.add(random.nextInt(100));
    }}
    public void addToList2(){

        synchronized (lock2){ // 2. синхронизация на обьекте локере скорость около 2500

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                 list2.add(random.nextInt(100));
    }}
    public void work(){
        for (int i = 0; i<1000; i++) {
            addToList1();
            addToList2();
        }
    }
    public void main(){
        long before = System.currentTimeMillis();
        // 1ый - вариант просто запускаем метод ворк
        //work();
        // 2ой - с потоками
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                work();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                work();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long after = System.currentTimeMillis();
        System.out.println("Program took " + (after-before)+ "ms to run");
        System.out.println("List1 : " + list1.size());
        System.out.println("List2 : " + list2.size());
    }
}


