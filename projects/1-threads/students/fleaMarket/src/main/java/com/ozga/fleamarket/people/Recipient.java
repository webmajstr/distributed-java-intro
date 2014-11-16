/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozga.fleamarket.people;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Ozga
 */
public class Recipient implements Runnable{
    private final String name;
    private final List<String> items;
    private final Chairman chairman;
    private Lock lock = new ReentrantLock();
    private Condition auctionRunning = lock.newCondition();
    //auction

    public Recipient(String name) {
        this.name = name;
        this.items = new ArrayList();
        this.chairman = Chairman.getInstance();
    }
    
    public void addItem(String item) throws InterruptedException {
        Random generator = new Random();
        items.add(item);
        Thread.sleep(1000 * (generator.nextInt(10) + 6));
    }

    public String getName() {
        return name;
    }
    
    public void onAuctionFinished() {
        lock.lock();
        try {
            auctionRunning.signalAll();
        } finally {
            lock.unlock();
        }
    }
    
    
    @Override
    public void run() {
        Random generator = new Random();
        lock.lock();
        try {
            while(true) {
                Thread.sleep(500 * (generator.nextInt(10) + 1));
                
                if (this.chairman.register(this)) {
                    auctionRunning.await();
                }
            }
           
        } catch (InterruptedException ex) {
           // Logger.getLogger(Recipient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            lock.unlock();
            System.out.println(this.name + " says good bye leaving with items " + this.items.toString());
        }
    }
    
    
}
