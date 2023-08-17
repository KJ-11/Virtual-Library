// --== CS400 Project One File Header ==--
// Name: Sreyas Srivastava
// CSL Username: sreyas
// Email: sssrivastav2@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader:

/**
 * Helper class that combines (key, value) pairs into a single object
 * 
 * @author Sreyas Srivastava
 *
 * @param <KeyType> generic type to store keys
 * @param <ValueType> generic type to store values
 */
public class HashObjectHelper<KeyType, ValueType> {
	private KeyType key; // to store the key
	private ValueType value; // to store the value
	
	/**
	 * Constructor to create a new (key, value) pair as an object
	 * 
	 * @param key key in the object
	 * @param value value in the object
	 */
	public HashObjectHelper(KeyType key, ValueType value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Getter method for the key that is in the object
	 * 
	 * @return key in the object
	 */
	public KeyType getKey() {
		return this.key;
	}
	
	/**
	 * Getter method for the value that is in the object
	 * 
	 * @return value in the object
	 */
	public ValueType getValue() {
		return this.value;
	}
	
	/**
	 * This method links a value to the given key
	 * (optional method)
	 * 
	 * @param value value to be linked to the key
	 */
	public void setValue(ValueType value) {
		this.value = value;
	}
}
