package il.ac.tau.cs.sw1.hw3;

import java.util.Arrays;

public class ArrayUtils {
	
	public static void main(String[] args) {
		return;
	}

	public static int[][] transposeMatrix(int[][] m) {
		if (m.length==0) {
			return m;
		}
		int[][] transmat = new int [m[0].length][m.length];
		for (int i=0; i<m[0].length; i++) {
			for (int j=0; j<m.length; j++) {
				transmat[i][j]=m[j][i];
			}
		}
		return transmat; //Replace this with the correct returned value

	}

	public static int[] shiftArrayCyclic(int[] array, int move, char direction) {
		if ((array.length ==0) || ((direction!='L') && (direction!='R'))) {
			return array;
		}
		if ((move <0) && (direction == 'R')) {
			move = Math.abs(move);
			direction = 'L';
		}
		if ((move <0) && (direction == 'L')) {
			move = Math.abs(move);
			direction = 'R';
		}
		int[] aide = new int [array.length];
		System.arraycopy(array, 0, aide, 0, array.length);
		if (direction == 'R') {
			for (int i=0; i<array.length; i++) {
			array[Math.floorMod(i+move, array.length)]=aide[i];
			}
			}
		if (direction == 'L') {
			for (int i=0; i<array.length; i++) {
			array[Math.floorMod(i-move, array.length)]=aide[i];
			}
		
		}
		return array;
	}

	
	public static int alternateSum(int[] array) {
		if (array.length==0) {
			return 0;
		}
		return alternateSumRec(array, 0, 1);

	}
	
	public static int alternateSumRec(int[] array, int sum, int sign) {
		if (array.length == 0) {
			return sum;
		}
		int with_first = alternateSumRec(Arrays.copyOfRange(array, 1, array.length), sum+sign*array[0], -1*sign);
		int without_first = alternateSumRec(Arrays.copyOfRange(array, 1, array.length), 0, 1);
		int finalstop = sum;
		return Math.max(Math.max(with_first, without_first), finalstop);
	}

	public static int findPath(int[][] m, int i, int j, int k) {
		if (k==0) {
			return 0;
		}
		if (k==1 && m[i][j]==1) {
			return 1;
		}
		if ((k>0) && (i==j) && m[i][j]==1) {
			return 1;
		}
		int sum = 0;
		for (int count = 0;count<m[i].length; count++) {
			if (m[i][count]==1) {
				sum = Math.max(sum,findPath(m, count, j, k-1));
				}
			}
				
		if (sum>0) {
			sum=1;
		}
		return sum;

	}
	
		}

