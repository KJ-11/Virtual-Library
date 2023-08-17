import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class for tester methods for the BookMapperBackend
 * 
 * @author kshitij
 *
 */
public class BDTest {
  /**
   * Method to test the addBook() and getNumberofBooks() methods
   * 
   * @return True if all tests pass, false otherwise
   */
  public static boolean test1() {

    BookMapperBackend testBookMapperBD = new BookMapperBackend(true); // instantiates backend

    // tests if backend is empty and has no books
    if (testBookMapperBD.getNumberOfBooks() != 0) {
      System.out.println("Method didn't return expected value 0 when called on an empty backend.");
      return false;
    }

    // tests if book is added to backend correctly and checks if the size updates accordingly
    IBook testBook1 = new BookBD("title1", "author1", "ISBN1");
    testBookMapperBD.addBook(testBook1);
    if (testBookMapperBD.getNumberOfBooks() != 1) {
      System.out
          .println("Method didn't return expected value 1 when called on a backend with 1 book.");
      return false;
    }

    // tests if book is added to backend correctly and checks if the size updates accordingly
    IBook testBook2 = new BookBD("title2", "author2", "ISBN2");
    testBookMapperBD.addBook(testBook2);
    if (testBookMapperBD.getNumberOfBooks() != 2) {
      System.out
          .println("Method didn't return expected value 2 when called on a backend with 2 books.");
      return false;
    }

    return true; // all tests passed
  }

  /**
   * Method to test the getAuthorFilter() and setAuthorFilter() methods
   * 
   * @return True if all tests pass, false otherwise
   */
  public static boolean test2() {

    BookMapperBackend testBookMapperBD = new BookMapperBackend(true); // instantiates backend

    // tests if author filter is set to null by default
    if (testBookMapperBD.getAuthorFilter() != null) {
      System.out.println("Author filter didn't return expected value of null "
          + "when called on a default backend.");
      return false;
    }

    testBookMapperBD.setAuthorFilter("author1");

    // tests if author filter was set correctly
    if (testBookMapperBD.getAuthorFilter() != "author1") {
      System.out.println(
          "Author filter didn't return expected value when called on a backend with a filter.");
      return false;
    }

    testBookMapperBD.resetAuthorFilter();

    // tests if author filter was reset correctly
    if (testBookMapperBD.getAuthorFilter() != null) {
      System.out.println(
          "Author filter didn't return expected value when called on a backend with no sfilter.");
      return false;
    }

    return true;
  }

  /**
   * Method to test the searchByTitleWord() method
   * 
   * @return True if all tests pass, false otherwise
   */
  public static boolean test3() {

    BookMapperBackend testBookMapperBD = new BookMapperBackend(true); // instantiates backend

    // searchByTitleWord() currently iterates over a hardcoded list of books as defined in the
    // iterator() method in the hashtablemap implementation

    IBook bookReturned = (IBook) testBookMapperBD.searchByTitleWord("title").get(0); // should
                                                                                     // ideally
                                                                                     // return
                                                                                     // testBook1 as
                                                                                     // defined in
                                                                                     // the
                                                                                     // hashtable
                                                                                     // placeholder

    if (!(bookReturned.getAuthors().equals("author1"))) {
      System.out.println("searchByTitleWord didn't return expected author name when called.");
      return false;
    }

    if (!(bookReturned.getTitle().equals("title1"))) {
      System.out.println("searchByTitleWord didn't return expected title when called.");
      return false;
    }

    if (!(bookReturned.getISBN13().equals("ISBN1"))) {
      System.out.println("searchByTitleWord didn't return expected ISBN when called.");
      return false;
    }

    IBook bookReturned2 = (IBook) testBookMapperBD.searchByTitleWord("title").get(1); // should
                                                                                      // ideally
                                                                                      // return
                                                                                      // testBook2
                                                                                      // as defined
                                                                                      // in the
                                                                                      // hashtable
                                                                                      // placeholder

    if (!(bookReturned2.getAuthors().equals("author2"))) {
      System.out.println("searchByTitleWord didn't return expected author name when called.");
      return false;
    }

    if (!(bookReturned2.getTitle().equals("title2"))) {
      System.out.println("searchByTitleWord didn't return expected title when called.");
      return false;
    }

    if (!(bookReturned2.getISBN13().equals("ISBN2"))) {
      System.out.println("searchByTitleWord didn't return expected ISBN when called.");
      return false;
    }


    return true; // all tests passed


  }

