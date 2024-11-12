
/**
 * An interface representing a max heap data structure
 */
public interface MaxHeapInterface<T extends Comparable<? super T>>
{
    /**
     * Adds a new entry to the max heap.
     * 
     * @param newEntry The entry to be added.
     */
    public void add(T newEntry);

    /**
     * Removes and returns the maximum value (root) from the max heap.
     * 
     * @return The maximum value removed from the heap.
     */
    public T removeMax();

    /**
     * Retrieves the maximum value (root) from the max heap without removing it.
     * 
     * @return The maximum value in the heap.
     */
    public T getMax();

    /**
     * Checks if the max heap is empty.
     * 
     * @return true if the max heap is empty, false otherwise.
     */
    public boolean isEmpty();

    /**
     * Gets the number of elements currently in the max heap.
     * 
     * @return The size of the max heap.
     */
    public int getSize();

    /**
     * Clears all elements from the max heap.
     */
    public void clear();
} 