package org.example_13_Semaphore;

import java.util.concurrent.Semaphore;
// Семафор управляет доступом к общему ресурсу с помощью счетчика.
// Если счетчик больше нуля, доступ разрешается,а если он равен нулю, то в доступе будет отказано.
public class Test {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        try {
            semaphore.acquire();// взяли разрешение и взаимодействуем с ресурсом
            semaphore.acquire();
            semaphore.acquire();

            System.out.println("All permits have been acquired");

            semaphore.acquire();

            System.out.println("Can't reach here...");


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        semaphore.release(); // отдает разрешение на использование

        System.out.println(semaphore.availablePermits());

    }
}