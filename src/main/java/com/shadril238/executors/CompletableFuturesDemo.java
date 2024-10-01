package com.shadril238.executors;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CompletableFuturesDemo {
    public static int toFahrenheit(int celsius) {
        return (int) (celsius * 9 / 5) + 32;
    }

    public static void show() {
        System.out.println("--------- CompletableFuturesDemo ----------");

        Supplier<Integer> task = () -> 238;
        var future = CompletableFuture.supplyAsync(task);
        try {
            var result = future.get();
            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // thenRunAsync() method is used to run the task asynchronously after the completion of the future. thenRun() method is used to run the task synchronously after the completion of the future.
        future.thenRunAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("Done");
        });

        // Handling exceptions
        var future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Getting the current weather");
            throw new IllegalStateException();
        });

        try {
            future2.exceptionally(ex -> 1).get(); // This will return 1 if there is an exception, otherwise it will return the result
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            e.getCause();
            throw new RuntimeException(e);
        }

        // Transforming a CompletableFuture
        var future3 = CompletableFuture.supplyAsync(() -> 20);
        var future4 = future3.thenApply(CompletableFuturesDemo::toFahrenheit)// thenApply() method is used to transform the result of the future, it takes a function as an argument
                .thenAccept(result -> System.out.println(result)); // thenAccept() method is used to consume the result of the future, it takes a consumer as an argument

        CompletableFuture.supplyAsync(() -> "email")
                .thenCompose(email -> CompletableFuture.supplyAsync(() -> "playlist")) // thenCompose() method is used to chain two CompletableFutures together
                .thenAccept(playlist -> System.out.println(playlist));

        // Combining CompletableFutures
        var future5 = CompletableFuture.supplyAsync(() -> "20USD")
                .thenApply(str -> {
                    var price = str.replace("USD", "");
                    return Integer.parseInt(price);
                });
        var future6 = CompletableFuture.supplyAsync(() -> 30);
        future5.thenCombine(future6, (price, exchangeRate) -> price * exchangeRate) // thenCombine() method is used to combine two CompletableFutures together
                .thenAccept(result -> System.out.println(result));

        // Waiting for many CompletableFutures
        var future7 = CompletableFuture.supplyAsync(() -> "email");
        var future8 = CompletableFuture.supplyAsync(() -> "playlist");
        var future9 = CompletableFuture.supplyAsync(() -> "product");

        var all = CompletableFuture.allOf(future7, future8, future9); // allOf() method is used to wait for all the CompletableFutures to complete
        all.thenRun(() -> {
            System.out.println("All are done successfully");
        });

        // FlightService project
        var startTime = LocalTime.now();
        var flightService = new FlightService();
        var futures = flightService.getQuotes()
                .map(f -> f.thenAccept(System.out::println))
                .collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenRun(() -> {
                    var endTime = LocalTime.now();
                    Duration duration = Duration.between(startTime, endTime);
                    System.out.println("All flights received in " + duration.toMillis() + " msec");
                });

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
