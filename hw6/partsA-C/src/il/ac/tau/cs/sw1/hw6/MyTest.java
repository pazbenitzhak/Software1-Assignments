package il.ac.tau.cs.sw1.hw6;

import java.util.Arrays;

public class MyTest {
	
public static void main(String[] args) {
		
		
		Polynomial p1 = new Polynomial(); // p1 = 0
		
		printError(p1.getDegree() == 0,
				"the degree of p1 is 0");
		
		printError(p1.computePolynomial(3) == 0.0,
				"the value of p1 with x=3 is 0.0");
		
		double[] coefficients = new double[]{1.0,2.0,3.0};
		Polynomial p2 = new Polynomial(coefficients); // p2 = 1.0+2.0*x+3.0*x^2
		
		printError(p2.computePolynomial(3) == 34.0,
				"the value of p2 with x=3 is 34.0");
		
		printError(p2.getCoefficient(1) == 2.0,
				"the coefficient of x is 2.0");
		
		Polynomial p3 = p2.multiply(2.0);
		/*
		 * p2 = 1.0+2.0*x+3.0*x^2
		 * p3 = 2.0+4.0*x+6.0*x^2
		 */
		
		printError(p2.getCoefficient(1) == 2.0,
				"the coefficient of x is 2.0");
		
		printError(p3.getCoefficient(1) == 4.0,
				"the coefficient of x is 4.0");
		
		
		Polynomial p4 = p2.adds(p3);
		/*
		 * p2 = 1.0+2.0*x+3.0*x^2
		 * p3 = 2.0+4.0*x+6.0*x^2
		 * p4 = 3.0+6.0*x+9.0*x^2
		 */
		printError(p4.getDegree() == 2,
				"the degree of p4 is 2");
		
		Polynomial p5 = p4.getFirstDerivation();
		
		/*
		 * p4 = 3.0+6.0*x+9.0*x^2
		 * p5 = 6.0 + 18*x
		 */
		
		printError(p5.getDegree() == 1,
				"the degree of p5 is 1");
		
		
		double [] coef_p6 = {1.0,3.7,-4.2,6.5,0.0,7.0};
		Polynomial p6 = new Polynomial(coef_p6);
		
		if (p6.getCoefficient(1)!=3.7) {
			System.out.println("error no. 1");
		}
		
		if (p6.getCoefficient(5)!=7.0) {
			System.out.println("error no. 2");
		}
		
		p6.setCoefficient(4, 0.3);
		if (p6.getCoefficient(4)!=0.3) {
			System.out.println("error no. 3");
		}
		
		Polynomial p7 = p6.multiply(-2);
		double[] p7_correct = {-2.0,-7.4,8.4,-13,-0.6,-14.0};
		for (int i=0;i<p7_correct.length;i++) {
			if (p7_correct[i]!=p7.getCoefficient(i)) {
				System.out.println("error no. 5");
				System.out.println("index = " +i);
			}
		}
		
		double[] p8_coeff = {0.5,7.4,1.4,13.0,5.0,14.0};
		Polynomial p8 = new Polynomial(p8_coeff);
		Polynomial p9 = p7.adds(p8);
		
		if (p9.getDegree()!=4) {
			System.out.println("error no. 6");
		}
		
		
		if (p9.computePolynomial(2.7)!=303.7760400000001) {
			System.out.println("error no. 7");
		}
		
		
		Polynomial p10 = p9.getFirstDerivation();
		double [] derivative = {0.0,19.6,0.0,17.6};
		for (int i=0;i<derivative.length;i++) {
			if (derivative[i]!=p10.getCoefficient(i)) {
				System.out.println("error no. 8");
				System.out.println(p10.getCoefficient(i));
				System.out.println("index = " +i);
			}
		}
		
		double[] b = {0.0,2.0,1.0};
		Polynomial p11 = new Polynomial(b);
		if (!(p11.isExtrema(-1.0))) {
			System.out.println("error no. 9");
		}
		
		
		double[] coefficients2 = new double[]{-8.0,4.0,2.0};

        Polynomial p12 = new Polynomial(coefficients2);

        Polynomial p13 = p12.getFirstDerivation();
        Polynomial p14 = p13.getFirstDerivation();
        Polynomial p15 = p14.getFirstDerivation();
        Polynomial p16 = p15.getFirstDerivation();

        
        if(!p12.isExtrema(-1)) {
        	System.out.println("error no. 10");}
        if (p12.isExtrema(7)) {
        	System.out.println("error no. 11");}

        if(p13.isExtrema(-1)) {
        	System.out.println("error no. 12");
        }
				
        if(p14.isExtrema(7)) {
        	System.out.println("error no. 13");}
		
	}





	public static void printError(boolean condition, String message) {
		if (!condition) {
			throw new RuntimeException("[ERROR] " + message);
		}
	}
}
