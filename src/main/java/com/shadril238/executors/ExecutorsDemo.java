package com.shadril238.executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class ExecutorsDemo {

    // Thread Pool -> Thread Pool is a collection of threads that are created to perform a number of tasks. It is a way to manage a large number of threads that are created to perform a number of tasks.
    // Executor Framework -> Executor Framework is a framework that is used to decouple the task creation and execution. It is a way to manage the threads that are created to perform a number of tasks.
    // Executor Service -> Executor Service is an interface that is used to manage the threads that are created to perform a number of tasks.
    // Executors -> Executors is a class that is used to create the thread pool and manage the threads that are created to perform a number of tasks.
    // ThreadPoolExecutor -> ThreadPoolExecutor is a class that is used to create the thread pool and manage the threads that are created to perform a number of tasks.
    public static void show() {
        System.out.println("--------- ExecutorsDemo ----------");
        var executor = Executors.newFixedThreadPool(2);

        try {
            var future = executor.submit(() -> { // submit() method is used to submit the task to the thread pool.
                LongTask.simulate();
                return 1;
            });
            System.out.println("Do more work here.");

            try {
                System.out.println(future.get()); // get() method is used to get the result of the task.
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        } finally {
            executor.shutdown(); // shutdown() method is used to shutdown the thread pool.
        }


    }
}
