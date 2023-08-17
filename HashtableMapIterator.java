// --== CS400 Project One File Header ==--
// Name: Sreyas Srivastava
// CSL Username: sreyas
// Email: sssrivastav2@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader:

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class defines the iterator that iterates over the (key, value) pairs of
 * the hashtable
 * 
 * @author Sreyas Srivastava
 *
 */
public class HashtableMapIterator implements Iterator<IBook> {
	
	private LinkedList<HashObjectHelper>[] hash; // to hold the hashtable containing
												 // (key, value) pairs
	private IBook next; // to hold the reference to the next value
	
	/**
	 * Constructor to create a new iterator object that iterates through the (key, value)
	 * pairs of the given hashtable 
	 * 
	 * @param elements hashtable that is to be iterated through
	 */
	public HashtableMapIterator(LinkedList<HashObjectHelper>[] elements) {
		this.hash = elements;
		for (int i = 0; i < hash.length; i++) {
			if (hash[i] == null) {
				continue;
			}
			this.next = (IBook) (hash[i].getFirst().getValue());
			break;
		}
	}
	
	/**
	 * Checks if there are more (key, value) pairs to return
	 * 
	 * @return true if there are more (key, value) pairs to return, and false otherwise
	 */
	@Override
	public boolean hasNext() {
		return this.next != null;
	}
	
	/**
	 * Returns the next (key, value) pair in the hashtable
	 * 
	 * @return next (key, value) pair in the hashtable or null if there are no more
	 *         pairs remaining
	 * @throws NoSuchElementException if the next element does not exist (i.e. is null)
	 */
	@Override
	public IBook next() throws NoSuchElementException {
		if (!this.hasNext()) {
			throw new NoSuchElementException();
		}
		
		// to store the indexes of the linked lists that are not empty 
		// (i.e the non-null indexes of the hashtable)
		ArrayList<Integer> fullIndexes = new ArrayList<Integer>(); 
		for (int i = 0; i < hash.length; i++) {
			if (hash[i] == null) {
				continue;
			} else {
				fullIndexes.add(i);
			}
		}
		
		IBook toReturn = this.next; // (key, value) pair to be returned
		for (int i = 0; i < fullIndexes.size(); i++) {
			for (int j = 0; j < (hash[fullIndexes.get(i)].size()); j++) {
				if (((IBook) (hash[fullIndexes.get(i)].get(j).getValue())).getISBN13()
						.equals((this.next.getISBN13()))) {
					// check to avoid iterating beyond the size of the linked list
					if (j == (hash[fullIndexes.get(i)].size() - 1) 
							|| hash[fullIndexes.get(i)].get(j + 1) == null) {
						toReturn = this.next;
						// check to avoid iterating beyond the size of the hashtable
						if (i == (fullIndexes.size() - 1)) {
							// reached the end of the hashtable
							this.next = null;
						} else {
							// get first (key, value) pair in the next non-empty linked list
							this.next = (IBook) (hash[fullIndexes.get(i + 1)].getFirst().getValue());
							return toReturn;
						}
					} else {
						toReturn = this.next;
						// get next (key, value) pair in the same linked list
						this.next = (IBook) (hash[fullIndexes.get(i)].get(j + 1).getValue());
						return toReturn;
					}
				}
			}
		}
		return toReturn;
	}
}
