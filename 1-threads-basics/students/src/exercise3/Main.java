package exercise3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args){

        ArrayList<Thread> threads = new ArrayList();
        for (int i = 0; i < 4; i++) {
            threads.add(new Thread(new MyRunnable(), "Thread-" + i));
        }

        Iterator iter = threads.iterator();

        while (iter.hasNext()) {
            Thread tmp = (Thread) iter.next();
            tmp.start();
        }
        
        iter = threads.iterator();

        while (iter.hasNext()) {
            Thread tmp = (Thread) iter.next();
            try {
                tmp.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        int alive = 4;
        while (alive != 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            iter = threads.iterator();
            alive = 4;
            while (iter.hasNext()) {
                Thread tmp = (Thread) iter.next();
                if (!tmp.isAlive()) {
                    alive--;
                }
            }
        }
        
        System.out.println("All Threads FINISHED");
    }
}
