package il.ac.tau.cs.sw1.hw6;

public class SectionB {
	
	/*
	* @post $ret == true iff exists i such that array[i] == value
	*/
	public static boolean contains(int[] array, int value) { 
		for (int i = 0;i<array.length;i++) {
			if (array[i]==value) {
				return true;
			}
		}
		return false;
	}
	
	// there is intentionally no @post condition here 
	/*
	* @pre array != null
	* @pre array.length > 2
	* @pre Arrays.equals(array, Arrays.sort(array))
	*/
	public static int unknown(int[] array) { 
		//return max number
		return array[array.length-1];
	}
	/*
	* @pre Arrays.equals(array, Arrays.sort(array))
	* @pre array.length >= 1
	* @post for all i array[i] < $ret
	*/
	public static int max(int[] array) { 
		return array[array.length-1]+1;
	}
	
	/*
	* @pre array.length >=1
	* @post for all i array[i] >= $ret
	* @post Arrays.equals(array, prev(array))
	*/
	public static int min(int[] array) { 
		int min = array[0];
		for (int i=0;i<array.length;i++) {
			if (array[i]<min) {
				min = array[i];
			}
		}
		return min;
	}
	
	/*
	* @pre word.length() >=1
	* @post for all i : $ret.charAt(i) == word.charAt(a.length() - i - 1)

	*/
	public static String reverse(String word) 
	{
		char[] reverse = new char[word.length()];
		for (int i =0;i<word.length();i++) {
			reverse[i]=word.charAt(word.length()-i-1);
		}
		String reversed_string = new String(reverse);
		return reversed_string;
	}
	
	/*
	* @pre array != null
	* @pre array.length > 2
	* @pre Arrays.equals(array, Arrays.sort(array))
	* @pre exist i,j such that: array[i] != array[j]
	* @post !Arrays.equals($ret, Arrays.sort($ret))
	* @post for any x: contains(prev(array),x) == true iff contains($ret, x) == true
	*/
	public static int[] guess(int[] array) { 
		for (int i=0;i<array.length-1;i++) {
			if (array[i]!=array[i+1]) {
				int first = array[i];
				int second = array[i+1];
				array[i]=second;
				array[i+1]=first;
				break;
			}
		}
		return array;
	}


}
