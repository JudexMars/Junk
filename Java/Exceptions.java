import java.util.Scanner;
import java.io.*;
import java.nio.charset.*;
import java.util.logging.*;

import com.sun.tools.javac.Main;



public class Exceptions {
    public static void main(String[] args) 
    throws UnsupportedEncodingException,
    IOException
    {
/*         var array = new int[5];
        readArray(array, -1); */

       /*  // The code below demonstrates many uses of try-catch formula
        try {
            Human man = new Human("", false);
            Headcrab crab = new Headcrab();
            crab.takeControl(man);
        }
        catch (HeadProtectionDetected e){
            System.out.println("This guy has a damn helmet");
            e.printStackTrace(); // this method prints a name of the exception and also tells us where the exception was initially thrown
        }
        catch (NoName e){
            System.out.println("This guy doesn't really exist");
            e.printStackTrace();
        }

        try{
            Human rick = new Human("Rick", true);
             new Headcrab().takeControl(rick);
        }
        catch (NoName | HeadProtectionDetected e){
            System.out.println("This guy either has a helmet or doesn't have a name");
            e.printStackTrace();
        }

        try{
            Human rick = new Human();
             new Headcrab().takeControl(rick);
        }
        catch (NoName | HeadProtectionDetected e){
            System.out.println("This guy either has a helmet or doesn't have a name");
            e.printStackTrace();
        } */

        // Wrapping of original cause of exception into a different exception

        try{
            Human rick = new Human();
            Headcrab crab = new Headcrab();
            infest(rick, crab);
        }
        catch(Exception e){
            System.out.println("Something went wrong!");
            System.out.println("The real cause: " + e.getCause().getClass().getName());
            e.printStackTrace(); // Default stack trace
            e.getCause().printStackTrace(); // Original stack trace
            // There's no need to get the original cause before printing because
            // printStackTrace() method is already made to write all original causes
        }
        finally{
            System.out.println("This block of code is invoked whether or not an exception was called");
            // Programmers often use it to clean the program of resources that are no longer needed.
            // It doesn't matter if an exception was called because, for example, any file should be closed
            // after it served its purpose.
        }

        // There is a more modern and easier way to clean up resources
        // it's called try-with-resources
        try {
            upperCaseRead("exceptionTest\\original.txt", "exceptionTest\\result.txt");
        }
        catch (IOException e){
            System.out.println("You messed up the paths");
            e.printStackTrace();
        }

        // Stack walker is the tool to trace calls of a function
        System.out.println("\n\n\nStack Walker:\n");
        factorial(5);

        // Assertions are a mechanism that is used by programmers to check their own code for mistakes
        // that shouldn't be fixed in runtime but instead prevented at the development stage
        // for example, your method's parameters must never be null because of the overall program logic
        // but if they do happen to be null it means that your code has bug and you should better
        // stop the program before such mistakes lead to unforeseen consequences

        /* try (Scanner in = new Scanner(System.in)){
            int x = Integer.parseInt(in.nextLine());
            assert x > 0 : "Math is not real"; // Not a good example for real-life situation but helps to understand the syntax
        } */
        // to activate assertions you have to pass -ea parameter when running your program with a java command


        // Logging is a flexible tool to debug code. It allows a programmer not only to monitor all the necessary
        // info about program's status but also to easily disable every log if he needs to (before finishing the project)
        Logger.getGlobal().setLevel(Level.ALL);
        Logger.getGlobal().info("Logging section has been reached");
        Logger.getGlobal().setLevel(Level.OFF);
        Logger.getGlobal().info("This text is not visible");

        // To make this work we have to configure our logger to print logs that are higher level than INFO
        var logger = Logger.getGlobal();
        logger.setLevel(Level.ALL);
        logger.throwing("Exceptions", "main", new Exception("Logger throwing test"));

        // Localization test

        PrintStream ps = new PrintStream(System.out, true, "cp866");
        ps.println("Русский текст");

        // Preparing a logger
        logger = Logger.getLogger("International logger", "loggers.localization.logmessages");
        logger.setUseParentHandlers(false);

        // Creating new separate handlers
        var consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setEncoding("Cp866");

        var fileHandler = new FileHandler("loggers/logs/java%g");
        fileHandler.setEncoding("UTF-8");
        fileHandler.setLevel(Level.ALL);

        // Making connections
        logger.addHandler(consoleHandler);
        logger.addHandler(fileHandler);

        // Logging
        logger.info("readingFile");
        logger.log(Level.INFO, "devMsg", "Govnokod");
    }

    static int factorial(int n){
        var walker = StackWalker.getInstance();
        //traceInfo.append("\n");
        walker.forEach(System.out::println);
        if (n > 0) return n * factorial(n-1);
        return 1;
    }

    static void upperCaseRead(String inPath, String outPath) throws FileNotFoundException, IOException{
        try (var in = new Scanner(
            new FileInputStream(inPath), StandardCharsets.UTF_8);
            var out = new PrintWriter(outPath, StandardCharsets.UTF_8))
        {
            while (in.hasNext())
            out.println(in.next().toUpperCase());
        }
    }

    static void infest(Human human, Headcrab crab) throws Exception {
        try{
            crab.takeControl(human);
        }
        catch (NoName | HeadProtectionDetected e){
            var wrapper = new Exception();
            wrapper.initCause(e);
            throw wrapper;
        }
    }

    static void readArray(int[] arr, int i){
        if (i > arr.length || i < 0) throw new ArrayIndexOutOfBoundsException("You are moron");
        System.out.println(arr[i]);
    }
}

class Headcrab{
    public void takeControl(Human victim) throws HeadProtectionDetected, NoName {
        if (victim.getHeadProtectionStatus() == true) throw new HeadProtectionDetected("It's over...");
        if (victim.getName() == null || victim.getName().equals("")) throw new NoName("Who is this guy?");
        System.out.println("Headcrab has taken control of " + victim.getName() + " body");
    }
}

class Human{
    private String name;
    private boolean headProtection;

    public Human(String name, boolean headProtection){
        setName(name);
        this.headProtection = headProtection;
    }

    public Human(){
        // nothing happens, all fields are initialized by the program automatically
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getHeadProtectionStatus(){
        return headProtection;
    }
}

class HeadProtectionDetected extends Exception{
    public HeadProtectionDetected(){}
    public HeadProtectionDetected(String message){
        super(message);
    }
}

class NoName extends Exception{
    public NoName(){}
    public NoName(String message){
        super(message);
    }
}