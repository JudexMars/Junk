import java.util.Scanner;

public class EnumerationClasses {
    public static void main(String[] args){
        System.out.println("The program is running");
        var in = new Scanner(System.in);
        System.out.println("Please select your size:");
        System.out.println("Small | Medium | Large");
        String choice = in.nextLine().toUpperCase();
        Size size = Size.valueOf(choice);
        System.out.println("You've chosen " + size + " (" + size.getAbbreviation() + ")");
        in.close();
    }

    public enum Size { 
        SMALL("S"), MEDIUM("M"), LARGE("L");
        
        private String abbreviation;

        private Size(String abbreviation){
            this.abbreviation = abbreviation;
        }

        public String getAbbreviation() { return this.abbreviation; }
    }
    
}
