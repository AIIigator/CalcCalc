
/**
 * Write a description of class LogTerm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class LogTerm extends Term 
{    protected double base;
    protected double coeff;
    protected double inside;
    
    public double getCoeff() {
        return coeff;
    }
    
    public void setCoeff(double c) {
        coeff = c;
    }
    
    public void setInside(double i) {
        inside = i;
    }
    
    public double getInside() {
        return inside;
    }
    
    public LogTerm(double b, double c, double i) {
        base = b;
        coeff = c;
        inside = i;
    }
    
    public LogTerm(double c, double i) {
       coeff = c;
       inside = i;
    }
    
    public LogTerm(String s) throws IllegalArgumentException {
        int dex = s.indexOf("log");
        double c = 1;
        if(dex != 0) {
            c = Double.parseDouble(s.substring(0,dex));
        }
        
        if(s.indexOf("_") < 0) {
            base = 10;
        } else {
            int d1 = s.indexOf("_");
            int d2 = s.indexOf("(");
            if(d1 + 1 == d2) {
                throw new IllegalArgumentException();
            } else {
                base = Double.parseDouble(s.substring(d1+1,d2));
            }
        }
        
        
        int d1 = s.indexOf("(");
        int d2 = s.indexOf("x");
        double i = 1;
        if(d1 + 1 != d2) {
            i = Double.parseDouble(s.substring(d1+1,d2));
        }
        
        coeff = c;
        inside = i;
    }
    
    public PolyTerm derive() {
        return new PolyTerm((1.0 / Math.log(base)), -1);
    }
    
    public ArrayList<Term> integrate() throws IntegrationException {
        throw new IntegrationException();
    }
    
    public String toString() {
        String ans = "";
        ans = coeff != 1 ? ans + coeff : ans;
        ans += "log_";
        ans = base != 10 ? ans + base : ans.substring(0,ans.length()-1);
        ans += "(";
        ans = inside != 1 ? ans + inside : ans;
        ans += "x)";
        return ans;
    }
}
