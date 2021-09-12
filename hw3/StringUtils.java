package il.ac.tau.cs.sw1.hw3;

import java.lang.reflect.Array;
import java.util.Arrays;

public class StringUtils {

	public static String findSortedSequence(String str) {
		String dup = str;
		if (dup.replace(" ", "").equals("") || str.equals("")) {
			return ""; //cover all cases of empty string
		}
		String arr[] = str.split(" "); //all the strings' words
		int[] indic = new int [2]; //first index: how long the seq, second: what index to start from
		int consecutive = 0; //to check max seq lexically sorted
		for (int i = 0; i<arr.length-1; i++) {
			if(arr[i].compareTo(arr[i+1])<=0){
				consecutive +=1;
				if (i==arr.length-2) {
					consecutive+=1;
					indic[0]=consecutive;
					indic[1] = i+1-consecutive+1;
				}
			} else {
				if (consecutive+1>=indic[0]&&consecutive>0) {
					if (i<arr.length-2) {
						consecutive+=1;
						indic[0]=consecutive;
						indic[1] = i-consecutive+1;
						consecutive=0;
					} if(i==arr.length-2) {
						consecutive+=1;
						indic[0]=consecutive;
						indic[1] = i-consecutive+1;
						consecutive=0;
					}
					}
				}
			}
		if (indic[0]==0) {
			return arr[arr.length-1];
		}
		String [] seqarray = new String[indic[0]];
		System.arraycopy(arr,indic[1], seqarray, 0, indic[0]);
		String res = String.join(" ", seqarray);
		return res; //Replace this with the correct returned value

	}

	public static boolean isAnagram(String a, String b) {
		//base case empty
		// lower/upper difference
		String dup1 = a;
		String dup2 = b;
		boolean isEmpty1 = dup1.replace(" ", "").equals("") || a.equals("");
		boolean isEmpty2 = dup2.replace(" ", "").equals("") || b.equals("");
		if (isEmpty1 && isEmpty2) {
			return true;
		}
		if ((isEmpty1 && !isEmpty2) || (isEmpty2 && !isEmpty1)) {
			return false;
		}
		a = a.replace(" ", "");
		a = a.toLowerCase();
		b = b.replace(" ", "");
		b = b.toLowerCase();
		char [] first_arr = a.toCharArray();
		char [] second_arr = b.toCharArray();
		Arrays.sort(first_arr);
		Arrays.sort(second_arr);
		boolean isanagram = (Arrays.equals(first_arr, second_arr));
		return isanagram; //Replace this with the correct returned value
	}
	
	public static boolean isEditDistanceOne(String a, String b){
		if (a == b) {
			return true;
		}
		for (int i = 0; i<a.length();i++) {
			for (int j=0; j<b.length();j++) {
				if(replacechar(a,i,b.charAt(j)).equals(b)||addchar(a,i,b.charAt(j)).equals(b)) {
					return true;
				}
			}
		}
		for (int i = 0; i<b.length();i++) {
			for (int j=0; j<a.length();j++) {
				if(replacechar(b,i,a.charAt(j)).equals(a)||addchar(b,i,a.charAt(j)).equals(a)) {
					return true;
				}
			}
		}
		for (int i = 0; i<a.length();i++) {
			if (deletechar(a,i).equals(b) ) {
				return true;
			}
		}
		for (int i = 0; i<b.length();i++) {
			if (deletechar(b,i).equals(a) ) {
				return true;
			}
		}
		return false; //Replace this with the correct returned value
	}
	
	public static String replacechar(String orig, int index, char c) {
		String firstsub = orig.substring(0,index);
		String secondsub = orig.substring(index+1);
		return firstsub+c+secondsub;
	}
	
	public static String addchar(String orig, int index, char c) {
		String firstsub = orig.substring(0,index);
		String secondsub = orig.substring(index);
		return firstsub+c+secondsub;
	}
	
	public static String deletechar(String orig, int index) {
		String firstsub = orig.substring(0,index);
		String secondsub = orig.substring(index+1);
		return firstsub+secondsub;
	}
	
}
