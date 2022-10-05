package Concurrency;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockAndCondition {
    public static void main(String ... args) throws UnsupportedEncodingException{
        PrintStream ps = new PrintStream(System.out, true, "Cp866");
        ps.println("The program is running");
        LockExample o = new LockExample();
        for(int i = 0; i < 5; i++){
            var t1 = new Thread(()->{
                try {
                    Thread.sleep(3000);
                    o.doSomething();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t1.start();
        }

        var t2 = new Thread(()->{
            try {
                o.printValue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t2.start();

        Scanner in = new Scanner(System.in);
        int value = in.nextInt();
        o.setValue(value);
        in.close();
    }
}

class LockExample{
    // Used for locking current thread
    private ReentrantLock lock = new ReentrantLock();

    private int value = 0;
    private Condition valueIsPresent = lock.newCondition();

    void doSomething(){
        lock.lock();
        try {
            System.out.println("Thread: " + Thread.currentThread().getName());
        } catch (Exception e) {
            System.err.println("Something is wrong");
        }
        finally{
            lock.unlock();
        }
    }

    void printValue() throws InterruptedException {
        lock.lock();
        try {
            while(value == 0) valueIsPresent.await();
            System.out.println("Contained value is: " + value);
        } finally {
            lock.unlock();
        } 
    }
    
    void setValue(int value){
        lock.lock();
        try {
            if (value > 0) this.value = value;
            valueIsPresent.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
