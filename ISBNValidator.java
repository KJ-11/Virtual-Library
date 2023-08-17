// --== CS400 Project One File Header ==--
// Name: Sreyas Srivastava
// CSL Username: sreyas
// Email: sssrivastav2@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader:

/**
 * This class allows a user to validate the ISBN number of a book
 * 
 * @author Sreyas Srivastava
 *
 */
public class ISBNValidator implements IISBNValidator {
	
	/**
     * Checks if the given ISBN number is a valid ISBN13 number.
     * 
     * @param isbn13 the ISBN number to validate
     * @return true is the number if a valid ISBN number, false otherwise
     */
	@Override
	public boolean validate(String isbn13) {
		if (isbn13.length() != 13) {
			return false;
		}
		int digitSum = 0;
		for (int i = 0; i < 12; i++) {
			if (i % 2 == 0) {
				digitSum+= Character.getNumericValue(isbn13.charAt(i));
			} else {
				digitSum+= 3 * (Character.getNumericValue(isbn13.charAt(i)));
			}
		}
		int expectedLastDigit = 10 - (digitSum % 10);
		int actualLastDigit = Character.getNumericValue(isbn13.charAt(12));
		if (actualLastDigit != expectedLastDigit) {
			return false;
		}
		return true;
	}
}