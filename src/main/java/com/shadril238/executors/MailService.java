package com.shadril238.executors;

import java.util.concurrent.CompletableFuture;

public class MailService {
    public void send() {
        LongTask.simulate();
        System.out.println("Mail was sent.");
    }

    // sendAsync() method is used to send the mail asynchronously. It returns a CompletableFuture<Void> object. CompletableFuture is a class that is used to represent a future result of an asynchronous computation.
    public CompletableFuture<Void> sendAsync() {
        return CompletableFuture.runAsync(() -> send());
    }
}
