// --== CS400 Project One File Header ==--
// Name: Henry Burke
// CSL Username: hburke
// Email: hpburke@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: did you eat enough today?

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Opens and reads a CSV file to gather book data.
 */
public class BookLoader implements IBookLoader {

    /**
     * Creates a list of books in a CSV file.
     *
     * @param filepathToCSV path to the CSV file relative to the executable
     * @return a list of book objects in the CSV file
     * @throws FileNotFoundException when path is invalid
     */
    @Override
    public List<IBook> loadBooks(String filepathToCSV) throws FileNotFoundException {
        try {
            // initializes the list we will be returning.
            List<IBook> bookList = new ArrayList<>();

            // initializes scanner to read the csv file.
            Scanner scnr = new Scanner(new File(filepathToCSV), "UTF-8");

            // moves past headers
            scnr.nextLine();

            // next now operates with commas instead of whitespace
            scnr.useDelimiter(",");


            // outer while loop
            // iterates through every line of the csv file and makes IBook objects to add to bookList
            while (scnr.hasNextLine()) {
                // moves past book ID
                scnr.next();

                // saves book title
                String title = scnr.next();

                // deletes back-slashes from titles
                title = title.replace("\\", "");

                // saves authors
                String authors = scnr.next();


                // inner while loop
                // checks the next element to make sure it is the rating and not part of the authors
                while (true) {
                    String commaCheck = scnr.next();

                    try {
                        // next element is the rating -- break
                        double rating = Double.parseDouble(commaCheck);
                        break;
                    } catch (NumberFormatException e) {
                        // next element is part of the authors -- add to author string
                        authors = authors + "," + commaCheck;
                    }
                }


                // delete quotes from author string
                authors = authors.replace("\"", "");

                // moves past isbn to get isbn13
                scnr.next();

                // saves isbn13 number
                String isbn13 = scnr.next();

                // moves to next book in csv file
                scnr.nextLine();

                // adds book data to List
                IBook currBook = new Book(isbn13, title, authors);

                // adds book to bookList
                bookList.add(currBook);
            }

            // returns bookList to wherever it is called
            return bookList;

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File Path Incorrect.");
        }
    }
}

