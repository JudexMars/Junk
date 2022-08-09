import java.util.Objects;

public class Inheritance {
    public static void main(String[] args){
        System.out.println("The program is running");
        Monster goblin = new Monster("aaaGoblin", "Goblin", 5, 20);
        goblin.setDisplayName("Rob");
        System.out.println(goblin.getDisplayName() + " " + goblin.getId() + " " + goblin.getDamage() + " " + goblin.getHp());
        NPC npc = goblin;
        System.out.println(npc.getDisplayName()); // dynamic casting to the derived class (works automatically)
        System.out.println(npc instanceof Monster ? "This is a monster!":"This is not a monster"); // checking what class variable CAN refer to
        NPC simplex = new NPC("aaaSimplex", "Unknown");
        System.out.println(simplex.equals(goblin));   
        System.out.println(simplex.toString());          
        System.out.println(goblin.toString());    
    }
}

class NPC{
    private String id;
    private String displayName;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public NPC(String id, String displayName){
        this.setDisplayName(displayName);
        this.setId(id);
    }

    /**
     * An attempt to override Object's equals method
     */
    @Override public final boolean equals(Object otherObject){
        if (this == otherObject) return true;
        if (otherObject == null) return false;
        if (!(otherObject instanceof NPC)) return false;
        return this.getId() == ((NPC)otherObject).getId();
    }

    /**
     * Generation of a hash-code for an object. It is used in hash-tables.
     */
    @Override public final int hashCode(){
        return Objects.hash(getId());
    }

    /**
     * This method is used to generate readable summary of an object
     */
    @Override public String toString(){
        return getClass().getName() + "[id=" + getId() + ";displayName=" + getDisplayName() + "]";
    }
}

class Monster extends NPC{
    private int damage;
    private int hp;
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getDisplayName(){
        return super.getDisplayName() + " - Monster";
    }

    public Monster(String id, String displayName, int damage, int hp){
        super(id, displayName);
        this.setDamage(damage);
        this.setHp(hp);
    }

    /**
     * Extension of NPC's toString method
     */
    @Override public String toString(){
        return super.toString() + "[damage=" + getDamage() + ";hp=" + getHp() + "]";
    }
}