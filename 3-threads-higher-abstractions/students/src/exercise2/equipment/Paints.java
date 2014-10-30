package exercise2.equipment;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Paints {

    private final BlockingQueue queue;

    public Paints() {
        this.queue = new ArrayBlockingQueue(3);
        this.queue.offer("green");
        this.queue.offer("red");
        this.queue.offer("yellow");
    }

    public String takePaint() throws InterruptedException {
        return this.queue.take().toString();
    }

    public void returnPaint(String paint) {
        this.queue.offer(paint);
    }
}
