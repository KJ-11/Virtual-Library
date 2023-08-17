// --== CS400 Project One File Header ==--
// Name: Sreyas Srivastava
// CSL Username: sreyas
// Email: sssrivastav2@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader:

import java.util.Iterator;

/**
 * Subclass of HashtableMap that implements the IterableMapADT interface and 
 * allows a user of this class to iterate over the values in the hashtable 
 * (in no specific order)
 * 
 * @author Sreyas Srivastava
 *
 */
public class IterableHashtableMap<KeyType, ValueType> extends HashtableMap implements IterableMapADT {
	
	/**
	 * This method calls the iterator that iterates over the (key, value) pairs
	 * of the hashtable
	 * 
	 * @return iterator to iterate over (key, value) pairs
	 */
	@Override
	public Iterator<IBook> iterator() {
		return new HashtableMapIterator(super.hashTable);
	}
}
