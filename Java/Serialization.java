import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serialization {
    public static void main(String ... args) throws FileNotFoundException, IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("oos.dat"));
        TestClass testObj = new TestClass("Easy Pete", 53, TestClass.Sex.MALE);
        oos.writeObject(testObj);
        System.out.println("Вывод сохраненного файла");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("oos.dat"));
        try {
            var x = (TestClass) ois.readObject();
            System.out.println(x);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        oos.close(); ois.close();
        System.out.println("Имя out: " + System.out.getClass().getName());
        System.out.println("Имя in: " + System.in.getClass().getName());
    }
}

class TestClass implements Serializable {
    String name;
    int age;
    enum Sex {
        MALE,
        FEMALE
    }
    Sex sex;
    public TestClass(String name, int age, TestClass.Sex sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return name + " " + age + " " + sex;
    }
}