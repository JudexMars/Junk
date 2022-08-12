import java.util.Comparator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.print.attribute.IntegerSyntax;
import javax.swing.*;
import javax.swing.Timer;
import java.util.function.*;
import java.util.stream.*;
import java.util.List;

/**
 * This class demonstrates a work of the lambda expressions. Lambda expressions are used to
 * create unnamed blocks of codes that usually are passed to other functions.
 * @author Danny Brante
 * @version 1.0
 */
public class Lambda {
    public static void main(String[] args){
        System.out.println("The program is running");

        // Sorting by the word's length

        Comparator<String> lengthComparator = (String first, String second) -> {
            return first.length() - second.length();
        };

        String[] dict = {"Monkey", "Fly", "Elephant"};
        Arrays.sort(dict, lengthComparator);
        System.out.println(Arrays.toString(dict));

        // Realization of a timer

        // in the line below event is the single parameter to the lambda expression that is not used
        /* var timer = new Timer(1000, event -> System.out.println("Current time: " + new Date())); // Swing framework
        timer.start();

        JOptionPane.showMessageDialog(null, "Stop it?"); // Swing framework
        System.exit(0); */

        // Predicate

        var nums = new ArrayList<Integer>();
        nums.add(0); nums.add(1); nums.add(2); nums.add(3); nums.add(4);
        Predicate<Integer> condition = x -> {
            if (x % 2 == 0) return true;
            return false;
        };
        nums.removeIf(condition);
        System.out.println(nums.toString());

        // Method references

        /* var timer = new Timer(1000, System.out::println);
        timer.start();
        JOptionPane.showMessageDialog(null, "Hey you!");
        System.exit(0); */

        Arrays.sort(dict, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(dict));

        // This feature allows us to invoke other functions in exotic ways
        // it's basically a function-pointer from C++
        BiFunction<Integer, Integer, Integer> bi = (Integer x, Integer y) -> {
            return x + y;
        };

        System.out.println("Result of the bifunction: " + bi.apply(1, 9));

        // More method references
        Function<String, String> trim = String::trim;
        String testLine = "word       "; // testLine has a lot of useless spaces
        System.out.println(trim.apply(testLine).length()); // this code deletes all spaces following the word

        // A constructor reference
        Function<Integer, ArrayList<Integer>> kop = ArrayList<Integer>::new;
        var kopList = kop.apply(5);
        kopList.add(325);
        System.out.println(kopList.toString());   

        ArrayList<String> names = new ArrayList<>(Arrays.asList("John", "Ivan", "Ahmed"));
        Stream<Person> stream = names.stream().map(Person::new);
        List<Person> people = stream.collect(Collectors.toList());
        System.out.println(people.toString());

        // This form of functional interface allows us to use simple primitive types
        // instead of the wrappers (PConsumer, PPredicate, etc.)
        // It is better to use these methods whenever you can
        IntConsumer intConsumer = (int x) -> System.out.println(x);
        intConsumer.accept(250);

        // Here we compare a lot of objects by refering to their names
        people.sort(Comparator.comparing(Person::getName)); // works just fine
        System.out.println(people.toString());

        // This code sorts the array, firstly, by the person's length of name and, secondly, in alphabetical order
        people.sort(Comparator.comparing(Person::getName, (s, t) -> Integer.compare(s.length(), t.length())).thenComparing(Person::getName));
        System.out.println(people.toString());
    }
}

/**
 * This is the equivalent of the lambda expression that we wrote in the main method.
 * To use we just have to create an object of Comparator<String> and allocate new memory for the LengthComparator
 */
class LengthComparator implements Comparator<String>{
    public int compare(String first, String second){
        return first.length() - second.length();
    }
}

class Person{
    private String name;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Person(String name) { this.setName(name); }

    @Override public String toString(){
        return this.getClass().getName() + ": " + this.getName();
    }
}