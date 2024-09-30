package com.shadril238.concurrency;

public class DownloadFileTask implements Runnable {
    private DownloadStatus status;

    public DownloadFileTask() {
        this.status = new DownloadStatus();
    }

    public DownloadFileTask(DownloadStatus status) {
        this.status = status;
    }

    @Override
    public void run() {
        System.out.println("Downloading a file: " + Thread.currentThread().getName());

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        for (var i = 0; i < 10_000; i++) {
            if (Thread.currentThread().isInterrupted()) return;
//            System.out.println("Downloading byte " + i);
            status.incrementTotalBytes();
        }
        status.done();
        synchronized (status) {
            status.notifyAll(); // This will notify all the threads that are waiting on the status object
        }
        System.out.println("Download complete: " + Thread.currentThread().getName());
    }

    public DownloadStatus getStatus() {
        return status;
    }
}
