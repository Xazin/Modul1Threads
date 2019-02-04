package A_Opgaver;

import sun.misc.Lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Opgave3 {

    public static void main( String[] args ) {
        ExecutorService workingJack = Executors.newFixedThreadPool( 17 );
        System.out.println( "Main starts" );
        for ( int count = 0; count < 100; count++ ) {
            Runnable task = new MyTask111( count );
            workingJack.submit( task );
        }
        System.out.println( "Main is done" );
        workingJack.shutdown();
    }

}

class MyTask111 implements Runnable {
    private ReentrantLock lock = new ReentrantLock();
    private Lock lock1 = new Lock();

    private int count = 0;
    private int sleepTime = 0;

    private static List<Integer> list = new ArrayList<>();

    MyTask111( int cnt ) {
        sleepTime = (int) ( Math.random() * 800 + 200 ); // At least 200 ms, up to one sec
        count = cnt;
    }

    @Override
    public void run() {
        try {
            lock1.lock();
            try {
                list.add(count);
                System.out.println("Task: " + count + ": List size = " + list.size());
            } finally {
                lock1.unlock();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock1.unlock();
        }
    }
}
