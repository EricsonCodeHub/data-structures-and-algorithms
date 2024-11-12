import TreePackage.*;
import java.util.Iterator;
/**
   A class that implements the ADT maxheap
   by using a binary search tree.

   @author Charles Hoot
   @author Frank M. Carrano
   @version 5.0
 */
public class BstMaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T>
{
	private SearchTreeInterface<T> heap;

	public BstMaxHeap() 
	{
		heap = new BinarySearchTree<>();
	} // end default constructor

	public BstMaxHeap(T[] array) 
	{
		heap = new BinarySearchTree<>();

		// Add entries in given array to heap
		for (int index = 0; index < array.length; index++)
			add(array[index]);
	} // end constructor

	public void add(T newEntry) 
	{
		heap.add(newEntry);
	} // end add

	public T removeMax() 
	{
		T max = null;

		Iterator<T> iterator = heap.getInorderIterator();
		while (iterator.hasNext()) 
			max = iterator.next();

		heap.remove(max);
		return max;
	} // end removeMax

	public T getMax() 
	{
		T max = null;

		Iterator<T> iterator = heap.getInorderIterator();
		while (iterator.hasNext()) 
			max = iterator.next();

		return max;
	} // end getMax

	public boolean isEmpty() 
	{
		return heap.isEmpty();
	} // end isEmpty

	public int getSize() 
	{
		return heap.getNumberOfNodes();
	} // end getSize

	public void clear() 
	{
		heap.clear();
	} // end clear
} // end BstMaxHeap
