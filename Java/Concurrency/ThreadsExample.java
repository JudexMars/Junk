package Concurrency;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class ThreadsExample {
    public static void main(String ... args) throws UnsupportedEncodingException{
        PrintStream ps = new PrintStream(System.out, true, "Cp866");

        ps.println("The program is running");
        Runnable r1 = () -> { 
            for(int i = 0; i < 10; i++){
                ps.println(Thread.currentThread());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // do nothing lol
                }
            }

        };
        Runnable r2 = () -> { 
            for(int i = 0; i< 10; i++){
                ps.println(Thread.currentThread()); 
                try {
                    if (i == 5) Thread.currentThread().interrupt();
                    else Thread.sleep(1300);
                } catch (InterruptedException e) {
                    ps.println("Thread Interrupted");
                    break;
                }
            }
        };

        var t1 = new Thread(r1);
        var t2 = new Thread(r2);
        
        //t1.start();
        //t2.start();

        // Daemon Threads
        // The VM will stop execution if the only remaining threads are daemon
        // Basically Daemon threads exist to provide support for other threads
        // There is no point in keeping them once the main ones are over
        Runnable r3 = () -> { 
            for(int i = 0; i < 5; i++){
                try {
                    ps.println("Daemon thread test: " + i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        var t3 = new Thread(r3);
        t3.setDaemon(true); // True - VM will exit immediately after other normal threads are done, False - VM will execute the thread as usual
        t3.start();

        // Any thread can have a custom name
        // Obviously it is utilized for more convenient debugging

        var t4 = new Thread(() -> {
            ps.println(Thread.currentThread());
            int[] a = {1, 2, 3};
            ps.println(a[3]);
        });
        t4.setName("Тред с необычным именем");
        // test of a custom exception handler
        t4.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        t4.start();

        // test of a default exception handler
        var t5 = new Thread(()->{
            try {
                Thread.sleep(1000);
                int[] a = {0};
                ps.println(a[99]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t5.start();
    }
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        System.out.println("Exception Handler has been activated");
        //t.interrupt();           
    }
}
