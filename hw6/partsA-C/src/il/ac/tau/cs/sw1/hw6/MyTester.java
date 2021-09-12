package il.ac.tau.cs.sw1.hw6;

public class MyTester {
	
	static public void main(String[] args) {
		String s = "raul";
		System.out.println(reverse(s));
	}
	
	public static String reverse(String word) 
	{
		char[] reverse = new char[word.length()];
		for (int i =0;i<word.length();i++) {
			reverse[i]=word.charAt(word.length()-i-1);
		}
		String reversed_string = new String(reverse);
		return reversed_string;
	}
	

}
