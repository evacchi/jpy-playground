package io.github.evacchi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Hello world!
 */
public class NamedPipe {
    final String name;
    private FileOutputStream out;

    public NamedPipe(String name) {
        this.name = name;
    }


    public NamedPipe start() {
        new Thread(this::run).start();
        return this;
    }

    public void run() {
        System.out.println("Writing to " + name);
        try {
            this.out = new FileOutputStream(name, true);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        try {
            Thread.sleep(2000);
            out.write("hello".getBytes(StandardCharsets.UTF_8));
            out.write('\n');
            Thread.sleep(2000); // simulates pauses
            out.write("world".getBytes(StandardCharsets.UTF_8));
            out.write('\n');

            out.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
