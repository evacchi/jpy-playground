package io.github.evacchi;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Hello world!
 *
 */
public class App {
    BlockingQueue<String> bq = new ArrayBlockingQueue<>(10);
    public BlockingQueue<String> blocking_queue() {
        return bq;
    }

    public Queue<String> queue() {
        return bq;
    }

    public App start() {
        new Thread(this::run).start();
        return this;
    }

    public void run() {
        try {
            Thread.sleep(2000);
            var m = "hello";
            bq.add(m);
        } catch (Exception e) {
        }
    }

}
