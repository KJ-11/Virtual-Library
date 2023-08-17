// --== CS400 Project One File Header ==--
// Name: <Kshitij Jhunjhunwala>
// CSL Username: <kshitij>
// Email: <kjhunjhunwa2@wisc.edu>
// Lecture #: <001 @11:00am>
// Notes to Grader: <any optional extra notes to your grader>

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/*
 * Class for HashtableMap containing various methods
 */
public class HashtableMapBD<KeyType, ValueType> implements IterableMapADT<KeyType, ValueType> {


  protected LinkedList<HashEntryBD>[] hashtable; // single dimensional array of type
                                               // LinkedList<HashEntry> field

  /**
   * Constructor for creating a hashtable map with given capacity
   * 
   * @param capacity the given capacity of the hashtable (array)
   */
  public HashtableMapBD(int capacity) {

    this.hashtable = (LinkedList<HashEntryBD>[]) new LinkedList[capacity];

    for (int i = 0; i < this.hashtable.length; i++) {
      this.hashtable[i] = null;
    }

  }


  /*
   * Default constructor for creating a hashtable map with default capacity
   */
  public HashtableMapBD() {

    this.hashtable = (LinkedList<HashEntryBD>[]) new LinkedList[15]; // with default capacity = 15

    for (int i = 0; i < this.hashtable.length; i++) {
      this.hashtable[i] = null;
    }

  }

  /**
   * Put method to insert a key value pair into the hashtable
   * 
   * @param key   the key
   * @param value the value
   * @return boolean true if added, false if unable to add
   */
  @Override
  public boolean put(KeyType key, ValueType value) {

    if (key == null || this.containsKey(key)) {
      return false; // invalid key or duplicate key
    } else {
      int hash = Math.abs(key.hashCode());
      int index = (hash % this.hashtable.length); // finds index to put key value pair into
      if (this.hashtable[index] == null) {

        this.hashtable[index] = new LinkedList<HashEntryBD>(); // creates new linkedlist if index has
                                                             // nothing stored already

        HashEntryBD<KeyType, ValueType> newEntry = new HashEntryBD(key, value); // creates new key value
                                                                            // pair object to be
                                                                            // stored
        this.hashtable[index].add(newEntry); // adds new object to linkedlist

        if (this.loadReached() == true) {
          this.rehash(); // rehashes and dynamically grows if load factor of 70% is reached
        }

        return true;

      } else {
        HashEntryBD<KeyType, ValueType> newEntry = new HashEntryBD(key, value); // creates new key value
                                                                            // object to be stored
        this.hashtable[index].add(newEntry); // adds new object to pre-existing linkedlist at index

        if (this.loadReached() == true) {
          this.rehash(); // rehashes and dynamically grows if load factor of 70% is reached
        }

        return true;

      }
    }
  }


  /**
   * Private helper method to determine if load factor is reached
   * 
   * @return boolean true if load factor reached, false otherwise
   */
  private boolean loadReached() {

    if ((((double) this.size() / (double) this.hashtable.length) * 100) >= 70) {
      return true;
    } else {
      return false;
    }
  }


  /**
   * Private helper method to rehash key value pairs and dynamically grow array
   */
  private void rehash() {

    LinkedList<HashEntryBD>[] newHashtable =
        (LinkedList<HashEntryBD>[]) new LinkedList[this.hashtable.length * 2]; // creates new array
                                                                             // with double the size

    for (int i = 0; i < this.hashtable.length; i++) {

      if (this.hashtable[i] == null) {
        continue;
      } else {

        LinkedList<HashEntryBD> currentList = this.hashtable[i]; // stores current key value pairs at
                                                               // index i in variable

        for (int j = 0; j < currentList.size(); j++) {
          KeyType currentKey = (KeyType) currentList.get(j).getKey(); // stores current key
          ValueType currentValue = (ValueType) currentList.get(j).getValue(); // stores current
                                                                              // value
          int rehash = Math.abs(currentKey.hashCode());
          int newindex = (rehash % newHashtable.length); // finds new index of key value pair

          if (newHashtable[newindex] == null) {
            HashEntryBD<KeyType, ValueType> newEntry = new HashEntryBD(currentKey, currentValue);
            newHashtable[newindex] = new LinkedList<HashEntryBD>();
            newHashtable[newindex].add(newEntry); // adds key value pair into new linkedlist
          } else {
            HashEntryBD<KeyType, ValueType> newEntry = new HashEntryBD(currentKey, currentValue);
            newHashtable[newindex].add(newEntry); // adds key value pair to preexisting linkedlist
          }

        }

      }

    }

    this.hashtable = newHashtable; // assigns new hashtable to instance field

  }


