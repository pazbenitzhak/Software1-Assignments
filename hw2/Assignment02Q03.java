package il.ac.tau.cs.sw1.ex2;

public class Assignment02Q03 {

	public static void main(String[] args) {
		int numOfEven = 0;
		int n = -1;
		n = Integer.parseInt(args[0]); //reset to relevant value
		int prev = 0; //to start Fib seq
		int curr = 1;
		String sequence = "";
		for (int i = 0; i < n; i++) {
			int sum = prev + curr;
			prev = curr;
			curr = sum;
			String add = Integer.toString(prev);
			if (prev%2==0) {
				numOfEven+=1;
			}//for even numbers count
			sequence += add+ " ";// add to seq
		}
		
		
		System.out.println("The first "+ n +" Fibonacci numbers are:");
		System.out.println(sequence);
		System.out.println("The number of even numbers is: "+numOfEven);

	}

}
