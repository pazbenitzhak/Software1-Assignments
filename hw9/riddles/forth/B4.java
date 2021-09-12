package il.ac.tau.cs.sw1.ex9.riddles.forth;

import java.util.Iterator;

public class B4 implements Iterator<String>{
	
	private int num;
	private String[] array;
	private int index;
	private int secInd;
	
	public B4(String[] args, int number) {
		this.num = number;
		this.array = args;
	}
	
	public boolean hasNext() {
		return !(this.index==this.array.length-1&&this.secInd==this.num);
	}
	
	public String next() {
		if (this.secInd==this.num) {
			this.secInd=1;
			this.index++;
			return this.array[index];
		}
		this.secInd++;
		String res = this.array[index];
		return res;
	}
	
}
