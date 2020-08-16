package me.weave.java8to11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTest2 {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Callable<String> hello = () -> {
            Thread.sleep(1000L);
            return "Hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(2000L);
            return "Java";
        };

        Callable<String> spring = () -> {
            Thread.sleep(3000L);
            return "Spring";
        };

        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, spring));
        for(Future<String> f : futures) {
            System.out.println(f.get());
        }

        // invokeAny 는 블로킹 콜이며, 결과는 Thread.sleep 이 가장 짧은 애가 나온다.
        String s = executorService.invokeAny(Arrays.asList(hello, java, spring));
        System.out.println(s);

        executorService.shutdown();
    }

}
