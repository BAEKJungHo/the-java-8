package me.weave.java8to11;

import java.util.concurrent.*;

public class CallableTest {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Future<String> helloFuture = executorService.submit(hello);
        // 상태 체크
        System.out.println(helloFuture.isDone());

        System.out.println("Started!");

        helloFuture.get(); // 블로킹 콜

        /**
         * helloFuture.cancel(true); // 현재 진행중인 작업을 interrupt 해서 종료료
        */

        System.out.println("End !!");
        executorService.shutdown();
    }

}
