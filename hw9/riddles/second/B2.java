package il.ac.tau.cs.sw1.ex9.riddles.second;

public class B2  extends A2{
	
	private boolean indicator;
	
	public B2() {
		this.indicator=false;
	}
	
	public B2 (boolean indic) {
		this.indicator=indic;
	}
	
	public String foo(String input) {
		if (this.indicator==true) {
			return input.toUpperCase();
		} else {//indicator is false
			return super.foo(input);
		}
	}
	
	public B2 getA(boolean bool) {
		B2 b = new B2(bool);
		return b;
	}
	
}
