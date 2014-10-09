package exercise3;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyRunnable implements Runnable{

    public void run() {
        
        Thread currentThread = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            System.out.println(Integer.toString(i) + ". " + currentThread.getName());
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(MyRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(currentThread.getName() + " FINISHED");
    }
}
