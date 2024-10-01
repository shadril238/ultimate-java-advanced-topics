package com.shadril238.executors;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class FlightService {
    public Stream<CompletableFuture<Quote>> getQuotes() {
        var sites = List.of("site1", "site2", "site3");
        return sites.stream()
                .map(this::getQuote);
    }

    public CompletableFuture<Quote> getQuote(String site) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Getting a quote from " + site);

            LongTask.simulate(); // simulate() method is used to simulate a long running task. It sleeps the current thread for 3 seconds.
            var random = new Random();
            var price = 100 + random.nextInt(10);

            return new Quote(site, price);
        });
    }

}
