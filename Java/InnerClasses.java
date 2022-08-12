import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;

public class InnerClasses {
    public static void main(String ... strings){
        System.out.println("The program is running");

        KillerQueen k = new KillerQueen(StandDevelopment.WEAK);
        k.useSheerHeartAttack();

        Human drake = new Human();
        Human.Brain b = drake.new Brain(); // we can produce an object of the inner public class
        // although we can create a new brain, we always need to create a human first
        drake.Eat("sandwich");

        //System.out.println(Human.secret.getClass()); doesnt work because the method uses *this* pointer
        Human.message(); // but this works!
    }
}

enum StandDevelopment { WEAK, MEDIUM, STRONG, REQUIEM };

class KillerQueen{
    int hp;
    StandDevelopment level;

    private class SheerHeartAttack{
        void explode(){
            if (level != StandDevelopment.WEAK) System.out.println("Look over here!");
            // same as
            // if (KillerQueen.this.level != StandDevelopment.WEAK) System.out.println("Look over here!");
        }
    }

    public void useSheerHeartAttack(){
        System.out.println("Now you die!");
        SheerHeartAttack sha = new SheerHeartAttack();
        sha.explode();
    }

    public KillerQueen(StandDevelopment level){
        this.level = level;
    }
}

class Human{
    public class Brain{
        public Brain(){
            System.out.println("The Narrator: This human now has a brain.");
        }
    }

    public void Eat(String name){
        // This is basically an anonymous local inner class
        var food = new Consumer<String>() {
            public void accept(String name){
                System.out.println("This " + name + " is so tasty!");
            }
        };

        food.accept(name);
    }

    public static String secret;
    public static void message(){
        System.out.println(new Object(){}.getClass().getEnclosingClass());
    }
}