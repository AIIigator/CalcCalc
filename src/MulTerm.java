
/**
 * Write a description of class MulTerm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MulTerm
{
    private ArrayList<Term> terms;
    protected double coeff;
    private Function [] rayFunc;  
    
    public double getCoeff() {
        return coeff;
    }
    
    public MulTerm(String s) {
        coeff = 1;
        terms = new ArrayList<>();
        String[] ray = s.split("\\*");
        
        /*for(String baka : ray) {
            System.out.println(baka);
        }*/
        
        for(String t : ray) {
            Term baka = Term.get(t);
            terms.add(baka);
        }
        cleanUp();
    }
    
    public MulTerm(Term t) {
        terms = new ArrayList<>();
        terms.add(t);
        cleanUp();
    }
    
    public MulTerm(ArrayList<Term> ls) {
        terms = ls;
    }
    
    /*public MulTerm(Function [] f){
    	rayFunc=f; 
    }*/
    
    
    public String toString() {
        String ans = "";
        // cleanUp();
        
        if(terms.size() != 0) {
            for(Term t : terms) {
                ans = ans + t.toString() + "*";
            }
            ans = ans.substring(0,ans.length()-1);
            
            for (int i = 0; i < terms.size(); i++) {
                if(terms.get(i).toString().equals("0")) {
                    ans = "0";
                }
            }
        } else {
            ans = "0";
        }
        int l = ans.length();
        if(ans.charAt(l-1) == '*') {
            ans = ans.substring(0,l-1);
        }
        return ans;
    }
    
    public void cleanUp() {
        coeff = 1;
        
        for (int i = terms.size()-1; i >= 0; i--) {
            if(terms.get(i).toString().equals("1") || terms.get(i).toString().equals("1.0")) {
                terms.remove(i);
            }
            
            if(terms.get(i) instanceof PolyTerm) {
                PolyTerm qq = (PolyTerm) terms.get(i);
                if(qq.getCoeff() == 1 && qq.getExp() == 0) {
                    terms.remove(i);
                }
            }
        }
        
        for(int i = 0; i < terms.size(); i++) {
            coeff *= terms.get(i).getCoeff();
            // System.out.println(coeff);
            terms.get(i).setCoeff(1);
        }
        terms.get(0).setCoeff(coeff);
        
        simplify();
        
        /*for(int i = 0; i < terms.size(); i++) {
            if(terms.get(i) instanceof PolyTerm) {
                double tot = ((PolyTerm)terms.get(i)).getExp();
                for(int j = terms.size() - 1; j > i; j--) {
                    if(terms.get(j) instanceof PolyTerm) {
                        tot += ((PolyTerm)terms.get(j)).getExp();
                        terms.remove(j);
                    }
                }
                terms.remove(i);
                terms.add(new PolyTerm(1,tot));
            }
        }*/
        terms.get(0).setCoeff(coeff);
    }
    
    public void simplify() {
        double c = coeff;
        ArrayList<PolyTerm> polys = new ArrayList<>();
        
        for(int i = terms.size()-1; i >= 0; i--) {
            if(terms.get(i) instanceof PolyTerm) {
                polys.add((PolyTerm)terms.get(i));
                terms.remove(i);
            }
        }
        
        // System.out.println(polys.size());
        if(polys.size() > 0) {
            int tot = 0;
            for(PolyTerm t : polys) {
                tot += t.getExp();
            }
            // System.out.println(tot);
            terms.add(new PolyTerm(1,tot));
            terms.get(0).setCoeff(coeff);
        }
    }
    
    public boolean isSingle() {
        if(terms.size() == 1) {
            return true;
        }
        return false;
    }
    
    public Term firstTerm() {
        if(isSingle()) {
            return terms.get(0);
        } else {
            return null;
        }
    }
    
    public ArrayList<MulTerm> derive() {
        ArrayList<MulTerm> ls = new ArrayList<>();
        if(isSingle()) {
            MulTerm t = new MulTerm(firstTerm().derive());
            ls.add(t);
            return ls;
        } else {
            Term t11 = terms.get(0);
            Term t12 = terms.get(1).derive();
            Term t21 = terms.get(1);
            Term t22 = terms.get(0).derive();
            
            ArrayList<Term> l1 = new ArrayList<>();
            ArrayList<Term> l2 = new ArrayList<>();
            
            l1.add(t11);
            l1.add(t12);
            l2.add(t21);
            l2.add(t22);
            
            MulTerm mt1 = new MulTerm(l1);
            mt1.cleanUp();
            MulTerm mt2 = new MulTerm(l2);
            mt2.cleanUp();
            
            ls.add(mt1);
            ls.add(mt2);
            // System.out.println("bakahoe" + (ls.get(1).terms.get(1) instanceof PolyTerm));
            return ls;
        }
    }
    
    public ArrayList<MulTerm> integrate() throws IntegrationException{
        ArrayList<MulTerm> ls = new ArrayList<>();
        if(isSingle()) {
            MulTerm t = new MulTerm(firstTerm().integrate());
            ls.add(t);
            return ls;
        } else {
            System.out.println("Integration is hard, and");
            System.out.println("that's a bit too complicated for this program");
            throw new IllegalArgumentException();
        }
    }
}
