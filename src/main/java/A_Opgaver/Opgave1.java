package A_Opgaver;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 The purpose of Executor03 is to...

 @author kasper
 */
public class Opgave1 {

    public static void main( String[] args ) {
        ExecutorService workingJack = Executors.newFixedThreadPool(1);
        System.out.println( "AAA - ZZZ:" );

        int firstChar = 65;
        int endChar = 90;

        for ( int count = 65; count <= endChar; count++ ) {
            Runnable task = new MyTask1( count );
            workingJack.submit( task );
        }
        System.out.println( "Main is done" );
        workingJack.shutdown();
    }

}

class MyTask1 implements Runnable {

    private int count = 0;

    MyTask1( int cnt ) {
        count = cnt;
    }

    @Override
    public synchronized void run() {
        char s = (char) count;
        System.out.printf("%s%s%s\n", s, s, s);
    }
}