  /**
   * Method to test the getByISBN() method
   * 
   * @return True if all tests pass, false otherwise
   */
  public static boolean test4() {

    BookMapperBackend testBookMapperBD = new BookMapperBackend(true); // instantiates backend

    IBook testBook1 = new BookBD("title1", "author1", "ISBN1"); // creates book to be added
    testBookMapperBD.addBook(testBook1); // adds book to backend
    IBook bookReturned = testBookMapperBD.getByISBN("ISBN1"); // searches and returns book by ISBN

    if (!(bookReturned.getAuthors().equals(testBook1.getAuthors()))) {
      System.out.println("getByISBN didn't return expected author name when called.");
      return false;
    }

    if (!(bookReturned.getTitle().equals(testBook1.getTitle()))) {
      System.out.println("getByISBN didn't return expected title when called.");
      return false;
    }

    if (!(bookReturned.getISBN13().equals(testBook1.getISBN13()))) {
      System.out.println("getByISBN didn't return expected ISBN when called.");
      return false;
    }

    return true; // all tests passed
  }

  /**
   * Method to test the iterator()
   * 
   * @return True if all tests pass, false otherwise
   */
  public static boolean test5() {
    HashtableMapBD newHashtable = new HashtableMapBD();
    Iterator newIterator = newHashtable.iterator();
    IBook firstBook = (IBook) newIterator.next(); // iterates over hardcoded list
    IBook secondBook = (IBook) newIterator.next();
    IBook thirdBook = (IBook) newIterator.next();


    if (!firstBook.getAuthors().equals("author1")) {
      System.out.println("Your iterator didn't return the expected object");
      return false;
    }
    if (!firstBook.getTitle().equals("title1")) {
      System.out.println("Your iterator didn't return the expected object");
      return false;
    }
    if (!firstBook.getISBN13().equals("ISBN1")) {
      System.out.println("Your iterator didn't return the expected object");
      return false;
    }

    if (!secondBook.getAuthors().equals("author2")) {
      System.out.println("Your iterator didn't return the expected object");
      return false;
    }
    if (!secondBook.getTitle().equals("title2")) {
      System.out.println("Your iterator didn't return the expected object");
      return false;
    }
    if (!secondBook.getISBN13().equals("ISBN2")) {
      System.out.println("Your iterator didn't return the expected object");
      return false;
    }

    if (!thirdBook.getAuthors().equals("author3")) {
      System.out.println("Your iterator didn't return the expected object");
      return false;
    }
    if (!thirdBook.getTitle().equals("title3")) {
      System.out.println("Your iterator didn't return the expected object");
      return false;
    }
    if (!thirdBook.getISBN13().equals("ISBN3")) {
      System.out.println("Your iterator didn't return the expected object");
      return false;
    }

    return true; // all tests passed
  }

  /**
   * Method to test if code works when incorporated with AE and DW
   * 
   * @return True if all tests pass, false otherwise
   */
  public static boolean test6() {

    BookLoader bookLoader = new BookLoader(); // instantiates bookLoader
    BookMapperBackend testBookMapperBD = new BookMapperBackend(); // instantiates backend

    try {
      List<IBook> bookList = bookLoader.loadBooks("books.csv"); // reads books
      for (IBook book : bookList)
        testBookMapperBD.addBook(book); // adds books to backend
    } catch (Exception e) {
      System.out.println("Loading books threw an unexpected exception.");
      return false;
    }

    testBookMapperBD.setAuthorFilter("James Joyce"); // sets filter
    List<IBook> returnedList = testBookMapperBD.searchByTitleWord(" \n"); // empty search

    // expected value checked from csv file
    if (returnedList.size() != 12) {
      System.out.println(
          "Correct number of books weren't returned when searched with author filter James Joyce.");
      return false;
    }

    testBookMapperBD.setAuthorFilter("Rowling"); // sets filter
    returnedList = testBookMapperBD.searchByTitleWord("Harry Potter"); // search with Harry Potter

    // expected value checked from csv file
    if (returnedList.size() != 20) {
      System.out.println(
          "Correct number of books weren't returned when searched with author filter Rowling.");
      return false;
    }

    return true;
  }

