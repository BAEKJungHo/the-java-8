package me.weave.java8to11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {

    public static void main(String[] args) {
        /**
         * executorService 는 작업을 실행하고 다음 작업이 들어올 때까지 실행하기 때문에 프로세스가 죽지 않는다.
         * 따라서 명시적으로 shutdown() 을 해야한다.
         */

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 1
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread : " + Thread.currentThread().getName());
            }
        });

        // 2
        executorService.submit(() -> {
            System.out.println("Thread : " + Thread.currentThread().getName());
        });
        executorService.shutdown(); // graceful shutdown : 현재 진행중인 작업은 끝까지 마치고 끝낸다.

        ExecutorService executorService1 = Executors.newFixedThreadPool(2);
        executorService1.submit(getRunnable("Hello1"));
        executorService1.submit(getRunnable("Hello2"));
        executorService1.submit(getRunnable("Hello3"));
        executorService1.submit(getRunnable("Hello4"));
        executorService1.submit(getRunnable("Hello5"));
        executorService1.shutdown();

        ScheduledExecutorService executorService2 = Executors.newSingleThreadScheduledExecutor();
        executorService2.schedule(getRunnable("Hello"), 3, TimeUnit.SECONDS);

        // Fork/Join Framework 는 멀티프로세싱 프로그램을 개발할 때 유용하다.

        // Callable 은 Runnable 과 비슷한데 차이점은 return 할 수 있다는 것이다. 이 return 으로 받아오는것은 Future 이다.

    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }

}
