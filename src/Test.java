import java.io.Console;
import java.util.*;
import java.util.regex.*;

public class Test {
    
    public static void p(Object[] a) {
        for(Object o : a) {
            System.out.println(o);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("*****************");
        
        String s = "3x*2ln(x)";
        Function f = new Function(s);
        System.out.println(f);
        f.cleanUp();
        System.out.println(f);
        
        // System.out.println(f.terms.get(0).terms.get(1) instanceof PolyTerm);
        
        Function d = f.derive();
        System.out.println(d);
        
        d.cleanUp();
        System.out.println(d);
        
        System.out.println(d);
    }
}