
/**
 * Write a description of class LnTerm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LnTerm extends LogTerm
{
    public LnTerm(double c, double i) {
        super(c, i);
    }
    
    public LnTerm(String s) {
        super(2.718,1,1);
        int dex = s.indexOf("ln");
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
        
        setCoeff(c);
        setInside(i);
    }
    
    public PolyTerm derive() {
        return new PolyTerm(coeff,-1);
    }
    
    public String toString() {
        String ans = "";
        ans = coeff != 1 ? ans + coeff : ans;
        ans += "ln(";
        ans = inside != 1 ? ans + inside : ans;
        ans += "x)";
        
        return ans;
    }
}
