package com.shadril238.concurrency;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadDemo {
    public static void show() {
        System.out.println("-------------------Threads-------------------");

        System.out.println(Thread.currentThread().getName()); // Main Thread - The thread that runs the main method

        Thread thread = new Thread(new DownloadFileTask());
        thread.start(); // This will call the run method in the DownloadFileTask class

        // This will create 10 threads that will run the DownloadFileTask, but they will run in parallel, not sequentially
//        for (int i = 0; i < 10; i++) {
//            Thread thread1 = new Thread(new DownloadFileTask());
//            thread1.start();
//        }

//        try {
//            thread.join(); // This will make the threads run sequentially, not in parallel, because it will wait for the thread to finish before moving on
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        try {
            thread.sleep(1000); // This will pause the main thread for 1 second
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread.interrupt(); // This will interrupt the thread

        System.out.println("File is ready to be scanned.");

        // Race Condition - When multiple threads are trying to access the same resource (DownloadStatus in this case) at the same time and the outcome of the program depends on the sequence of execution of these threads
        var status = new DownloadStatus();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            var thread1 = new Thread(new DownloadFileTask(status));
            thread1.start();
            threads.add(thread1);
        }

        for (var t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(status.getTotalBytes());

        // Thread Safety - Making sure that a resource can be accessed by multiple threads without any issues
        // Volatile Keyword - This keyword is used to indicate that a field might be changed by multiple threads that are executing at the same time
        // Synchronized Keyword - This keyword is used to make sure that only one thread can access a resource at a time
        // Locks - A more advanced way to make sure that only one thread can access a resource at a time
        List<DownloadFileTask> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            var task = new DownloadFileTask(new DownloadStatus());
            tasks.add(task);
            var thread1 = new Thread(task);
            thread1.start();
        }

        var totalBytes = tasks.stream()
                .map(t -> t.getStatus().getTotalBytes())
                .reduce(Integer::sum);


        System.out.println(totalBytes);

        // Volatile Keyword - This keyword is used to indicate that a field might be changed by multiple threads that are executing at the same time, it is used to prevent caching of variables, so all threads can see the most up-to-date value

        var status1 = new DownloadStatus();
        var thread1 = new Thread(new DownloadFileTask(status1));
        var thread2 = new Thread(() -> {
            while (!status1.isDone()) {
                synchronized (status1) {
                    try {
                        status1.wait(); // This will make the thread wait until it is notified by another thread
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Total bytes: " + status1.getTotalBytes());
        });

        thread1.start();
        thread2.start();

        // Synchronized Collections - These are collections that are thread-safe, meaning that they can be accessed by multiple threads without any issues
        Collection<Integer> collection =
                Collections.synchronizedCollection(new ArrayList<>());
        var thread3 = new Thread(() -> {
            collection.addAll(Arrays.asList(1, 2, 3));
        });

        var thread4 = new Thread(() -> {
            collection.addAll(Arrays.asList(4, 5, 6));
        });

        thread3.start();
        thread4.start();

        try {
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(collection);

        // Concurrent Collections - These are collections that are optimized for concurrent access, they are more efficient than synchronized collections
        Map<Integer, String> map = new ConcurrentHashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        map.get(1);
        map.remove(1);
    }
}
