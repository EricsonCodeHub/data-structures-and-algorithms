
import java.util.Arrays;

/** A class that implements a list of objects by using an array.
 * Entries in a list have positions that begin with.
 * Duplicate entries are allowed.
 * The size of the array doubles until it exceeds the maximum allowed
 * capacity, where upon an exception is thrown.
 * 
 * This code is from Chapter 13 of 
 * Data Structures and Abstractions with Java 4/e
 * @author Frank M. Carrano
 *      
 * The toString method is overwritten to give a nice display of the items in
 * the list in this format { <1> <2> <3> <4> }
 * Modification by Charles Hoot
 */
class AList<T> implements ListInterface<T> 
{
    private T[] list; //Array of list entries; ignore list[0]
    private int numberOfEntries; // current number of entries in list
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;
    
    

    public AList() 
	{
        this(DEFAULT_CAPACITY);  // Call next constructor
    } // end default constructor



    public AList(int initialCapacity) 
	{
        // Is initialCapacity too small?
        if (initialCapacity < DEFAULT_CAPACITY)
            initialCapacity = DEFAULT_CAPACITY;
        else // Is initialCapacity too big?
            checkCapacity(initialCapacity);
        
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempList = (T[]) new Object[initialCapacity + 1];
        list = tempList;
        numberOfEntries = 0;
        initialized = true;
    } // end constructor



    /* Throws an exception if this object is not initialized.
     * 
     */
    private void checkInitialization()
	{
        if (!initialized)
            throw new SecurityException("ArrayBag object is not initialized " +
                                        "properly.");
    } // end checkInitialization



    /* Throws an exception if the desired capacity exceeds the maximum.
     * 
     */
    private void checkCapacity(int desiredCapacity)
	{
        if(desiredCapacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a bag " +
                                            "whose capacity exceeds " +
                                            "allowed maximum.");
    } // end checkCapacity
    


	/** Adds a new entry to the end of this list.
	 *  Entries currently in the list are unaffected.
	 *  The list's size is increased by 1.
	 *  @param newEntry The object to be added as a new entry.
	 */
    public void add(T newEntry) 
	{
        checkInitialization();
        list[numberOfEntries+1] = newEntry;
        numberOfEntries++;
        ensureCapacity();
    } // end add



