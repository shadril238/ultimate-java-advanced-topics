package com.shadril238.concurrency;

import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DownloadStatus {
    private int totalBytes;
    private volatile boolean isDone;

    private Object totalBytesLock = new Object(); // This is a lock object that will be used to lock the totalBytes variable

//    private Lock lock = new ReentrantLock();

    public int getTotalBytes() {
        return totalBytes;
    }

//    public void incrementTotalBytes() {
//        lock.lock(); // Lock the code block, so only one thread can access it at a time
//        try{
//            totalBytes++;
//        } finally {
//            lock.unlock(); // Unlock the code block, so other threads can access it
//        }
//    }

    public void incrementTotalBytes() {
        synchronized (totalBytesLock) { // This will lock the object, so only one thread can access it at a time
            totalBytes++;
        }
    }

    public boolean isDone() {
        return isDone;
    }

    public void done() {
        isDone = true;
    }
}
