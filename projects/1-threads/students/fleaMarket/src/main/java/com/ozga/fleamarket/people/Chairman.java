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

    private ItemsQueue itemsQueue;
    private MarketManager marketManager;
    private BlockingQueue<Recipient> recipients;

    public Chairman(ItemsQueue itemsQueue, MarketManager marketManager) {
        this.itemsQueue = itemsQueue;
        this.marketManager = marketManager;
        this.recipients = new ArrayBlockingQueue<>(10);
    }

    public boolean register(Recipient recipient) {
        try {
            return this.recipients.add(recipient);
        } catch (IllegalStateException e) {
            return false;
        }
    }
    
    private Recipient finishAuction() {
        int length = this.recipients.size();
        Random generator = new Random();
        int winnerId = generator.nextInt(length);
        Recipient winner = null;
        for (int i = 0; i < length; i++) {
            if (i == winnerId) {
                winner = this.recipients.poll();   
            } else {
                this.recipients.poll();
            }
        }
        try {
            winner.addItem(this.itemsQueue.dequeueItem());
        } catch (InterruptedException ex) {
            Logger.getLogger(Chairman.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return winner;
         
    }

    @Override
    public void run() {
        Random generator = new Random();

        try {
            if (itemsQueue.isEmpty()) {
                Thread.sleep(500 * (generator.nextInt(10) + 1));
                if (itemsQueue.isEmpty()) {
                    this.marketManager.endAuction();
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Chairman.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
