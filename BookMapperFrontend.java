// --== CS400 Project One File Header ==--
// Name: Michael Deng
// CSL Username: mdeng
// Email: madeng@wisc.edu
// Lecture #: <001 @11:00am>
// Notes to Grader: <any optional extra notes to your grader>import java.util.List;
import java.util.List;
import java.util.Scanner;

/**
 * Implements methods detailed in IBookMapperFrontend
 * @author Michael
 **/
public class BookMapperFrontend implements IBookMapperFrontend {
    IBookMapperBackend backend;
    Scanner inputScanner;
    IISBNValidator validator;

    /*
     * The constructor that the implementation this interface will provide. It takes
     * the Scanner that will read user input as a parameter as well as the backend
     * and the ISBNnalidator.
     */
    BookMapperFrontend(Scanner userInputScanner, IBookMapperBackend backend, IISBNValidator validator) {
        this.inputScanner = userInputScanner;
        this.backend = backend;
        this.validator = validator;
    }

    /**
     * This method starts the command loop for the frontend, and will terminate when
     * the user exists the app.
     */
    public void runCommandLoop() {
        System.out.print("Welcome to the Book Mapper Application!\r\n" + "x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\r\n");
        int input = 0;

        while (input != 4) {
            displayMainMenu();
            input = inputScanner.nextInt();
            inputScanner.nextLine();

            if (input == 1)
                isbnLookup();

            else if (input == 2)
                titleSearch();

            else if (input == 3) {
                System.out.print("You are in the Set Author Filter Menu:\r\n"
                        + "          Author name must currently contain: " + backend.getAuthorFilter()
                        + "          \nEnter a new string for author names to contain (empty for any):\r\n");
                String author = inputScanner.nextLine();
                if (!author.equals(""))
                    backend.setAuthorFilter(author);
                else
                    backend.resetAuthorFilter();
            }

            else if (input != 4){
                System.out.print("Please enter a valid integer option\r\n");
            }
        }
    }

    /**
     * Displays main menu by printing command optiosn to System.out. Support me thod for runCommandLoop()
     */
    public void displayMainMenu() {
        System.out.print("You are in the Main Menu:\r\n" + "          1) Lookup ISBN\r\n"
                + "          2) Search by Title Word\r\n" + "          3) Set Author Name Filter\r\n"
                + "          4) Exit Application\r\n");
    }

    /** displays a list of books
     * @param books the books to display
     **/
    public void displayBooks(List<IBook> books) {
        int i = 1;
        for (IBook book : books) {
            System.out.print(i + ". " + book.getTitle() + " by " + book.getAuthors() + ", ISBN: " + book.getISBN13() + "\r\n");
            i++;
        }
    }

    /** Reads isbn from system.in, and prints out a book satisfying that ISBN by utilizing the backend interface.
     **/
    public void isbnLookup() {
        String input;
        System.out.print("You are in the Lookup ISBN Menu:\n" + "            Enter ISBN to look up:\r\n");
        input = inputScanner.next();

        if (validator.validate(input)) {
            IBook book = backend.getByISBN(input);
            System.out.print("1. " + "\"" + book.getTitle() + "\"" + " by " + book.getAuthors() + ", ISBN: " + input + "\r\n");
        } else {
            System.out.print("Please enter a valid ISBN number\r\n");
        }
    }

    /** Reads a keyword from system.in, and prints a list of books which contain that keyword in their titles and also satsify the author filter. 
     *
     **/
    public void titleSearch() {
        String input;
        System.out.print("You are in the Search for Title Word Menu:\r\n"
                + "          Enter a word to search for in book titles (empty for all books):\r\n");
        input = inputScanner.nextLine();
        List<IBook> books = backend.searchByTitleWord(input);

        if (books != null) {
            System.out.print(
                    "Matches (author filter: " + backend.getAuthorFilter() + ") " + books.size() + " of " + backend.getNumberOfBooks() + "\r\n");
            displayBooks(books);
        } else
            System.out.print("Matches (author filter: " + backend.getAuthorFilter() + ") 0 of " + backend.getNumberOfBooks() + "\r\n");

    }
}
