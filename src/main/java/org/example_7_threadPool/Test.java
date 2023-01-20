package org.example_7_threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        // пул из двух потоков потоков =
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i =0; i < 5; i++){
            executorService.submit(new Work(i));
        }
        executorService.shutdown(); // заканчиваем передачу новых заданий, переходим к выполнению
        System.out.println("All task submitted");
        executorService.awaitTermination(1, TimeUnit.DAYS); //сколько времени даём на выполнение 
    }
}
 class Work implements Runnable{
    private int id;
    public Work(int id){
        this.id = id;
    }

    @Override
     public void run() {
         try {
             Thread.sleep(5000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }

         System.out.println("Work " + id + " was completed");
     }
 }

