import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.util.logging.*;
import java.util.*;

public class GenericClasses {
    public static void main(String[] args) throws UnsupportedEncodingException {
        PrintStream ps = new PrintStream(System.out, true, "Cp866");

        // anonymous generic subclass
        ArrayList<String> list = new ArrayList<>(){
            public void trimToSize(){
                Logger.getGlobal().info("This list isn't meant to be trimmed");
            }
            public String get(int n){
                return "String " + n + ": " + super.get(n);
            }
        };

        list.add("The world");

        list.trimToSize();
        ps.println(list.get(0));

        // Generic class

        Pair<String> pair = new Pair<>("Marko","Polo");
        ps.println("First: " + pair.getFirst() + "\nSecond: " + pair.getSecond());

        printColumn(1, 2, 3, 4);
    }

    public static <T> void printColumn(T ... args) { 
        for (T x : args) { System.out.println(x); }
     }
}

class Pair<T>{
    private T first;
    private T second;

    public T getFirst() { return first; }
    public T getSecond() {return second; }

    public void setFirst(T value) { first = value; }
    public void setSecond(T value) { second = value; }

    public Pair() { first = null; second = null; }
    public Pair(T first, T second) { this.first = first; this.second = second; }
}