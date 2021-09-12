package il.ac.tau.cs.software1.predicate;

public class Discount implements Action<Book> {
	
	private double percent;
	
	public Discount(double percentage) { // Q3
		this.percent = percentage;
	}
	
	
	@Override
	public void performAction(Book book) { // Q3
		double previous_price = book.getPrice();
		book.setPrice(this.percent*previous_price*0.01);
	}
	
}
