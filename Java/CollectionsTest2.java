import java.util.*;
import java.io.*;

public class CollectionsTest2 {
    public static void main(String[] args)
    throws UnsupportedEncodingException
    {
        PrintStream ps = new PrintStream(System.out, true, "Cp866");
        ps.println("The program is running");
        
        // Collection<E> is the root interface for all collections
        Collection<String> names = new ArrayList<>(5);
        names.add("Andy"); names.add("John"); names.add("Karen");
        names.removeIf(o -> o.equals("John")); // one of the methods of that interface
        ps.println(names.toString());

        // Iterators' logic is like in C++
        Iterator<String> it = names.iterator();
        while (it.hasNext()) ps.println(it.next());
        names.iterator().forEachRemaining(ps::println); // same result

        // HashMap is the default implementation of the Map interface
        Map<Integer, String> group = new HashMap<>();
        group.put(0, "Guitar");
        group.put(1, "Banjo");
        ps.println(group.toString());
    }
}