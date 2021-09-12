package il.ac.tau.cs.sw1.ex2;

public class Assignment02Q02 {

	public static void main(String[] args) {
		// do not change this part below
		double piEstimation = 0.0;
		
		int n = Integer.parseInt(args[0]);
		//extract relevant number
		double sum = 0.0;
		for (double i=0; i<n; i++) {
			//first + condition, than -
			if (i%2 == 0) {
				sum += (1/(1+2*i));
			}
			else {
				sum -= (1/(1+2*i));
			}
		}
		
		piEstimation = 4*sum;
		//multiply to get closer to pi
		
		
		// do not change this part below
		System.out.println(piEstimation + " " + Math.PI);

	}

}
