package dat.sem2.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Executor02 {

    public static void main( String[] args ) {
        ExecutorService workingJack = Executors.newSingleThreadExecutor();
        for ( int count = 0; count < 25; count++ ) {
            int finalCount = count;

            workingJack.submit( () -> {
                System.out.println( "Hello " + finalCount + " to us" );
            } );
        }
        workingJack.shutdown();
    }
}
