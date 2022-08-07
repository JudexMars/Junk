import java.util.Scanner;

import classdir.npcs.*;

import java.io.*;
import java.time.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

class Start{
    public static void main(String[] args) throws IOException {
/*         Scanner f = new Scanner(Path.of("myfile.txt"), StandardCharsets.UTF_8);
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

        LocalDate date = LocalDate.now();
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM, yyyy", Locale.US);
        System.out.println(date.format(dtf).toString());
        System.out.println(date.toString());

        Human humanTest = new Human(null, 37, Human.Sex.Male);
        System.out.println(humanTest.getName() + " " + humanTest.getAge() + " " + humanTest.getSex()); */

        Group group = new Group(10);
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.print("Human number: ");
            int num = in.nextInt();
            if (num == 0) break;
            var x = group.getHuman(num);
            System.out.println(x.getName() + " " + x.getAge() + " " + x.getSex());
        }
    }
}