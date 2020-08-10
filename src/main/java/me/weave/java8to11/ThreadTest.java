package me.weave.java8to11;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();

        System.out.println(Thread.currentThread().getName()); // main thread

        // Thread 생성하기 방법 2. Runnable
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread1 : " + Thread.currentThread().getName());
            }
        });
        thread.start();

        // 람다 버전
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000L); // 스레드 sleep
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread2 : " + Thread.currentThread().getName());
        });
        thread1.start();

        System.out.println("Hello : " + Thread.currentThread().getName());

        // interrupt
        Thread thread2 = new Thread(() -> {
            while (true) {
                System.out.println("Thread3 : " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("exit");
                    return;
                }
            }
        });

        thread2.start();
        Thread.sleep(3000L); // 3초
        thread2.interrupt(); // interruptedException 발생시킴
    }

    // 레드 생성하기 방법 1. Thread 상속
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread : " + Thread.currentThread().getName());
        }
    }

}
