
/**
 * Write a description of class TrigTerm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class TrigTerm extends Term
{
    // instance variables - replace the example below with your own
    private double coeff;
    private double inside;
    private double exp;
    public TrigFunc func;
    private String[] trigFuncs = {"sin","cos","tan","csc","sec","cot"};
    private final TrigFunc[] funcs = TrigFunc.values();
    
    
    public double getCoeff() {
        return coeff;
    }
    
    public void setCoeff(double c) {
        coeff = c;
    }
    
    public double getInside() {
        return inside;
    }
    
    public void setInside(double i) {
        inside = i;
    }
    
    private int which(String s) throws IllegalArgumentException {
        for(int i = 0; i < trigFuncs.length; i++) {
            int dex = s.indexOf(trigFuncs[i]);
            if(dex >= 0) {
                return i;
            }
        }
        throw new IllegalArgumentException();
    }

    public TrigTerm(String s) throws IllegalArgumentException {
        int w = which(s);
        
        func = funcs[w];
        
        /* switch(w) {
            case 0:
            func = TrigFunc.SIN;
            break;
            case 1:
            func = TrigFunc.COS;
            break;
            case 2:
            func = TrigFunc.TAN;
            break;
            case 3:
            func = TrigFunc.CSC;
            break;
            case 4:
            func = TrigFunc.SEC;
            break;
            case 5:
            func = TrigFunc.COT;
            break;
        } */
        
        int dex = s.indexOf(trigFuncs[w]);
        double c = 1;
        if(dex != 0) {
            c = Double.parseDouble(s.substring(0,dex));
        }
        
        int d1 = s.indexOf("(");
        int d2 = s.indexOf("x");
        double i = 1;
        if(d1 + 1 != d2) {
            i = Double.parseDouble(s.substring(d1+1,d2));
        }
        
        coeff = c;
        inside = i;
        exp = 1;
    }
    
    public TrigTerm(int f, double c, double i) {
        func = funcs[f];
        coeff = c;
        inside = i;
        exp = 1;
    }
    
    public TrigTerm(int f, double c, double i, double e) {
        func = funcs[f];
        coeff = c;
        inside = i;
        exp = e;
    }
    
    public TrigTerm(TrigFunc f, double c, double i) {
        func = f;
        coeff = c;
        inside = i;
        exp = 1;
    }

    public TrigTerm(TrigFunc f, double c, double i, double e) {
        func = f;
        coeff = c;
        inside = i;
        exp = e;
    }
    
    public TrigTerm derive() throws IllegalArgumentException {
        double c = coeff * inside;
        double i = inside;
        double e = 1;
        TrigFunc f;
        
        switch(func) {
            case SIN:
            f = TrigFunc.COS;
            break;
            
            case COS:
            f = TrigFunc.SIN;
            c *= -1;
            break;
            
            case TAN:
            f = TrigFunc.SEC;
            e = 2;
            break;
            
            case CSC:
            throw new IllegalArgumentException();
            
            case SEC:
            throw new IllegalArgumentException();
            
            case COT:
            f = TrigFunc.CSC;
            e = 2;
            c *= -1;
            break;
            
            default:
            f = TrigFunc.SIN;
            break;
        }
        
        return new TrigTerm(f,c,i,e);
    }
    
    public ArrayList<Term> integrate() throws IntegrationException {
        double c = coeff / inside;
        double i = inside;
        double e = 1;
        TrigFunc f;
        
        switch(func) {
            case SIN:
            f = TrigFunc.COS;
            c *= -1;
            break;
            
            case COS:
            f = TrigFunc.SIN;
            break;
            
            case TAN:
            throw new IntegrationException();
            
            case CSC:
            throw new IntegrationException();
            
            case SEC:
            throw new IntegrationException();
            
            case COT:
            throw new IntegrationException();
            
            default:
            f = TrigFunc.SIN;
            break;
        }
        
        ArrayList<Term> ls = new ArrayList<>();
        ls.add(new TrigTerm(f,c,i,e));
        return ls;
    }
    
    public String toString() {
        String ans = "";
        
        ans = coeff != 1 ? ans + coeff : ans;
        
        String f = "";
        switch(func) {
            case SIN:
            f = "sin";
            break;
            
            case COS:
            f = "cos";
            break;
            
            case TAN:
            f = "tan";
            break;
            
            case CSC:
            f = "csc";
            break;
            
            case SEC:
            f = "sec";
            break;
            
            case COT:
            f = "cot";
            break;
        }
        
        ans = ans + f + "(";
        
        ans = inside != 1 ? ans + inside: ans;
        
        ans += "x)";
        
        if(exp != 1) {
            ans = "(" + ans + ")^" + exp;
        }
        
        return ans;
    }
}