  /**
   * Method to test if code works when incorporated with AE and DW
   * 
   * @return True if all tests pass, false otherwise
   */
  public static boolean test7() {

    BookLoader bookLoader = new BookLoader(); // instantiates bookLoader
    BookMapperBackend testBookMapperBD = new BookMapperBackend(); // instantiates backend

    try {
      List<IBook> bookList = bookLoader.loadBooks("books.csv"); // reads books
      for (IBook book : bookList)
        testBookMapperBD.addBook(book); // adds books to backend
    } catch (Exception e) {
      System.out.println("Loading books threw an unexpected exception.");
      return false;
    }

    IBook bookReturned = testBookMapperBD.getByISBN("9780439785969");

    if ((!(bookReturned.getTitle()
        .equals("Harry Potter and the Half-Blood Prince (Harry Potter  #6)")))
        && (!(bookReturned.getAuthors().equals("J.K. Rowling/Mary GrandPré")))) {
      System.out.println("getbyISBN didn't return expected book (9780439785969).");
      return false;
    }

    bookReturned = testBookMapperBD.getByISBN("9780618517657");

    if ((!(bookReturned.getTitle().equals("The Lord of the Rings (The Lord of the Rings  #1-3)")))
        && (!(bookReturned.getAuthors().equals("J.R.R. Tolkien")))) {
      System.out.println("getbyISBN didn't return expected book (9780618517657).");
      return false;
    }

    return true;
  }

  /**
   * Method to test if Partner AE Code works correctly
   * 
   * @return True if all tests pass, false otherwise
   */
  public static boolean test8() {

    IterableHashtableMap testMap = new IterableHashtableMap();
    if (testMap.iterator().hasNext()) {
      System.out
          .println("hasNext method does not return expected value when called on an empty map.");
      return false;
    }

    IBook newBook = new Book("title1", "author1", "isbn13");
    testMap.put(1, newBook);
    if (testMap.iterator().hasNext() == false) {
      System.out.println(
          "hasNext method does not return expected value when called on a map with one item.");
      return false;
    }
    return true;


  }

  /**
   * Method to test if Partner AE Code works correctly
   * 
   * @return True if all tests pass, false otherwise
   */
  public static boolean test9() {

    IterableHashtableMap testMap = new IterableHashtableMap();
    
    try {
      testMap.iterator().next();
      System.out
      .println("next method does not throw expected exception when called on an empty map.");
      return false;
    } catch (NoSuchElementException e) {
      // do nothing works as intended
    } catch (Exception e) {
      System.out
          .println("next method throws unexpected exception when called on an empty map.");
      return false;
    }

    IBook newBook = new Book("title1", "author1", "isbn13");
    testMap.put(1, newBook);
    
    if (testMap.iterator().next() != newBook) {
      System.out.println(
          "next method does not return expected value when called on a map with one item.");
      return false;
    }

    return true;
  }



  public static void main(String[] args) {
    System.out.println("BackendDeveloper Individual Test 1: " + (test1() ? "passed" : "failed"));
    System.out.println("BackendDeveloper Individual Test 2: " + (test2() ? "passed" : "failed"));
    System.out.println("BackendDeveloper Individual Test 3: " + (test3() ? "passed" : "failed"));
    System.out.println("BackendDeveloper Individual Test 4: " + (test4() ? "passed" : "failed"));
    System.out.println("BackendDeveloper Individual Test 5: " + (test5() ? "passed" : "failed"));
    System.out.println("BackendDeveloper Integration Test 1: " + (test6() ? "passed" : "failed"));
    System.out.println("BackendDeveloper Integration Test 2: " + (test7() ? "passed" : "failed"));
    System.out.println(
        "BackendDeveloper Partner (AlgorithmEngineer) Test 1: " + (test8() ? "passed" : "failed"));
    System.out.println(
        "BackendDeveloper Partner (AlgorithmEngineer) Test 2: " + (test9() ? "passed" : "failed"));
  }
}
