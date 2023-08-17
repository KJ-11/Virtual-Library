
/**
 * Placeholder book class
 * @author kshitij
 *
 */
public class BookBD implements IBook{

  // Placeholder fields
  public String title;
  public String authors;
  public String ISBN;
  
  /**
   * Placeholder constructor
   * @param title title of book
   * @param authors authors of book
   * @param ISBN ISBN of book
   */
  public BookBD(String title, String authors, String ISBN) {
    this.title = title;
    this.authors = authors;
    this.ISBN =  ISBN;
    
  }
  
  /**
   * Placeholder for getTitle()
   */
  @Override
  public String getTitle() {
    return this.title;
  }

  /**
   * Placeholder for getAuthors()
   */
  @Override
  public String getAuthors() {
    return this.authors;
  }

  /**
   * Placeholder for getISBN13()
   */
  @Override
  public String getISBN13() {
    return this.ISBN;
  }

}
