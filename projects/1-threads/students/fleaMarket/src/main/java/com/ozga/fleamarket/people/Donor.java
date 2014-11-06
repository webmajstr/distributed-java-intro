/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozga.fleamarket.people;

import com.ozga.fleamarket.queues.ItemsQueue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ozga
 */
public class Donor implements Runnable {

    private final String name;
    private final ItemsQueue queue;

    public Donor(String name, ItemsQueue queue) {
        this.name = name;
        this.queue = queue;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        Random generator = new Random();
        String itemId;
        // while (true) {
        try {
            while (true) {
                Thread.sleep(1000 * (generator.nextInt(26) + 5));
                while (queue.isFull()) {
                    Thread.sleep(500 * (generator.nextInt(10) + 1));
                }

                itemId = queue.enqueueItem();
                System.out.println("Donor " + this.name + " enqueued item " + itemId);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Donor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println(this.name + " says good bye.");
        }
        // }
    }

}
