// --== CS400 Project One File Header ==--
// Name: <Kshitij Jhunjhunwala>
// CSL Username: <kshitij>
// Email: <kjhunjhunwa2@wisc.edu>
// Lecture #: <001 @11:00am>
// Notes to Grader: <any optional extra notes to your grader>

/**
 * Class for key value object (HashEntry)
 * 
 * @author kshitij
 */
public class HashEntryBD<KeyType, ValueType> {

  private KeyType key; // key instance field
  private ValueType value; // value instance field
  
/**
 * Constructor for HashEntry object
 * @param key
 * @param value
 */
  public HashEntryBD(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
  }

  /**
   * Method to get the key stored in the object
   * @return KeyType the key stored
   */
  public KeyType getKey() {
    return this.key;
  }

  /**
   * Method to get the value stored in the object
   * @return ValueType the value stored in the object
   */
  public ValueType getValue() {
    return this.value;
  }

}
