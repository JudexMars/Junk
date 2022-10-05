package Concurrency;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class SynchronizedKeyword {
    public static void main(String ... args) throws UnsupportedEncodingException{
        PrintStream ps = new PrintStream(System.out, true, "Cp866");
        
        var o = new ValueKeeper();
        var t1 = new Thread(()->{
             try {
                o.printValue();
            } catch (InterruptedException e) {            
                e.printStackTrace();
            }
        });

        t1.start();
        try (Scanner in = new Scanner(System.in)) {
            ps.println("Enter any value that is bigger than zero");
            var value = in.nextInt();
            o.setValue(value);
        }
    }
}

class ValueKeeper{

    private int value = 0;

    public synchronized void printValue() throws InterruptedException{
        while(value == 0) wait();
        System.out.println("Value: " + this.value);
    }

    public synchronized void setValue(int value){
        this.value = value;
        notifyAll();
    }
}