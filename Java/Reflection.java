import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.*;
import java.util.*;

public class Reflection {
    public static void main(String ... args) 
    throws ClassNotFoundException, 
    InstantiationException, 
    IllegalAccessException, 
    IllegalArgumentException, 
    InvocationTargetException, 
    NoSuchMethodException,
    NoSuchFieldException {
        System.out.println("The programm is running");
        Human h = new Human("John", 27);
        // Reflection begins
        Class cl = h.getClass();
        System.out.println("Class name: " + cl.getName() + "; Object name: " + h.getName());

        String className = "java.util.Random";
        cl = Class.forName(className);
        Object rng = cl.getConstructor().newInstance();
        var method = cl.getMethod("nextInt");
        System.out.println("Random int = " + method.invoke(rng));
        var constr = cl.getDeclaredConstructors();
        var meth = cl.getDeclaredMethods();
        var fld = cl.getDeclaredFields();

        // Getting all information about the selected class

        System.out.print(Modifier.toString(cl.getModifiers()));
        System.out.print(" class " + cl.getName() + "\n{\n");

        for (var c : constr){
            System.out.println("    " + c.toString());
        }
        System.out.println();
        for (var m : meth){
            System.out.println("    " + m.toString());
        }
        System.out.println();
        for (var f : fld){
            System.out.println("    " + f.toString());
        }

        System.out.println("}");

        // Now we will try to find out the value of some field in dynamic runtime and also invoke a method

        cl = Class.forName("Human");
        var human = cl.getConstructor(String.class, int.class).newInstance("Jauffree", 20);
        Field field = cl.getDeclaredField("id");
        method = cl.getDeclaredMethod("getName");
        System.out.print("\n\nHuman named " + method.invoke(human) + " has the id: " + field.get(human) + "\n");

        // Illegal access to a private field

        Field[] fields = cl.getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);
        System.out.println("Illegal access to the name field: " + fields[2].getName() + " = " + fields[2].get(human));

        // Using reflection to create generic methods

        var nums = new int[] {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(((int[])HandyTool.copyArrayGold(nums, 2))));
        System.out.println(Arrays.toString(((int[])HandyTool.copyArrayGold(nums, 10))));
    }
}

class Human{
    private static int nextId;

    static{
        nextId = 0;
    }

    public int id;

    private String name;
    private int age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Human(String name, int age){
        this.setName(name);
        this.setAge(age);
        id = nextId;
        nextId++;
    }
}       

class HandyTool{
    /**
     * A shitty way of copying an array
     * @param arr old array
     * @param newLength how long or short the new array has to be
     * @return new array with a different length but same elements (of Object type)
     */
    @Deprecated public static Object[] copyArray(Object[] arr, int newLength){
        Object[] newArr = new Object[newLength];
        System.arraycopy(arr, 0, newArr, 0, Math.min(arr.length, newLength));
        return newArr;
    }
    /**
     * A golden way of copying an array
     * @param arr old array
     * @param newLength how long or short the new array has to be
     * @return new array with a different length but same elements (of the type convertible to the source type)
     */
    public static Object copyArrayGold(Object arr, int newLength){
        Class cl = arr.getClass();
        if (!cl.isArray()) return null;
        Class componentType = cl.getComponentType();
        int length = Array.getLength(arr);
        Object newArr = Array.newInstance(componentType, newLength);
        System.arraycopy(arr, 0, newArr, 0, Math.min(length, newLength));
        return newArr;
    }
}