package npcs;

public class Group{
    private Human[] group;

    public Group(int size){
        group = new Human[size];
        for (int i = 0; i < group.length; i++){
            group[i] = new Human("Human", (int)(Math.random() * 70), (int)(Math.random() * 2) == 0 ? Human.Sex.Female : Human.Sex.Male);
        }
    }

    public Human getHuman(int index){
        if (index >= 0 && index < group.length){
            return new Human(group[index].getName(), group[index].getAge(), group[index].getSex());
        }
        else return null;
    }
}