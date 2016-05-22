import java.io.Console;
import java.util.*;
// import java.util.regex.*;

public class RegexTester {

    public static void main(String[] args){
        /*
        Console console = System.console();
        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }
        */
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your regex: ");
            
            String regex = sc.nextLine();

            System.out.println("Enter input string to search: ");
            
            String test = sc.nextLine();
            
            if(test.matches(regex)) {
                System.out.println("I found stuff dude");
            } else {
                System.out.println("yeah sorry nope");
            }
            
            /*console.format("I found the text" +
                " \"%s\" starting at " +
                "index %d and ending at index %d.%n",
                matcher.group(),
                matcher.start(),
                matcher.end()); */
        }
    }
}