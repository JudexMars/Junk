package npcs;
import java.util.Objects;
import java.util.Scanner;
public class Human{
    static int population = 0;

    private int id;
    private String name;
    private int age;
    public enum Sex {Male, Female}
    private Sex sex;

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
    public Sex getSex(){
        return sex;
    }
    public void setSex(Sex sex){
        this.sex = sex;
    }
    public Human(String name, int age, Sex sex){
        id = population;
        population++;
        this.name = Objects.requireNonNullElse(name, "Unknown");
        this.age = age;
        this.sex = sex; 
    }

    // unit-test
    public static void main(String[] args){
        System.out.println("Class Human. Unit-test.\n");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        int age = Integer.parseInt(in.nextLine());
        Human.Sex sex;
        String sexStr = in.nextLine();
        if (sexStr == "m") sex = Human.Sex.Male;
        else if (sexStr == "f") sex = Human.Sex.Female;
        else sex = Human.Sex.Male;
        Human x = new Human(name, age, sex);
        System.out.println("Name: " + x.getName() + " Age: " + x.getAge() + " Sex: " + x.getSex());
        in.close();
    }
}