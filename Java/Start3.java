public class Start3{
    public static void main(String[] args){
        System.out.println("The programm is running");
    }
}

/**
 * {@code NPC} object represents a Non-Playable Character, that can have its own AI, attributes and inventory.
 * <img src="doc-files/twinpeaks.png" width="200" alt="Twin Peaks"/>.
 * @author Danny Brante
 * @version 1.0
 */
class NPC{
    /**
     * This field shows current state of all NPCs' AI.
     */
    public static boolean AI = true;
    /**
     * This field is used to distinguish simillar NPCs one from another.
     */
    public int id;

    private String name;

    private int str;
    private int intel;
    private int dex;
    String inventory;
    /**
     * This method is used to get the inventory of one specific NPC.
     * @return NPC's inventory
     * @see {@link #setInventory(String) Method for setting inventory instead}
     */
    public String getInventory() {
        return inventory;
    }
    /**
     * This method is used to set the inventory of one specific NPC.
     * @param inventory
     * @return Nothing
     * @throws Everything will be ok!
     */
    public void setInventory(String inventory) {
        this.inventory = inventory;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getStr() {
        return str;
    }
    public void setStr(int str) {
        this.str = str;
    }
    public int getIntel() {
        return intel;
    }
    public void setIntel(int intel) {
        this.intel = intel;
    }
    public int getDex() {
        return dex;
    }
    public void setDex(int dex) {
        this.dex = dex;
    }

}