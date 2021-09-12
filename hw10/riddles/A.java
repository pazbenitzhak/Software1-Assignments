package riddles;


public class A implements Comparable<A> {
	
	protected int i;
	protected int j;

	public A(int i, int j) {
		this.i = i;
		this.j = j;
	}

	@Override
	public int hashCode() {
		if (this.j%2==0) {
			return 3;
		}
		else {
			return 5;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this==obj) {
			return true;
		}
		if (obj==null) {
			return false;
		}
		if (this.getClass()!=obj.getClass()) {
			return false;
		}
		A o = (A) obj;
		if (this.i==o.i) {
			return true;
		}
		if (this.toString().equals(obj.toString())) {
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(A o) {
		if (this.j>o.j) {
			return 1;
		}
		if (this.j==o.j) {
			return 0;
		}
		else {
			return -1;
		}
	}
	
	
	public String toString() {return "("+this.i+" "+this.j+")";}



}
