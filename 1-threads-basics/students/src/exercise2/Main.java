package exercise2;

import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        ArrayList<Thread> threads = new ArrayList();
        for (int i = 0; i<4; i++) {
            threads.add(new MyThread("Thread-" + i));
        }
        
        Iterator iter = threads.iterator();
        
        while (iter.hasNext()) {
            Thread tmp = (MyThread)iter.next();
            tmp.start();
        }
    }
}
