package org.amse.yaroslavtsev.practice.knots.math;

import java.util.*;
import java.util.Map.Entry;

public class Polynomial {

	private LinkedList <Monomial> myMonomials = new LinkedList <Monomial> ();
	
	private void addLastMonomial(Monomial monomial) {
		if (monomial.myCoefficient != 0) {
			myMonomials.addLast(monomial);
		}
	}
	
	public static class Monomial {
		private int myPower;
		private int myCoefficient;

		public Monomial(int power, int coefficient) {
			myPower = power;
			myCoefficient = coefficient;
		}

		private Monomial makeCopy() {
			return new Monomial(myPower, myCoefficient);
		}
		
		public char sign() {
			if (myCoefficient >= 0) {
				return '+';
			} else {
	                	return '-';
			}
		}

	        public String toString() {
		        String coefficient = "";
		        if (Math.abs(myCoefficient) != 1) {
		        	coefficient = Math.abs(myCoefficient) + "";
		        }
			return coefficient + "x<sup>" + myPower + "</sup>";
		}

		public void setPower(int power) {
			myPower = power;
		}

		public int getPower() {
			return myPower;
		}

		public int getCoefficient() {
			return myCoefficient;
		}
	}

	public List <Monomial> getMonomials() {
		return Collections.unmodifiableList(myMonomials);
	}

	public static Polynomial mul(Polynomial p1, Polynomial p2) {
		Polynomial result = new Polynomial();
		Polynomial current = new Polynomial();
		for (Monomial current1 : p1.myMonomials) {
			current.myMonomials.clear();						
			for (Monomial current2 : p2.myMonomials) {
				current.addLastMonomial(new Monomial(current1.myPower + current2.myPower, current1.myCoefficient * current2.myCoefficient));
			}			
			result = add(result, current);
		}
		return result;
	}
	
	private static Monomial getNext(Iterator <Monomial> it) {
		if (it.hasNext()) {
			return it.next();
		} else {
			return null;
		}
	}
	
	public static Polynomial add(Polynomial p1, Polynomial p2) {
		Iterator <Monomial> itFirst = p1.myMonomials.iterator();
		Iterator <Monomial> itSecond = p2.myMonomials.iterator();
		Monomial first = getNext(itFirst);
		Monomial second = getNext(itSecond);
		Polynomial result = new Polynomial();
		while (first != null && second != null) {
			if (first.myPower == second.myPower) {
				result.addLastMonomial(new Monomial(first.myPower, first.myCoefficient + second.myCoefficient));				
				first = getNext(itFirst);
				second = getNext(itSecond);
	                	
			} else if (first.myPower < second.myPower) {
				result.addLastMonomial(first.makeCopy());
				first = getNext(itFirst);
			} else {
				result.addLastMonomial(second.makeCopy());
				second = getNext(itSecond);
			}			
		}
		if (first != null) {
			result.addLastMonomial(first.makeCopy());
			while (itFirst.hasNext()) {
				result.addLastMonomial(itFirst.next().makeCopy());
			}
		}
		if (second != null) {
			result.addLastMonomial(second.makeCopy());
			while (itSecond.hasNext()) {
				result.addLastMonomial(itSecond.next().makeCopy());
			}
		}
		return result;
	}
	
	public void addMonomial(Monomial monomial) {
                if (monomial.getCoefficient() == 0) {
                        return;
                } 
                int position = 0;
		boolean modified = false;
		for (Monomial current : myMonomials) {
			if (current.myPower == monomial.myPower) {
				current.myCoefficient += monomial.myCoefficient;
				if (current.getCoefficient() == 0) {
                                        myMonomials.remove(current);
                                }
                                modified = true;
				break;
			} 
			if (current.myPower > monomial.myPower) {
				break;			           	
			}
			position++;
		}
		if (!modified) {
                        myMonomials.add(position, monomial);
                }

	}
	
	public String toString() {
	        if (myMonomials.size() == 0) {
                        return "<html>0</html>";
                }
                StringBuffer result = new StringBuffer();
	        boolean first = true;
		for (Monomial current : myMonomials) {
		        char sign = current.sign();
			if (!first) {
				result.append(" " + sign + " " + current);	
			} else {
				if (sign == '-') {
					result.append(sign + " ");
				}								
				result.append(current);
			}
			if (first) {
				first = false;
			}						
		}
		return "<html>" + result.toString() + "</html>";
	}
	
/*	public static void main(String [] args) {
		Polynomial poly = new Polynomial();		
                poly.addMonomial(new Monomial(1,1));
                poly.addMonomial(new Monomial(2,2));
                poly.addMonomial(new Monomial(-10,2));
                poly.addMonomial(new Monomial(0,3));
                System.out.println(poly);
                Polynomial poly1 = new Polynomial();				
                poly1.addMonomial(new Monomial(1,1));
                poly1.addMonomial(new Monomial(3,2));
                poly1.addMonomial(new Monomial(-1,1));
                poly1.addMonomial(new Monomial(-2,2));
                System.out.println(poly1);
		System.out.println(add(poly, poly1));
		System.out.println(mul(poly, poly1));
	}*/
}