	/** Adds a new entry at a specified position within this list.
	 *  Entries originally at and above the specified position
	 *  are at the next higher position within the list.
	 *  The list's size is increased by 1.
	 *  @param newPosition An integer that specifies the desired
	 *          position of the new entry.
	 *  @param newEntry The object to be added as a new entry.
	 *  @throws IndexOutOfBoundsException if either
	 *          newPosition less than 1, or
	 *          newPosition greater than getLength()+1. 
	 */
    public void add(int newPosition, T newEntry) 
	{
        checkInitialization();

        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) 
		{
            if (newPosition <= numberOfEntries) 
			{
                makeRoom(newPosition);
            }

            list[newPosition] = newEntry;
            numberOfEntries++;
            ensureCapacity(); // Ensure enough room for next add
        } 
		else 
		{
            throw new IndexOutOfBoundsException("Illegal position given to add operation.");
        }
    } // end add



	/** Removes the entry at a given position from this list.
	 *  Entries originally at positions higher than the given
	 *  position are at the next lower position within the list,
	 *  and the list's size is decreased by 1.
	 *  @param givenPosition An integer that indicates the position of
	 *         the entry to be removed.
	 *  @return A reference to the removed entry.
	 *  @throws IndexOutOfBoundsException if either
	 *          givenPosition less than 1, or
	 *          givenPosition greater than getLength()+1. 
	 */
    public T remove(int givenPosition) 
	{
        checkInitialization();
        
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) 
		{
            assert !isEmpty();
            T result = list[givenPosition ]; // Get entry to be removed
            // Move subsequent entries toward entry to be removed,
            // unless it is last in list
            if (givenPosition < numberOfEntries) 
			{
                removeGap(givenPosition);
            }
            numberOfEntries--;
            return result; // Return reference to removed entry
        }
        else
             throw new IndexOutOfBoundsException("Illegal position given to " +
												 "remove operation.");
    } // end remove



	/** Removes all entries from this list. */
    public void clear() 
	{
        numberOfEntries = 0;
    } // end clear



	/** Replaces the entry at a given position in this list.
	 *  @param givenPosition An integer that indicates the position of the
	 *         entry to be replaced.
	 *  @param newEntry The object that will replace the entry at the
	 *         position givenPosition.
	 *  @return The original entry that was replaced.
	 *  @throws IndexOutOfBoundsException if either
	 *          givenPosition less than 1, or
	 *          givenPosition greater than getLength()+1. 
	 */
    public T replace(int givenPosition, T newEntry) 
	{
        checkInitialization();

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) 
		{
            assert !isEmpty();
            T originalEntry = list[givenPosition];
            list[givenPosition] = newEntry;
            return originalEntry;
        } 
		else 
             throw new IndexOutOfBoundsException("Illegal position given to " +
												 "replace operation.");
    } // end replace



	/** Retrieves the entry at a given position in this list.
	 *  @param givenPosition An integer that indicates the position of
	 *         the desired entry.
	 *  @return A reference to the indicated entry.
	 *  @throws IndexOutOfBoundsException if either
	 *          givenPosition less than 1, or
	 *          givenPosition greater than getLength()+1. 
	 */
    public T getEntry(int givenPosition) 
	{
        checkInitialization();
        
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) 
		{
            assert !isEmpty();
            return list[givenPosition];
        } 
		else 
             throw new IndexOutOfBoundsException("Illegal position give to " +
												 "getEntry operation.");
    } // end getEntry



	/** Sees whether this list contains a given entry.
	 *  @param anEntry The object that is the desired entry.
	 *  @return True if the list contains anEntry, or false if not. 
	 */
    public boolean contains(T anEntry) 
	{
        checkInitialization();
        
        boolean found = false;
        int index = 1;

        while (!found && (index <= numberOfEntries)) 
		{
            if (anEntry.equals(list[index])) 
			{
                found = true;
            }
            index++;
        } // end for
        return found;
    } // end contains



	/** Gets the length of this list.
	 *  @return The integer number of entries currently in the list. 
	 */
    public int getLength() 
	{
        return numberOfEntries;
    } // end getLength



	/** Sees whether this list is empty.
	 * @return True if the list is empty, or false if not.
	 */
    public boolean isEmpty() 
	{
        return numberOfEntries == 0;
    } // end isEmpty



	/** Retrieves all entries that are in this list in the order in which
	 *  they occur in the list. 
	 *  @return A newly allocated array of all the entries in the list.
	 */
    public T[] toArray() 
	{
        checkInitialization();
        
        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];

        for (int index = 0; index < numberOfEntries; index++) 
		{
            result[index] = list[index+1];
        } // end for
        return result;
    } // end toArray



    // Doubles the size of the array list if it is full.
    private void ensureCapacity() 
	{
        int capacity = list.length - 1;
        
        if (numberOfEntries >= capacity) 
		{
            int newCapacity = 2 * capacity;
            checkCapacity(newCapacity);  // Is capacity too big?
            list = Arrays.copyOf(list, newCapacity + 1);
        }
    } // end ensureCapacity

    

    /** Makes room for a new entry at newPosition.
     * Precondition: 1 <= newPosition <= numberOfEntries+1;
     * numberOfEntries is list's length before addition. 
     * checkInitialization has been called.
     */
    private void makeRoom(int newPosition) 
	{
        assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);
        int newIndex = newPosition;
        int lastIndex = numberOfEntries;
        // Move each entry to next higher index, starting at end of
        // array and continuing until the entry at newIndex is moved
        for (int index = lastIndex; index >= newIndex; index--) 
		{
            list[index + 1] = list[index];
        }
    } // end makeRoom



    /** Shifts entries that are beyond the entry to be removed
     * to the next lower position.
     * Precondition: 1 <= givenPosition < numberOfEntries;
     * numberOfEntries is list's length before removal. 
     * checkInitialization has been called.
     */
    private void removeGap(int givenPosition) 
	{
        assert (givenPosition >= 1) && (givenPosition < numberOfEntries);

        int removedIndex = givenPosition;
        int lastIndex = numberOfEntries;
        for (int index = removedIndex; index < lastIndex; index++) 
            list[index] = list[index + 1];
    } // end removeGap



    /** Build a string representation of the list
     *
     * @return A string showing the state of the list.
     */
    public String toString() 
	{
        String result = "{ ";
        for (int i = 0; i < numberOfEntries; i++) 
		{
            result = result + "<" + list[i+1] + "> ";
        }
        result = result + "}";
        return result;
    }
}
