/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozga.fleamarket.queues;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Ozga
 */
public class ItemsQueue {
    
    private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
    private AtomicInteger counter;
    private Lock lock = new ReentrantLock();

    public ItemsQueue() {
        counter = new AtomicInteger(0);
    }
    
    
    public boolean isFull() {
        return queue.size() == 10;
    }
    
    public boolean isEmpty() {
        return queue.isEmpty();
    }
    
    public String enqueueItem (){
        lock.lock();
        try {
            String itemName = null;
            if (!this.isFull()) {
                itemName = "Item-" + Integer.toString(counter.incrementAndGet());
                queue.add(itemName);
            }
            return itemName;
        } finally {
            lock.unlock();
        }
        
        
    }
    
    public String dequeueItem() throws IllegalAccessException {
        if (this.isEmpty()) {
            throw new IllegalAccessException();
        }
        return queue.poll();
    }
    
}
