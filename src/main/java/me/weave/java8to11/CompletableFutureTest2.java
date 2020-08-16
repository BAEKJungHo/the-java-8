package me.weave.java8to11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CompletableFutureTest2 {

    public static void main(String[] args) throws Exception {
        boolean throwError = true;

        // 에러처리 1
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            if(throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("Hello" + Thread.currentThread().getName());
            return "Hello";
        }).exceptionally(ex -> {
            System.out.println(ex);
            return "Error";
        });

        // 에러처리 2
        CompletableFuture<String> hello1 = CompletableFuture.supplyAsync(() -> {
            if(throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("Hello" + Thread.currentThread().getName());
            return "Hello";
        }).handle((result, ex) -> {
            if(ex != null) {
                System.out.println(ex);
                return "Error";
            }
            return result;
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return "World";
        });

        // hello 를 먼저하고 world 를 하려면 아래처럼 하면 된다. (즉 서로 연관관계가 있는 경우. A 처리 후 B 처리)
        // thenCompose
        CompletableFuture<String> future = hello.thenCompose(CompletableFutureTest2::getWorld);
        System.out.println(future.get());

        // 둘이 서로 연관 관계가 없는 경우에 비동기적으로 실행하는 방법 ex) 애플 주식 가져오고, MS 주식 가져오고
        // thenCombine
        CompletableFuture<String> future1 = hello.thenCombine(world, (h, w) -> {
            return h + " " + w;
        });
        System.out.println(future1.get());

        // 서브 태스크가 두 개 이상일 때 합쳐서 실행하는 방법
        // allOf
        CompletableFuture<Void> future2 = CompletableFuture.allOf(hello, world)
                .thenAccept(System.out::println);
        System.out.println(future2.get()); // null


        // list 로 결과 받는 방법
        List<CompletableFuture<String>> futures = Arrays.asList(hello, world);
        CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

        CompletableFuture<List<String>> results = CompletableFuture.allOf(futuresArray)
                .thenApply(v -> {
                    return futures.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList());
                });
        results.get().forEach(System.out::println);
    }


    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World" + Thread.currentThread().getName());
            return message + " World";
        });
    }

}
