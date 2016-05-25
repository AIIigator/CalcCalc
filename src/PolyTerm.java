
/**
 * Write a description of class PolyTerm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class PolyTerm extends Term
{
    private double coeff;
    private double exp;
    private boolean innerFunction; //find inner function.
    private Function inner;
    
    public double getExp() {
        return exp;
    }
    
    public double getCoeff() {
        return coeff;
    }
    
    public void setCoeff(double c) {
        coeff = c;
    }
    
    public void setCoeff(int c) {
        coeff = c;
    }
    
    public PolyTerm(String s) throws IllegalArgumentException {
        //System.out.println(s + " here is what it looks like");
        
        int brackets = 0;
        int whereWeAt = 0; 
        String innerString="";
        for(int i=0; i < s.length(); i++){
        	char c = s.charAt(i);
        	if( c == '(' ){
        		brackets++;
        		innerFunction = true;
        		if(brackets == 1){
        			whereWeAt = i;
        		}
        	}
        	if( c == ')' ){
        		brackets--;
        		if(brackets == 0){
        			innerString = s.substring(whereWeAt+1,i);
        		}
        	}
        }
        if(innerFunction){
        	//inner = new Function(innerString);
        	//System.out.println(innerString);
        	System.out.println("sorry, my program can't do chain rule yet.");
        	System.out.println("Please wait for version 2 which will inclue chain rule.");
        	throw new IllegalArgumentException();
        }
        
        int dex = 0;
        if(!innerFunction) {
        	dex = s.indexOf("x");
        } else {
        	dex = s.indexOf("(");
        }
        
        if(dex < 0) {
            // System.out.println("This term does not contain an x");
            try {
                coeff = Double.parseDouble(s);
                exp = 0;
                return;
            } catch (Exception e) {
                throw new IllegalArgumentException();
            }
        } else if(dex == 0) {
            coeff = 1;
        } else {
            try {
                coeff = Double.parseDouble(s.substring(0,dex));
                //System.out.println(coeff+" this is the coeff");
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException();
            }
        }
        String test="";
        if(!innerFunction){
            dex = s.lastIndexOf("^");
        } else {
        	test=s.substring(s.indexOf(")")+1,s.length());
        	System.out.println(test+" test string");
        	dex=test.indexOf("^");
        	//dex=s.indexOf("^");
        }
        
        if(dex < 0) {
            exp = 1;
        } else {
            try {
            	if(!innerFunction){
            	//System.out.println(s.substring(dex+1,s.length())+" this is where exp is");
                exp = Double.parseDouble(s.substring(dex+1,s.length()));
            	} else {
            		exp = Double.parseDouble(test.substring(dex+1,test.length()));
            	}
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException();
            }
        }
    }
    
    public PolyTerm(int c, int e) {
        coeff = (double) c;
        exp = (double) e;
    }
    
    public PolyTerm(double c, double e) {
        coeff = c;
        exp = e;
    }
    
    public PolyTerm(double c, int e) {
        coeff = c;
        exp = (double) e;
    }
    
    public PolyTerm(int c, int e, Function f) {
        coeff = (double) c;
        exp = (double) e;
        inner = f;
    }
    
    public PolyTerm(double c, double e, Function f) {
        coeff = c;
        exp = e;
        inner = f;
    }
    
    public PolyTerm(double c, int e, Function f) {
        coeff = c;
        exp = (double) e;
        inner = f; 
    }
    public String toString() {
        String ans = "";
        if(coeff != 0 && coeff != 1) {
            ans += coeff;
        } else if(coeff == 1) {
            //ans = ans;
        } else {
            return "0";
        }
        
        if(exp != 0 && exp != 1) {
            ans += "x^" + exp;
        } else if(exp == 1) {
            ans += "x";
        }
        return ans;
    }
    
    public PolyTerm derive() {
        if(exp == 0) {
        	return new PolyTerm(0,0);
	    }
        if(!innerFunction) {
	        return new PolyTerm(coeff*exp,exp-1);
        } else {
        	return new PolyTerm(coeff*exp,exp-1,inner);
        }
    }
    
    public ArrayList<Term> integrate() {
        ArrayList<Term> ls = new ArrayList<>();
        if(exp != -1) {
            ls.add( new PolyTerm(coeff / (exp + 1.0), exp + 1) );
        } else {
            ls.add( new LnTerm(coeff,1) );
        }
        return ls;
    }
}
