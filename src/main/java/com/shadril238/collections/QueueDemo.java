package com.shadril238.collections;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueDemo {
    public static void show(){
        Queue<String> queue = new ArrayDeque<>();
        queue.add("c");
        queue.add("a");
        queue.add("b");
        queue.offer("d");
        System.out.println(queue);

        var front = queue.peek();
        System.out.println(front);

        // Removing the item from the front of the queue
        front = queue.poll();
        System.out.println(front);
    }
}
