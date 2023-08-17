/**
 * Private helper class to create an IBook type object for the test methods
 * 
 * @author Sreyas Srivastava
 *
 */
public class Book implements IBook {
	private String isbn; // to store ISBN number
	private String title; // to store titles
	private String authors; // to store authors

	public Book (String isbn, String title, String authors) {
		this.isbn = isbn;
		this.title = title;
		this.authors = authors;
	}

	/**
	 * Returns the title of the book
	 * 
	 * @return title of the book
	 */
	@Override
	public String getTitle() {
		return this.title;
	}

	/**
	 * Returns the author of the book
	 * 
	 * @return author of the book
	 */
	@Override
	public String getAuthors() {
		return this.authors;
	}

	/**
	 * Returns the ISBN number of the book
	 * 
	 * @return ISBN number of the book
	 */
	@Override
	public String getISBN13() {
		return this.isbn;
	}
}