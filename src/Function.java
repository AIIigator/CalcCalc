
/**
 * Abstract class Function - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
import java.util.*;
public class Function
{
    private ArrayList<MulTerm> terms;
    
    public Function(ArrayList<MulTerm> t) {
        terms = t;
    }
    
    public Function(String s) {
        terms = new ArrayList<>();
        if(s.charAt(0)=='('&&s.charAt(s.length()-1)==')')
        	s=s.substring(1,s.length()-1);
        
        List<String> ray= new ArrayList<>();
        
        int brackets = 0;
        int whereWeAre = 0;
        
        for(int i=0;i<s.length();i++) {
        	char c=s.charAt(i);
        	if(c =='('){
        		brackets++;
        	}
        	if(c == ')') {
        		brackets--;
        	}
        	if(brackets<0){
        		throw new RuntimeException();
        	}
        	if(brackets==0){
        		if(c=='+') {
	        		ray.add(s.substring(whereWeAre,i));
	        		whereWeAre=i+1;
        		}
        	}
        }
        ray.add(s.substring(whereWeAre,s.length()));
        // String[] ray = s.split("\\+");
        
        /*for(String baka : ray) {
            System.out.println(baka);
        }*/
        
        for(String t : ray) {
            MulTerm baka = new MulTerm(t);
            terms.add(baka);
        }
        cleanUp();
    }
    
    public Function derive() {
        ArrayList<MulTerm> ls = new ArrayList<>();
        for(MulTerm t : terms) {
            ArrayList<MulTerm> baka = t.derive();
            for(MulTerm mt : baka) {
                ls.add(mt);
            }
        }
        Function f = new Function(ls);
        f.cleanUp();
        return f;
    }
    
    public Function integrate() throws IntegrationException {
        ArrayList<MulTerm> ls = new ArrayList<>();
        for(MulTerm t : terms) {
            ArrayList<MulTerm> baka = t.integrate();
            for(MulTerm mt : baka) {
                ls.add(mt);
            }
        }
        Function f = new Function(ls);
        f.cleanUp();
        return f;
    }
    
    public String toString() {
        String ans = "";
        cleanUp();
        
        if(terms.size() != 0) {
            for(MulTerm t : terms) {
                ans = ans + t.toString() + " + ";
            }
            ans = ans.substring(0,ans.length()-3);
        } else {
            ans = "0";
        }
        return ans;
    }
    
    public void cleanUp() {
        for (int i = 0; i < terms.size(); i++) {
            // terms.get(i).cleanUp();
            if(terms.get(i).toString().equals("0")) {
                terms.remove(i);
            }
        }
        
        for(int i = 0; i < terms.size() - 1; i++) {
            double e1 = 0;
            
            if(! terms.get(i).isSingle()) {
                continue;
            }
            
            if(terms.get(i).firstTerm() instanceof PolyTerm) {
                e1 = ((PolyTerm)terms.get(i).firstTerm()).getExp();
            } else {
                continue;
            }
            
            for(int k = i + 1; k < terms.size(); k++) {
                double e2 = 0;
                
                if(! terms.get(k).isSingle()) {
                    continue;
                }
                
                if(terms.get(k).firstTerm() instanceof PolyTerm) {
                    e2 = ((PolyTerm)terms.get(k).firstTerm()).getExp();
                } else {
                    continue;
                }
                
                if(e1 == e2) {
                    double c1 = ((PolyTerm)terms.get(i).firstTerm()).getCoeff();
                    double c2 = ((PolyTerm)terms.get(k).firstTerm()).getCoeff();
                    
                    ((PolyTerm)terms.get(i).firstTerm()).setCoeff(c1 + c2);
                    terms.remove(k);
                }
            }
        }
    }
}
