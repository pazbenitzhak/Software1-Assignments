package riddles;

public class C extends B {
	
	private int i;
	private int j;

	public C(int i, int j) {
		super(i,j);
		
	}

	@Override
	public int compareTo(A other) {
		if (this.j>other.j) {
			return 1;
		}
		if (this.j==other.j) {
			return 0;
		}
		else {
			return -1;
		}
	}



}