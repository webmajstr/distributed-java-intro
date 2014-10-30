package exercise1.equipment;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Brushes {
    private int available = 3;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void takeBrush() throws InterruptedException {
        if (available == 0) {
            throw new IllegalStateException("There are no more brushes!");
        }
        available -= 1;
    }

    public void returnBrush() {
        available += 1;
    }
}
