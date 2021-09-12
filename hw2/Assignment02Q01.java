package il.ac.tau.cs.sw1.ex2;

public class Assignment02Q01 {

	public static void main(String[] args) {
		// for each string
		for (String str : args) {
			char first = str.charAt(0);
			int val = first;
			//check first char value
			if (val % 6 == 0) {
				System.out.println(first);
			}
		}
		
	}

}
