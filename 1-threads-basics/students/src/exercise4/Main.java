package exercise4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args){

        ExecutorService executor = new ScheduledThreadPoolExecutor(4, Executors.defaultThreadFactory());
        
        for (int i = 0; i < 4; i++) {
            executor.execute(new MyRunnable());
        }
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("FINISHED (after showdown)");
        
        
    
    }
}
