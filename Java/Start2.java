import npcs.*;

public class Start2{
    public static void main(String[] args){
        System.out.println("The programm has been succesfully launched");

        Animal animal = new Animal();
        Human human = new Human("John", 29, Human.Sex.Male);
        Group group = new Group(5);
    }
}