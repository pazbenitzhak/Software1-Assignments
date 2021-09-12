package riddles;

public class B extends A{
	
	protected int i;
	protected int j;


	public B(int i, int j) {
		super(i,j);
	
	}


	@Override
	public int hashCode() {
		if (this.i%2==0) {
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
		B o = (B) obj;
		if (this.j==o.j) {
			return true;
		}
		if (this.toString().equals(obj.toString())) {
			return true;
		}
		return false;
	}

}
