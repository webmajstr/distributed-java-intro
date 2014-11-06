/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozga.fleamarket.people;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Ozga
 */
public class MarketManager {
    private List<Donor> donors;
    private List<Recipient> recipients;
    private ExecutorService executors;

    public MarketManager() {
        donors = new ArrayList();
        recipients = new ArrayList();
        executors = Executors.newCachedThreadPool();
    }
    
    public void addDonor(Donor donor) {
        donors.add(donor);
    }
    
    public void addRecipient(Recipient recipient) {
        recipients.add(recipient);
    }
    
    public void startAuction() {
        for (int i = 0, size = donors.size(); i < donors.size(); i++) {
            executors.execute(donors.get(i));
        }
        for (int i = 0, size = recipients.size(); i < recipients.size(); i++) {
            executors.execute(recipients.get(i));
        }
       // executors.shutdown();
      //  executors.awaitTermination(30, TimeUnit.SECONDS);
    }
    
    public void endAuction() {
        executors.shutdown();
        
    }
    
    
    
}
