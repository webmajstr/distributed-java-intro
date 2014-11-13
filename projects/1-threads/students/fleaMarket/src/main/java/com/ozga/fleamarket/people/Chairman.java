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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ozga
 */
public class Chairman implements Runnable {

    private final ItemsQueue itemsQueue;
    private final MarketManager marketManager;
    private final BlockingQueue<Recipient> recipients;

    public Chairman(ItemsQueue itemsQueue, MarketManager marketManager) {
        this.itemsQueue = itemsQueue;
        this.marketManager = marketManager;
        this.recipients = new ArrayBlockingQueue<>(10);
    }
    
    public String addItem() {
        return itemsQueue.enqueueItem();
    }
    

    public synchronized boolean register(Recipient recipient) {
        try {
            System.out.println("Registering " + recipient.getName());
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    private Recipient finishAuction(String item) throws InterruptedException {
        int length = this.recipients.size();
        Recipient winner = null;

        if (length > 0) {
            Random generator = new Random();
            int winnerId = generator.nextInt(length);

            for (int i = 0; i < length; i++) {
                if (i == winnerId) {
                    winner = this.recipients.poll();
                } else {
                    this.recipients.poll();
                }
            }
            winner.addItem(item);

            System.out.println("Winner for auction " + item + " is " + winner.getName());
        } else {
            System.out.println("There is no winner for " + item);
        }

        return winner;

    }

    @Override
    public void run() {
        Random generator = new Random();

        try {
          //  Thread.sleep(10000);
            while (true) {
                try {
                    String item = this.itemsQueue.dequeueItem();
                    this.finishAuction(item);
                } catch (IllegalAccessException e) {
                    Thread.sleep(500 * (generator.nextInt(10) + 1));
                    if (itemsQueue.isEmpty()) {
                        System.out.println("No auctions within 5 seconds. Closing the market.");
                        this.marketManager.endAuction();
                    }
                }

            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Chairman.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("Chairman says good bye.");
        }

    }

}
