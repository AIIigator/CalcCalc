
/**
 * Write a description of class Term here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public abstract class Term
{
    public abstract Term derive();
    
    public abstract ArrayList<Term> integrate() throws IntegrationException;
    
    public abstract double getCoeff();
    
    public abstract void setCoeff(double c);
    
    public Term() {
        
    }
    
    public static Term get(String s) throws IllegalArgumentException {
        
        String poly = "[0-9\\.\\-]*(\\(.*x.*\\)|x?)(\\^[0-9\\.\\-]*)?";
        // slashless: [0-9\.\-]*(\(.*x.*\)|x?)(\^[0-9\.\-]*)?
        
        // String polyChain="[0-9\\.\\-]*(\\(.*x\\+(\\(*x*\\))\\)|x?)(\\^[0-9\\.\\-]*)?";
        
        String ln = "[0-9\\.\\-]*ln\\([0-9\\.\\-]*x\\)";
        // slashless: [0-9\.\-]*ln\([0-9\.\-]*x\)
        
        String log = "[0-9\\.\\-]*log(_[0-9\\.]*)?\\([0-9\\.\\-]*x\\)";
        // slashless: [0-9\.\-]*log(_[0-9\.]*)?\([0-9\.\-]*x\)
        
        boolean isTrig = true;
        if(s.indexOf("sin") < 0 && 
            s.indexOf("cos") < 0 && 
            s.indexOf("tan") < 0 && 
            s.indexOf("csc") < 0 &&
            s.indexOf("sec") < 0 &&
            s.indexOf("cot") < 0) {
            isTrig = false;
        }
        
        // System.out.println(rgx);
        // System.out.println(s.matches(rgx));
        // Term t = null;
        
        // boolean m = s.matches(rgx);
        // System.out.println(m);
        
        
        
        
        if(s.matches(poly)) {
            try {
                Term t = new PolyTerm(s);
                return t;
            } catch(Exception e) {
                throw new IllegalArgumentException();
            }
        } else if(s.matches(ln)) {
            Term t = new LnTerm(s);
            return t;
        } else if(s.matches(log)) {
            Term t = new LogTerm(s);
            return t;
        } else if(isTrig) {
            Term t = new TrigTerm(s);
            return t;
        
        } else {
            throw new IllegalArgumentException();
        }
        // return null;
    }
}
