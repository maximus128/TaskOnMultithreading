package org.example_5_synchronized;

public class Test {
    private int counter;
    // два потака меняют эту переменную -> "состояние гонки"

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        // у каждого обьекта есть монитор, только один поток может взаимодействовать
        // с обьектом и его методами
        test.doWork();
    }
    /*
    1: 100 -> +1 -> 101; 101 -> +1 -> 102; 102 -> +1 -> 103
    2: в памяти еще 100 -> +1 -> опять записывает в counter 101
     */

    public synchronized void increment(){ // только один поток может выполнять тело метод
        counter++;
    }

    public void doWork() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++)
                    //counter = counter +1;
                    increment();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++)
                    //counter = counter +1;
                    increment();
            }
        });
        thread1.start();
        thread2.start();

        thread1.join(); // InterruptedException
        thread2.join();
        //заставляет дождаться выполнения потоков, только после этого вызовет ->
        //без ".join()" main поток не будет дожидаться и может вывести count=0
        System.out.println(counter);
    }

}

