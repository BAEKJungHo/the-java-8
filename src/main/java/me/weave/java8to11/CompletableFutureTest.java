package me.weave.java8to11;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureTest {

    public static void main(String[] args) throws Exception {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("weave");

        // return type 이 없는 경우
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("Hello" + Thread.currentThread().getName());
        });
        voidCompletableFuture.get();

        // return type 이 있는 경우
        CompletableFuture<String> stringCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello" + Thread.currentThread().getName());
            return "Hello";
        }).thenApply((s) -> { // call back : return 이 있는 경우 (thenApply)
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();
        });
        System.out.println(stringCompletableFuture1.get());

        // return type 이 없는 경우
        CompletableFuture<Void> voidCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello" + Thread.currentThread().getName());
            return "Hello";
        }).thenAccept((s) -> { // call back : return 이 없는 경우 (thenAccept)
            System.out.println(Thread.currentThread().getName());
        });
        voidCompletableFuture1.get();

        // 뭔가 하기만 하면 되는 콜백 : thenRun
        CompletableFuture<Void> voidCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello" + Thread.currentThread().getName());
            return "Hello";
        }).thenRun(() -> { // 이 자리에는 Runnable 이 온다.
            System.out.println(Thread.currentThread().getName());
        });

        // ForkJoinPool 은 자바 7 에서 들어온것인데, 작업을 DeQueue 를 써서 처리한다.
        // 작업 단위를, 서브 태스크를 분산 시켜서 처리하고 그 결과값을 모아서 처리하는 프레임워크이다.
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return "Hello";
        }, executorService).thenRun(() -> {
            System.out.println(Thread.currentThread().getName());
        });
    }

}
