/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozga.fleamarket.people;

import com.ozga.fleamarket.queues.ItemsQueue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ozga
 */
public class Chairman implements Runnable {

    private ItemsQueue itemsQueue;
    private final BlockingQueue<Recipient> recipients;
    private static Chairman instance;
    private final Lock lock;
    private boolean isAuctionRunning;

    public static synchronized Chairman getInstance() {
        if (instance == null) {
            instance = new Chairman();
        }
        return instance;
    }

    private Chairman() {
        this.recipients = new ArrayBlockingQueue<>(10);
        lock = new ReentrantLock();
        isAuctionRunning = false;
        itemsQueue = new ItemsQueue();
    }

    public String addItem() {
        return itemsQueue.enqueueItem();
    }

    public synchronized boolean register(Recipient recipient) {
        boolean output = false;
        lock.lock();
        try {
            output = !this.isAuctionRunning && recipients.offer(recipient);
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
        if (output) {
            System.out.println("Registering " + recipient.getName());

        }
        return output;
    }

    private Recipient finishAuction(String item) throws InterruptedException {
        lock.lock();
        try {
            Thread.sleep(5000);
            isAuctionRunning = true;
            int length = this.recipients.size();
            Recipient winner = null;
            
            if (length > 0) {
                Random generator = new Random();
                int winnerId = generator.nextInt(length);
                
                for (int i = 0; i < length; i++) {
                    if (i == winnerId) {
                        winner = this.recipients.poll();
                    } else {
                        this.recipients.poll().onAuctionFinished();
                    }
                }
                winner.addItem(item);
                
                System.out.println("Winner for auction " + item + " is " + winner.getName());
            } else {
                System.out.println("There is no winner for " + item);
            }
            isAuctionRunning = false;
            return winner;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        Random generator = new Random();

        try {
            Thread.sleep(5000);
            while (true) {
                if (!isAuctionRunning) {
                    try {
                        String item = this.itemsQueue.dequeueItem();
                        this.finishAuction(item);
                    } catch (IllegalAccessException ex) {
                        Thread.sleep(500 * (generator.nextInt(10) + 1));
                        if (itemsQueue.isEmpty()) {
                            System.out.println("No auctions within 5 seconds. Closing the market.");
                            MarketManager.getInstance().endAuction();
                        }
                    }
                }

            }

        } catch (InterruptedException ex) {
           // Logger.getLogger(Chairman.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("Chairman says good bye.");
        }

    }

}
