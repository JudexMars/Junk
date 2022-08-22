import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.function.Supplier;
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

        var table = new Pair[10]; // can only initialize a raw array of Pair objects
        table[0] = new Pair<Integer>(0, 1); // that's how you can initialize each element
        ps.println(Arrays.toString(table));

        // Because every generic types converts to the raw type during the compilation, you can create arrays of Pair objects both with String and Integer parameters
        var table2 = new Pair[] { new Pair<String>("Blessing", "Curse"), new Pair<Integer>(7, 13) };
        ps.println(Arrays.toString(table2));

        // One of the ways to create arrays of generic types
        ArrayList<Pair<String>> genericList = new ArrayList<>();
        genericList.add(new Pair<String>("Don't say", "a word")); // only Pair<String> can be passed

        printColumn(1, 2, 3, 4);

        // We give our class a reference to the method that can be used to construct a new entity
        Pair<String> constrExample = Pair.makePair(String::new);
        // We give our class a reference to the class - String
        constrExample = Pair.makePair_reflect(String.class);

        String[] names = { "Mary", "Alfred", "Brendon" };
        ps.println(Arrays.toString(minmax(String[]::new, names)));

        // Wildcard allows us to create pairs that can accept both the base class and his subclasses
        Pair<? extends Employee> wildPair;
        wildPair = new Pair<>(new Employee("Jack"), new Employee("Jane")); // OK
        ps.println(wildPair);
        wildPair = new Pair<>(new Manager("Mark"), new Manager("Sandy")); // OK
        ps.println(wildPair);
        wildPair = new Pair<>(new Employee("Morris"), new Manager("Michael")); // OK
        ps.println(wildPair);

    }

    // SafeVarargs annotation tells the compiler that we as a programmer have written a code that won't cause unexpected behaviour with generic types
    @SafeVarargs
    public static <T> void printColumn(T ... args) { 
        for (T x : args) { System.out.println(x); }
     }

     @SuppressWarnings("unchecked")
     public static <T extends Comparable> T[] minmax(IntFunction<T[]> constr, T ... args){
        T[] result = constr.apply(2);
        T min = args[0]; T max = args[0];
        for (T value : args) { if (value.compareTo(min) < 0) min = value; if (value.compareTo(max) > 0) max = value; }
        result[0] = min; result[1] = max;
        return result;
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

    // A way to implement a constructor for a generic class
    // basically it uses a constructor that the programmer has to provide in the main method
    public static <T> Pair<T> makePair(Supplier<T> constr){
        return new Pair<>(constr.get(), constr.get());
    }

    // Another way to make a constructor using a Class reference
    public static <T> Pair<T> makePair_reflect(Class<T> cl){
        try{
            return new Pair<>(cl.getConstructor().newInstance(), cl.getConstructor().newInstance());
        }
        catch (Exception e){
            Logger.getGlobal().warning("reflective makePair method couldn't construct a new entity");
            return null;
        }
    }

    @Override public String toString() { return "[" + first + "; " + second + "]"; }
}

class Employee {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee(String name) { this.name = name; }

    @Override public String toString() { return this.name; }
}

class Manager extends Employee {
    public Manager(String name) { super(name); }
}