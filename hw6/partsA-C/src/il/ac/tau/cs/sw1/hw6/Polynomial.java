package il.ac.tau.cs.sw1.hw6;

public class Polynomial {
	
	private double[] coefficients;
	/*
	 * Creates the zero-polynomial with p(x) = 0 for all x.
	 */
	public Polynomial()
	{
		this.coefficients= new double[0];
	} 
	/*
	 * Creates a new polynomial with the given coefficients.
	 */
	public Polynomial(double[] coefficients) 
	{
		this.coefficients=coefficients;
	}
	/*
	 * Addes this polynomial to the given one
	 *  and retruns the sum as a new polynomial.
	 */
	public Polynomial adds(Polynomial polynomial)
	{
		int new_length=Math.max(this.coefficients.length, polynomial.coefficients.length);
		int smaller_length = Math.min(this.coefficients.length, polynomial.coefficients.length);
		Polynomial longer;
		if (new_length==this.coefficients.length) {
			longer=this;
		} else {
			longer=polynomial;
		}
		
		double[] new_coeff = new double[new_length];
		for (int i =0;i<smaller_length;i++) {
			new_coeff[i]=this.coefficients[i]+polynomial.coefficients[i];
		}
		for (int j=smaller_length;j<new_length;j++) {
			new_coeff[j]=longer.coefficients[j];
		}
		new_coeff=eraseupperdegree(new_coeff);
		return new Polynomial(new_coeff);
		
	}
	/*
	 * Multiplies a to this polynomial and returns 
	 * the result as a new polynomial.
	 */
	public Polynomial multiply(double a)
	{
		double[] new_coeff = new double[this.coefficients.length];
		for (int i=0;i<this.coefficients.length;i++) {
			new_coeff[i]=a*this.coefficients[i];
		}
		if (a==0.0) {
			return new Polynomial();
		}
		return new Polynomial(new_coeff);
		
	}
	/*
	 * Returns the degree (the largest exponent) of this polynomial.
	 */
	public int getDegree()
	{
		this.coefficients = eraseupperdegree(this.coefficients);
		if (this.coefficients.length==0) {
			return 0;
		}
		return this.coefficients.length-1;
	}
	/*
	 * Returns the coefficient of the variable x 
	 * with degree n in this polynomial.
	 */
	public double getCoefficient(int n)
	{
		if (this.coefficients.length==0) {
			return 0;
		}
		if (n>=this.coefficients.length) {
			return 0.0;
		}
		return this.coefficients[n];
	}
	
	/*
	 * set the coefficient of the variable x 
	 * with degree n to c in this polynomial.
	 * If the degree of this polynomial < n, it means that that the coefficient of the variable x 
	 * with degree n was 0, and now it will change to c. 
	 */
	public void setCoefficient(int n, double c)
	{
		if (n+1>this.coefficients.length) {
			double[] new_coeff = new double[n+1];
			System.arraycopy(this.coefficients, 0, new_coeff, 0, this.coefficients.length);
			new_coeff[n]=c;
			new_coeff = eraseupperdegree(new_coeff);
			this.coefficients=new_coeff;
		}
		this.coefficients[n]=c;
		this.coefficients= eraseupperdegree(this.coefficients);
	}
	
	/*
	 * Returns the first derivation of this polynomial.
	 *  The first derivation of a polynomal a0x0 + ...  + anxn is defined as 1 * a1x0 + ... + n anxn-1.
	
	 */
	public Polynomial getFirstDerivation()
	{
		if (this.coefficients.length==0) {
			return this;
		}
		double[] new_coeff = new double[this.coefficients.length-1];
		for (int i=0;i<new_coeff.length;i++) {
			new_coeff[i]=(i+1)*this.coefficients[i+1];
		}
		Polynomial derivative=new Polynomial(new_coeff);
		return derivative;
	}
	
	/*
	 * given an assignment for the variable x,
	 * compute the polynomial value
	 */
	public double computePolynomial(double x)
	{
		if (this.coefficients.length==0) {
			return 0.0;
		}
		double sum = 0.0;
		for (int i=0;i<this.coefficients.length;i++) {
			sum+=this.coefficients[i]*Math.pow(x, i);
		}
		return sum;
	}
	
	/*
	 * given an assignment for the variable x,
	 * return true iff x is an extrema point (local minimum or local maximum of this polynomial)
	 * x is an extrema point if and only if The value of first derivation of a polynomal at x is 0
	 * and the second derivation of a polynomal value at x is not 0.
	 */
	public boolean isExtrema(double x)
	{
		Polynomial first_deriv = this.getFirstDerivation();
		if (first_deriv.computePolynomial(x)==0.0) {
			Polynomial second_deriv = first_deriv.getFirstDerivation();
			if (second_deriv.computePolynomial(x)!=0.0) {
				return true;
			}
		}
		return false;
	}
	
	public double[] eraseupperdegree(double[] args) {
		if (args.length==0) {
			return args;
		}
		if (args[args.length-1]!=0) {
			return args;
		}
		else {
			double[] new_array = new double[args.length-1];
			System.arraycopy(args, 0, new_array, 0, new_array.length);
			return eraseupperdegree(new_array);
		}
	}
	
	
	
	

    
    

}
