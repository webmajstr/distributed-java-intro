package exercise1.equipment;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Paints {
    private int available = 3;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void takePaint() throws InterruptedException {
        while (available == 0) {
            lock.lock();
        }
        if (available == 0) {
            throw new IllegalStateException("There are no more paints!");
        }
        available -= 1;
    }

    public void returnPaint() {
        lock.unlock();
        available += 1;
    }
}
