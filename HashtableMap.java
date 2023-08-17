// --== CS400 Project One File Header ==--
// Name: Sreyas Srivastava
// CSL Username: sreyas
// Email: sssrivastav2@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader:

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class defines a hashtable data structure implementing the MapADT interface
 * 
 * @author Sreyas Srivastava
 *
 * @param <KeyType> generic type to store keys
 * @param <ValueType> generic type to store values
 */
public class HashtableMap <KeyType, ValueType> implements MapADT <KeyType, ValueType> {
	protected LinkedList<HashObjectHelper>[] hashTable; // to store the whole hashtable
	
	/**
	 * Constructor to create a new hashtable with a specific capacity
	 * 
	 * @param capacity capacity of the hashtable to be created
	 */
	@SuppressWarnings("unchecked")
	public HashtableMap(int capacity) {
		this.hashTable = (LinkedList<HashObjectHelper>[]) new LinkedList[capacity];
	}
	
	/**
	 * Default constructor to create a new hashtable with a default capacity of 15
	 */
	@SuppressWarnings("unchecked")
	public HashtableMap() {
		this.hashTable = (LinkedList<HashObjectHelper>[]) new LinkedList[15]; 
		// with default capacity = 15
	}
	
	/**
	 * Inserts a new (key, value) pair into the map if the map does not
	 * contain a value mapped to key yet.
	 * 
	 * @param key the key of the (key, value) pair to store
	 * @param value the value that the key will map to
	 * @return true if the (key, value) pair was inserted into the map,
	 *         false if a mapping for key already exists and the
	 *         new (key, value) pair could not be inserted
	 */
	@Override
	public boolean put(KeyType key, ValueType value) {
		if (key == null || this.containsKey(key)) {
			return false;
		}
		
		HashObjectHelper<KeyType, ValueType> newEntry = new 
				HashObjectHelper<KeyType, ValueType>(key, value);
		int indexToAdd = Math.abs(key.hashCode()) % this.hashTable.length;
		if (this.hashTable[indexToAdd] != null) {
			// i.e. when a linked list exists at the index
			this.hashTable[indexToAdd].add(newEntry);
			// check to see if the hashtable needs to be expanded
			if (this.loadFactor() >= 0.7) {
				this.rehash();
			}
		} else {
			// create a linked list at the index to store the (key, value) pairs
			this.hashTable[indexToAdd] = new LinkedList<HashObjectHelper>();
			this.hashTable[indexToAdd].add(newEntry);
			// check to see if the hashtable needs to be expanded
			if (this.loadFactor() >= 0.7) {
				this.rehash();
			}
		}
		
		return true;
	}
	
	/**
	 * Returns the value mapped to a key if the map contains such a mapping.
	 * 
	 * @param key the key for which to look up the value
	 * @return the value mapped to the key
	 * @throws NoSuchElementException if the map does not contain a mapping
	 *                                for the key
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ValueType get(KeyType key) throws NoSuchElementException {
		if (key == null) {
			throw new NoSuchElementException();
		}
		for (int i = 0; i < this.hashTable.length; i++) {
			if (this.hashTable[i] == null) {
				continue;
			}
			for (int j = 0; j < this.hashTable[i].size(); j++) {
				// looking for the key in the linked list at index i of the hashtable
				if (((HashObjectHelper) this.hashTable[i].get(j)).getKey().equals(key)) {
					return (ValueType)(((HashObjectHelper) this.hashTable[i].get(j)).getValue());
				}
			}
		}
		// if (key, value) pair is not found
		throw new NoSuchElementException("Map does not contain a mapping for the key");
	}
	
	/**
	 * Private method that calculates the load factor of the hashtable
	 * 
	 * @return load factor of the hashtable
	 */
	private double loadFactor() {
		return ((double) this.size() / this.hashTable.length);
	}
	
	/**
	 * Private method that rehashes the dynamically growing array by assigning (key, value) 
	 * pairs to new indexes
	 */
	@SuppressWarnings("unchecked")
	private void rehash() {
		LinkedList<HashObjectHelper>[] newHashTable = (LinkedList<HashObjectHelper>[]) 
				new LinkedList[this.hashTable.length * 2]; // hashtable with double capacity
		for (int i = 0; i < this.hashTable.length; i++) {
			if (this.hashTable[i] == null) {
				continue;
			}
			for (int j = 0; j < this.hashTable[i].size(); j++) {
				if (this.hashTable[i].get(j) == null) {
					continue;
				}
				int indexToAdd = Math.abs((this.hashTable[i].get(j).getKey()).hashCode()) 
						% newHashTable.length;
				if (newHashTable[indexToAdd] != null) {
					// i.e. when a linked list exists at the index
					newHashTable[indexToAdd].add(this.hashTable[i].get(j));
				} else {
					// create a linked list at the index to store the (key, value) pairs
					newHashTable[indexToAdd] = new LinkedList<HashObjectHelper>();
					newHashTable[indexToAdd].add(this.hashTable[i].get(j));
				}
			}
		}
		this.hashTable = newHashTable;
	}
	
	/**
	 * Removes a key and its value from the map. 
	 * 
	 * @param key the key for the (key, value) pair to remove
	 * @return the value for the (key, value) pair that was removed,
	 *         or null if the map did not contain a mapping for key
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ValueType remove(KeyType key) {
		if (!this.containsKey(key)) {
			return null;
		}
		ValueType val = null; // to store the value to be returned
		for (int i = 0; i < this.hashTable.length; i++) {
			if (this.hashTable[i] == null) {
				continue;
			}
			for (int j = 0; j < this.hashTable[i].size(); j++) {
				// looking for the key in the linked list at index i of the hashtable
				if (((HashObjectHelper) this.hashTable[i].get(j)).getKey().equals(key)) {
					val = (ValueType)(((HashObjectHelper) this.hashTable[i].get(j))
							.getValue());
					this.hashTable[i].remove(j); // removing the object from the linked list
					break;
				}
			}
			if (val != null) {
				break;
			}
		}
		return val;
	}
	
	/**
	 * Checks if a key is stored in the map.
	 * 
	 * @param key the key to check for
	 * @return true if the key is stored (mapped to a value) by the map
	 *         and false otherwise
	 */
	@Override
	public boolean containsKey(KeyType key) {
		for (int i = 0; i < this.hashTable.length; i++) {
			if (this.hashTable[i] == null) {
				continue;
			}
			for (int j = 0; j < this.hashTable[i].size(); j++) {
				// looking for the key in the linked list at index i of the hashtable
				if (((HashObjectHelper) this.hashTable[i].get(j)).getKey().equals(key)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns the number of (key, value) pairs stored in the map.
	 * 
	 * @return the number of (key, value) pairs stored in the map
	 */
	@Override
	public int size() {
		int numPairs = 0; // to store the number of (key, value) pairs
		for (int i = 0; i < this.hashTable.length; i++) {
			if (this.hashTable[i] != null) {
				for (int j = 0;  j < this.hashTable[i].size(); j++) {
					numPairs++;
				}
			}
		}
		return numPairs;
	}
	
	/**
	 * Removes all (key, value) pairs from the map.
	 */
	@Override
	public void clear() {
		for (int i = 0; i < this.hashTable.length; i++) {
			if (this.hashTable[i] == null) {
				continue;
			}
			this.hashTable[i].clear(); // clearing the linked list at index i of the hashtable
		}
	}
}
