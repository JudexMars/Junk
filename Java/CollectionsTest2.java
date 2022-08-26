import java.util.*;
import java.util.logging.Logger;
import java.io.*;
import java.util.Hashtable;
import java.util.Enumeration;

public class CollectionsTest2 {
    public static void main(String[] args)
    throws UnsupportedEncodingException, FileNotFoundException
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
        group.forEach((k, v) -> {ps.println("key: " + k + "; value: " + v);});

        // LinkedList
        LinkedList<String> train = new LinkedList<>();
        train.add("Doctor"); train.add("Programmer"); train.add("Engineer");
        train.add(1, "Gardener");
        ListIterator<String> link = train.listIterator();
        link.next(); link.remove();
        ps.println(train.toString()); 

        // TreeSet automatically sorts all elements. Duplicates are not allowed just like in usual set
        TreeSet<String> letters = new TreeSet<>();
        letters.add("C"); letters.add("A"); letters.add("B");
        ps.println(letters.toString());

        // Deque
        Deque<String> dixie = new ArrayDeque<>();
        dixie.add("Alpha"); // adds to the end
        dixie.add("Beta");
        dixie.push("Charlie"); // pushes to the beginning
        ps.println(dixie.toString());
        dixie.poll(); // polls the first element (head)
        dixie.pollLast(); // polls the last element (tail)
        ps.println(dixie);

        // PriorityQueue
        Comparator<String> lengthComparator = (String first, String second) -> { return first.length() - second.length(); } ;
        PriorityQueue<String> schedule = new PriorityQueue<>(lengthComparator.thenComparing(String::compareTo));
        schedule.add("Dio");
        schedule.add("Kira");
        schedule.add("Diavolo");
        schedule.add("Pucci");
        ps.println("\nPriority Queue:\n");
        while (!schedule.isEmpty()) ps.print(schedule.remove() + " ");
        ps.println();

        // Sublists
        List<String> simpleList = List.of("Car", "Pen", "Merge");
        ArrayList<String> simpleArray = new ArrayList<>(simpleList);
        List<String> subList = simpleArray.subList(0, 1);
        ps.println(subList.toString());
        subList.clear();
        ps.println(simpleArray.toString());

        TreeSet<String> simpleSet = new TreeSet<>(Set.of("Tree", "Rock", "Car", "Mountain"));
        SortedSet<String> subSet = simpleSet.subSet("Car", "Tree");
        ps.println("\n" + subSet.toString());
        ps.println(simpleSet.tailSet("Rock").toString());

        // Checked views
        ArrayList<String> anotherArray = new ArrayList<>(List.of("Squirrel", "Cow"));
        List<String> checkedView = Collections.checkedList(anotherArray, String.class);
        List rawList = checkedView;
        rawList.add("Horse");
        // rawList.add(new DangerousClass()); // throws a ClassCastException

        // Algorithms
        ArrayList<Integer> sortTest = new ArrayList<>(List.of(99, 4, 2323, 11, 14, 1));
        Collections.sort(sortTest);
        ps.println("\nAlgorithms:\n" + sortTest.toString());
        ArrayList<Integer> searchTest = sortTest;

        ps.println("Number 11 is at the position: " + Collections.binarySearch(searchTest, 11));
        ps.println("Number 29 must be inserted at the position: " + (-Collections.binarySearch(searchTest, 29)-1));
        searchTest.add(-Collections.binarySearch(searchTest, 29)-1, 29);
        ps.println("New array: " + searchTest.toString());

        var setTest = new HashSet<>(Set.of(1, 2, 3, 4, 5));
        setTest.removeAll(Set.of(3,5));
        ps.println("Redacted set: " + setTest.toString());
        setTest.removeIf(x -> { if (x % 2 == 0) return true; else return false; });
        ps.println("Redacted set: " + setTest.toString());

        // Array-List conversion
        Integer[] values = new Integer[] {1, 2, 3, 4, 5};
        ps.println("Array: " + Arrays.toString(values));
        var valList = new ArrayList<Integer>(Arrays.asList(values));
        
        ps.println("Array to list: " + valList);
        ArrayList<Integer> valuesList = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        Integer[] valuesI;
        valuesI = valuesList.toArray(new Integer[valuesList.size()]); // int cannot be used
        ps.println("List to array: " + Arrays.toString(valuesI));

        // Properties
        Properties props = new Properties();
        props.setProperty("SafeMode", "True");
        props.setProperty("HiRes-Textures", "False");
        FileOutputStream out = new FileOutputStream("config.properties");
        //props.store(out, null);
    }
}

class DangerousClass{
    public void info(){
        Logger.getGlobal().info("This enitity exists");
    }
}