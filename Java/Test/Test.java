package Test;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Test{
    public static void main(String ... args) throws UnsupportedEncodingException{
        PrintStream ps = new PrintStream(System.out, true, "Cp866");
        ps.println("Hello World!");
    }
}x