COMMENTARY:

To find the largest entry in a binary search tree, follow right child pointers as far as possible.   

Since the getMax method here does not have access to the structure of the binary tree, it uses an iterator instead. It will visit all the nodes before it returns an answer. It is O(n), whereas a more typical implementation is O(lg n).   

The add method simply calls the add method of the binary search tree. Thus, its worst-case performance is O(n). Its average performance is more interesting. For a randomly built binary search tree, the add method would be O(lg n). But the binary search tree in this maxheap is not randomly built. For this application, maximum values are removed from the tree. Since these values are on the right side of the tree, removals will tend to make the tree unbalanced on the left. On the other hand, if we remove most of the values, the root of our tree will be small. Subsequent values will tend to be added on the right side of the tree, making it unbalanced towards the left. To see which of these effects dominates the performance, test cases can be run with random additions and removals. Each time an operation is done, we record the number of values in the tree, its height, and the number of comparisons done by the operation. This data can then be analyzed to estimate the expected performance.

The remove method in this implementation has essentially the same performance as getMax.

The solution to the previous approach is given.
===============
Alternate approach:

Another way to solve this problem revises BinarySearchTree so that it implements MaxHeapInterface.  Doing so requires that BinarySearchTree implement two extra methods: getMax and removeMax.

   public T getMax()    // Method appears in solution to Project 5 of Chapter 26
   {
      T result = null;
      BinaryNode<T> currentNode = getRootNode();
      
      while ( (currentNode != null) && (currentNode.getRightChild() != null) )
         currentNode = currentNode.getRightChild();
      
      if (currentNode != null)
         result = currentNode.getData();
      
      return result;
   } // end getMax

   public T removeMax()
   {
      T result = null;

      if (getRootNode()!= null)
      {
         result = getMax();
         remove(result);
      } // end if

      return result;
    } // end removeMax


The performance of these versions of the getMax and removeMax methods is O(n) in the worst case, but in the best case is O(1). The actual performance will depend on the structure of the tree, as described previously for the add method.   


