package org.example_3_sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("I am going to sleep!");
        Thread.sleep(3000); // возможна InterruptedException, поток main заснёт
        System.out.println("I am awake!!");
    }
}
