
/**
 * Write a description of class Runner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class Runner
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        System.out.println("input");
        String s = sc.nextLine();
        
        try {
            Function f = new Function(s);
            f.cleanUp();
            System.out.print("The function you entered is:  ");
            System.out.println(f);
            System.out.println("The derivative is:");
            Function d = f.derive();
            d.cleanUp();
            System.out.println(d);
            Function i = f.integrate();
            System.out.println("The integral is:");
            i.cleanUp();
            System.out.print(i);
            System.out.println(" + C");
        } catch (IntegrationException e) {
            System.out.println("That's too hard for this program to integrate");
        } catch (Exception e) {
            System.out.println("Yeah something went wrong, idk what to tell you");
            System.out.println("Maybe you just typed something that wasn't a function?");
            System.out.println("Or my code just sucks... this one seems most likely");
            //System.out.println(e.getStackTrace());
        } finally {
        	sc.close();
        }
    }
}
