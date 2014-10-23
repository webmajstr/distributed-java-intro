package exercise1;

import common.Counter;

public class SynchronizedCounter implements Counter {

    private long counter = 0;
    
    @Override
    synchronized public void increment() {
        counter++;
    }

    @Override
    public long getValue() {
        return counter;
    }
}