  /**
   * Returns the value mapped to a key if the map contains such a mapping.
   * 
   * @param key the key for which to look up the value
   * @return the value mapped to the key
   * @throws NoSuchElementException if the map does not contain a mapping for the key
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {

    int hash = Math.abs(key.hashCode());
    int index = (hash % this.hashtable.length);

    if (this.hashtable[index] == null) {
      throw new NoSuchElementException(); // map doesn't contain mapping for key
    } else {

      LinkedList<HashEntryBD> currentList = this.hashtable[index];
      ValueType returnValue = null;

      for (int i = 0; i < currentList.size(); i++) {
        if ((currentList.get(i).getKey().hashCode()) == (key.hashCode())) {
          returnValue = (ValueType) currentList.get(i).getValue(); // assigns returnvalue to
                                                                   // variable if key is found
        }
      }

      if (returnValue == null) {
        throw new NoSuchElementException(); // key not found in the index it should be in, therefore
                                            // no mapping for key in hashtable
      } else {
        return returnValue; // return the value found
      }
    }
  }


  /**
   * Removes a key and its value from the map.
   * 
   * @param key the key for the (key, value) pair to remove
   * @return the value for the (key, value) pair that was removed, or null if the map did not
   *         contain a mapping for key
   */
  @Override
  public ValueType remove(KeyType key) {

    int hash = Math.abs(key.hashCode());
    int index = (hash % this.hashtable.length); // finds the index the key value pair should be at

    if (this.hashtable[index] == null) {
      return null; // index empty, therefore no mapping
    } else {
      
      ValueType returnValue = null;

      for (int i = 0; i < this.hashtable[index].size(); i++) {

        if ((this.hashtable[index].get(i).getKey().hashCode() == key.hashCode())) {
          returnValue = (ValueType) this.hashtable[index].get(i).getValue(); // stores return value
          this.hashtable[index].remove(i); // removes key value object from linkedlist at index
        }
      }
      return returnValue; // null if key not found in linkedlist (since it was initialized to null),
                          // return value otherwise
    }
  }


  /**
   * Checks if a key is stored in the map.
   * 
   * @param key the key to check for
   * @return true if the key is stored (mapped to a value) by the map and false otherwise
   */
  @Override
  public boolean containsKey(KeyType key) {

    for (int i = 0; i < this.hashtable.length; i++) {
      if (this.hashtable[i] == null) {
        continue;
      } else {
        LinkedList<HashEntryBD> currentList = this.hashtable[i];
        for (int j = 0; j < currentList.size(); j++) {
          if ((currentList.get(j).getKey().hashCode()) == (key.hashCode())) {
            return true;
          }
        }
      }
    }
    return false;
  }

  
  /**
   * Returns the number of (key, value) pairs stored in the map.
   * 
   * @return int the number of (key, value) pairs stored in the map
   */
  @Override
  public int size() {
    int count = 0;
    for (int i = 0; i < this.hashtable.length; i++) {
      if (this.hashtable[i] != null) {
        count = count + this.hashtable[i].size();
      }
    }
    return count;
  }
  
  
  /**
   * Removes all (key, value) pairs from the map.
   */
  @Override
  public void clear() {
    for (int i = 0; i < this.hashtable.length; i++) {
      this.hashtable[i] = null;
    }

  }


  /**
   * Placeholder method for Iterator()
   */
  @Override
  public Iterator<ValueType> iterator() {
    ArrayList<ValueType> books = new ArrayList<ValueType>();
    IBook testBook1 = new BookBD("title1", "author1", "ISBN1");
    IBook testBook2 = new BookBD("title2", "author2", "ISBN2");
    IBook testBook3 = new BookBD("title3", "author3", "ISBN3");
    books.add((ValueType) testBook1);
    books.add((ValueType) testBook2);
    books.add((ValueType) testBook3);
    
    return books.iterator();
  }

}
