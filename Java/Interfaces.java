import java.util.*;

public class Interfaces {
    public static void main(String[] args) {
        System.out.println("The program is running");
        Creature John = new Person(); // we can make a reference to the class that implements this interface
        ((Person)John).setName("John");
        John.Eat(); // invoke a method that has to be provided in the class
        if (John instanceof Comparable) System.out.println("Do we really compare people by their age?");
        Predator wolf = new Wolf();
        wolf.Attack(John);

        var anotherHuman = ((Person)John).clone(); // use our implementation of clone() method
        System.out.println("My name is " + ((Person)anotherHuman).getName());

        John.Sleep();

        // Comparator test

        var friends = new String[] {"John", "Ted", "Marty"};
        Arrays.sort(friends, new LengthComparator()); // sorting by each word's length
        System.out.println(Arrays.toString(friends));
    }
}

/**
 * This is an abstract class which means that objects of this class cannot be instantiated. Its subclasses have to implement bodies
 * for every abstract method declared in this superclass or they won't be able to be instantiated as well.
 */
abstract class LivingBeing{
    public abstract void Sleep();
    public abstract void Eat();
    public abstract void Drink();
}

/**
 * This is an interface which means that every class that is supposed to implement is required to have this particular methods
 *  (and/or fields)
 */
interface Creature{
    /**
     * The default body is used if the class doesn't implements it
     */
    public default void Sleep(){
        System.out.println("I'm gonna... I'm gonna sleep! I'm sleeping!");
    }
    public void Eat();
    public void Drink();
}

interface Predator extends Creature{
    void Attack(Creature creature);
}

class Person implements Creature, Comparable<Person>, Cloneable {
    private String name;
    private int age;

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
      this.age = age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void Eat(){
        System.out.println("Yummy!");
    }
    public void Drink(){
        System.out.println("I'm drunk now");
    }

    public final int compareTo(Person person){
        return Integer.compare(this.getAge(), person.getAge());
    }

    public Object clone() {
        var obj = new Person();
        obj.setAge(this.getAge());
        obj.setName(this.getName());
        return obj;
    }

    public Person(){
        this.name = "Unknown";
        this.age = 20;
    }

    public Person(String name, int age){
        this.setName(name);
        this.setAge(age);
    }
}

/**
 * This thing is used to define by which criteria you are going to compare two objects
 */
class LengthComparator implements Comparator<String>{
    public int compare(String first, String second){
        return Integer.compare(first.length(), second.length());
        // or just return first.length() - second.length()
    }
}

class Wolf implements Predator{
    public void Sleep(){
        System.out.println("Zzzzz");
    }
    public void Eat(){
        System.out.println("OMNOMNOMNOM");
    }
    public void Drink(){
        System.out.println("*slurp*");
    }
    public void Attack(Creature creature){
        System.out.println("The wolf is barking violently at " + creature.getClass().getName());
    }
}