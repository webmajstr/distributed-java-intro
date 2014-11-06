/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozga.fleamarket.queues;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Ozga
 */
public class ItemsQueue {
    
    private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
    private int counter = 0;
    
    public boolean isFull() {
        return queue.size() == 10;
    }
    
    public boolean isEmpty() {
        return queue.isEmpty();
    }
    
    public String enqueueItem () {
        String itemName = "Item-" + Integer.toString(counter);
        queue.offer(itemName);
        counter++;
        
        return itemName;
    }
    
    public String dequeueItem() throws InterruptedException {
        return queue.take();
    }
    
}
