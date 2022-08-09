import java.util.ArrayList;

public class Generics {
    public static void main(String[] args){
        System.out.println("The program is running");
        var list = new ArrayList<Integer>(); // Analogue of Vector class from C++
        list.ensureCapacity(5); // sets mimimum capacity
        for (int i = 0; i < 5; i++){
            list.add(i);
        }
        list.set(0, 100);
        list.add(2, 300);
        list.remove(2);

        list.trimToSize(); // reduces the storage capacity of the array list to its current size.
        System.out.println(list + " Element no." + 2 + " = " + list.get(2));

        ArrayList raw = new ArrayList(); // raw ArrayList with no type

        raw.add(1); 

        @SuppressWarnings("unchecked") ArrayList<Integer> ref = raw;
        System.out.println(ref);

        printNums(1, 2, 3, 4, 5);
    }

    /**
     * This method shows a usage of args array
     * @param ints any numbers that have to be printed
     */
    static void printNums(int ... ints){
        for (var e : ints){
            System.out.println("Number " + e);
        }
    }
}
