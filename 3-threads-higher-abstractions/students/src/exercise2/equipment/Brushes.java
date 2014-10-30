package exercise2.equipment;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Brushes {

    private final BlockingQueue queue;
    
    public Brushes() {
        this.queue = new ArrayBlockingQueue(3);
        this.queue.offer("great");
        this.queue.offer("awesome");
        this.queue.offer("sick");
    }
    public String takeBrush() throws InterruptedException {
        return this.queue.take().toString();
    }

    public void returnBrush(String brush) {
        this.queue.offer(brush);
    }
}
