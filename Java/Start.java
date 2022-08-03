import java.util.Scanner;
import java.io.*;
import java.util.Date;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

class Start{
    public static void main(String[] args) throws IOException {
        Scanner f = new Scanner(Path.of("myfile.txt"), StandardCharsets.UTF_8);
        String info = f.nextLine();
        System.out.println(info);

        ext:
        while(true){
            int i = 0;
            while(true){
                i++;
                if (i == 5) System.out.println("break"); break ext;
            }
        }
        System.out.println("Left the cycle");

        int[] a = {1, 2, 3, 4, 5};
        int[] b = Arrays.copyOf(a, a.length);
        int[] c = a;
        c[0] = 99;
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(c));
        System.out.println(Arrays.toString(a));

        if (args.length > 0 && args[0].equals("-h")) {
            System.out.println("Hello!");
        }

        Human simplex = new Human("Jack", 37, Human.Sex.Male);
        System.out.println(simplex.getName() + " " + simplex.getAge() + " " + simplex.getSex());
    }
}

class Human{
    private String name;
    private int age;
    enum Sex {Male, Female}
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
        this.name = name;
        this.age = age;
        this.sex = sex; 
    }
}