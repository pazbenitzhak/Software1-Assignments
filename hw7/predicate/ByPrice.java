package il.ac.tau.cs.software1.predicate;

public class ByPrice implements Predicate<SmartPhone>{
	
	private double check_price;
	
	public ByPrice(double maxPrice) { // Q2
		this.check_price = maxPrice;
	}

	@Override
	public boolean test(SmartPhone phone) { // Q2
		return (phone.getPrice()<=this.check_price);
	}
	
	

}
