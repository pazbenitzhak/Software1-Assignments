package il.ac.tau.cs.sw1.riddle.a;

public class A {
	
	private B b;
	
	public A(B b) {
		this.b = b;
	}

	public void printA() {
		System.out.println("A1");
		int out = this.b.getIPlus()-1;
		System.out.println(out);
		System.out.println("A2");
	}
}
