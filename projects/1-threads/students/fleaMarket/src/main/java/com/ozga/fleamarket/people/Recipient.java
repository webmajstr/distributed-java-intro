/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozga.fleamarket.people;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ozga
 */
public class Recipient implements Runnable{
    private final String name;
    private final List<String> items;
    //auction

    public Recipient(String name) {
        this.name = name;
        this.items = new ArrayList();
    }
    
    public void addItem(String item) {
        items.add(item);
    }
    
    
    @Override
    public void run() {
        Random generator = new Random();
        try {
            Thread.sleep(500 * (generator.nextInt(10) + 1));
            
            
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Recipient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println(this.name + " says good bye leaving with items " + this.items.toString());
        }
    }
    
    
}
