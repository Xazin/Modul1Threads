package A_Opgaver;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The purpose of Executor04 is to...
 * @author kasper
 */
public class Opgave2 {
    public static void main( String[] args ) {
        //ExecutorService workingJack = Executors.newSingleThreadExecutor();
        ExecutorService workingJack = Executors.newFixedThreadPool( 3 );
        System.out.println( "Main starts" );
        for ( int count = 0; count < 25; count++ ) {
            Runnable task = new MyTaski(count);
            workingJack.submit(task);
        }
        System.out.println( "Main is done" );
        workingJack.shutdown();

    }

}

class MyTaski implements Runnable {

    private int count = 0;
    private int sleepTime = 0;

    private static int hundred = 100;

    MyTaski( int cnt ) {
        sleepTime = (int)(Math.random()*800+200); // At least 200 ms, up to one sec
        count = cnt;
    }

    @Override
    public synchronized void run() {
        try {
            synchronized (MyTaski.class) {
                int myH = hundred;
                Thread.sleep(sleepTime); // simulate some external job taking time
                myH += 100;
                hundred = myH;
            }
            System.out.println( "Task: " + count + ": Hundred = " + hundred);
        } catch ( InterruptedException ex ) {
            System.out.println( "We got interrupted" );
        }
    }
}