package il.ac.tau.cs.sw1.ex9.riddles.third;

public class B3 extends A3 {
	
	private String str;
	
	public B3(String s) {
		super(s);
		this.str = s;
	}
	
	public String getMessage() {
		return this.str;
	}
	
	public void foo(String s) throws B3{
		if (s.equals(this.s)){
			throw new B3(s);
		}
	}
}