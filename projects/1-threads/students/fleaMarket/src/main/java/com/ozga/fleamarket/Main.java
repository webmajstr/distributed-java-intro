/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozga.fleamarket;

import com.ozga.fleamarket.people.Chairman;
import com.ozga.fleamarket.people.Donor;
import com.ozga.fleamarket.people.MarketManager;
import com.ozga.fleamarket.people.Recipient;
import com.ozga.fleamarket.queues.ItemsQueue;

/**
 *
 * @author Ozga
 */
public class Main {
    public static void main(String[] args) {
        ItemsQueue queue = new ItemsQueue();
        MarketManager manager = new MarketManager();
        Chairman chairman = new Chairman(queue, manager);
        
        manager.addRecipient(new Recipient("Recipient 1", chairman));
        manager.addRecipient(new Recipient("Recipient 2", chairman));
        manager.addRecipient(new Recipient("Recipient 3", chairman));
        
        manager.addDonor(new Donor("donor 1", chairman));
        manager.addDonor(new Donor("donor 2", chairman));
        manager.addDonor(new Donor("donor 3", chairman));
        
        manager.startAuction();
        

        
    }
    
    
}
