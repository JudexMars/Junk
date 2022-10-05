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
                    Thread.sleep(1300);
                } catch (InterruptedException e) {
                    // do nothing lol
                }
            }
        };

        var t1 = new Thread(r1);
        var t2 = new Thread(r2);
        
        t1.start();
        t2.start();
    }
}
