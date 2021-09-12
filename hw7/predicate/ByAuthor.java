package il.ac.tau.cs.software1.predicate;

public class ByAuthor implements Predicate<Book> {
	
	private char check_letter;

	public ByAuthor(char letter) { // Q2
		this.check_letter = letter;
	}

	@Override
	public boolean test(Book book) { // Q2
		String author = book.getAuthor();
		String lower_author = author.toLowerCase();
		char first_letter = lower_author.charAt(0);
		return (first_letter==this.check_letter);
	}
	
}