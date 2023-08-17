import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class for backend that implements the IBookMapperBackend interface
 * 
 * @author kshitij
 *
 */
public class BookMapperBackend<KeyType, ValueType> implements IBookMapperBackend {

  private IterableMapADT bookTable; // instance field for iterableMapADT
  private String authorFilter = null;

  /**
   * Const
   * @param oldPlaceHolder
   */
  public BookMapperBackend(boolean oldPlaceHolder) {
    this.bookTable = (HashtableMapBD<KeyType, ValueType>) new HashtableMapBD(); // instantiates
  }
  /**
   * Constructor for the bookmapperbackend
   */
  public BookMapperBackend() {
    this.bookTable = (IterableHashtableMap<KeyType, ValueType>) new IterableHashtableMap(); 
  }


  /**
   * Adds a new book to the backend's database and is stored in a hash table internally.
   * 
   * @param book the book to add
   */
  @Override
  public void addBook(IBook book) {
    bookTable.put(book.getISBN13(), book); // puts book into hashtable
  }

  /**
   * Returns the number of books stored in the backend's database.
   * 
   * @return the number of books
   */
  @Override
  public int getNumberOfBooks() {
    return this.bookTable.size();
  }

  /**
   * This method can be used to set a filter for the author names contained in the search results. A
   * book is only returned as a result for a search by title, it is also contains the string
   * filterBy in the names of its authors.
   * 
   * @param filterBy the string that the book's author names must contain
   */
  @Override
  public void setAuthorFilter(String filterBy) {
    this.authorFilter = filterBy;
  }

  /**
   * Returns the string used as the author filter, null if no author filter is currently set.
   * 
   * @return the string used as the author filter, or null if none is set
   */
  @Override
  public String getAuthorFilter() {
    return this.authorFilter;
  }

  /**
   * Resets the author filter to null (no filter).
   */
  @Override
  public void resetAuthorFilter() {
    this.authorFilter = null;

  }

  /**
   * Search through all the books in the title base and return books whose title contains the string
   * word (and that satisfies the author filter, if an author filter is set).
   * 
   * @param word word that must be contained in a book's title in result set
   * @return list of books found
   */
  @Override
  public List<IBook> searchByTitleWord(String word) {

    List<IBook> toReturn = new ArrayList<IBook>(); // list to return
    Iterator<IBook> itr = bookTable.iterator(); // iterator
    while (itr.hasNext()) {
      IBook currBook = itr.next(); 
        if (currBook.getTitle().contains(word)) {
          if (this.getAuthorFilter() != null) {
            if (currBook.getAuthors().contains(this.getAuthorFilter())) {
              toReturn.add(currBook);
            }
          } else {
            toReturn.add(currBook);
          }
        }
      }
    return toReturn;
    }


  /**
   * Return the book uniquely identified by the ISBN, or null if ISBN is not present in the dataset.
   * 
   * @param ISBN the book's ISBN number
   * @return the book identified by the ISBN, or null if ISBN not in database
   */
  @Override
  public IBook getByISBN(String ISBN) {
    if (bookTable.containsKey(ISBN)) {
      return (IBook) bookTable.get(ISBN);
    } else {
      return null;
    }
  }
}
