import java.util.*;
/**
 * Represents a binary search tree.
 * @param <T> The type of elements stored in the tree.
 */
public class BinarySearchTree<T>
{
    // root of binary tree
    private BinaryNode<T> root;

    //number of nodes
    int nodes;

    /**
     * Constructs a BinarySearchTree from an array.
     * @param array The array used to create the binary search tree.
     */
    public BinarySearchTree(T[] array)
    {
        // creates a binary search tree from an array and assigns the root to root
        root = sortedArrayToBSTRC(array, 0, array.length - 1);
        // nodes  is the number of nodes in the tree, this variable is used to print
        nodes = array.length;
    }

    /*
     * Converts a sorted array into a binary search tree recursively.
     * @param array The sorted array to convert.
     * @param start The starting index of the current subarray.
     * @param end The ending index of the current subarray.
     * @return The root node of the resulting binary search tree.
     */
    private BinaryNode<T> sortedArrayToBSTRC(T[] array, int start, int end)
    {
        //method variables
        int mid;
        BinaryNode<T> node;
        // if node is a leaf recursion stops
        if (start == end)
        {
            // node is created and returned, no recursive calls
            return new BinaryNode<>(array[start]);
        }

        // mid point is calculated
        mid = start + (end - start) / 2;
        // node is created from mid point
        node = new BinaryNode<>(array[mid]);
        //left and right nodes are creates from recursive calls
        node.setLeftChild(sortedArrayToBSTRC(array, start, mid - 1));
        node.setRightChild(sortedArrayToBSTRC(array, mid + 1, end));
        // node is returned with all child nodes assigned
        return node;
    }

    /**
     * print - prints the binary tree level by level.
     */
    public void print()
    {
        //method variables
        Queue<BinaryNode<T>> nodesQueue = new LinkedList<>();
        BinaryNode<T> node;
        int height;
        int spacesBefore;
        int spacesBetween;

        /*
            root is added to the nodesQueue

            at this point queue only contain root node
        */
        nodesQueue.add(root);

        // height gets assigned the height of the binary tree
        height = (int) Math.ceil(Math.log(nodes) / Math.log(2));

        // loop for printing
        for (int i = 0; i < height; i++)
        {
            /*
                does math for the amount of spaces printed before the 
                first inner loop print(the nodes)
            */
            spacesBefore = (int) Math.pow(2, height - i - 1);

            // does the math for spaces needed between each node print
            spacesBetween = spacesBefore * 2 - 1; 

            /*
                calls the helper method to print spaces,
                the spaces bofore the first node print aere printed
            */
            spacesHelper(spacesBefore);

            // loops for the amount of nodes at current level
            for (int j = 0; j < Math.pow(2, i); j++)
            {
                // gets node form nodesnodesQueue
                node = nodesQueue.poll();
                /*
                    1 if node does not equall null node data will be printed

                    2 the child nodes will be added to the queue ---
                      this will happen for all the node at the current layer
                      this is also how the queue gets nodes to print the next layer

                    3 if node is null a spaces are printed in the place where node 
                      data would be printed, also if data portion is null there won't be
                      a left or right node

                */
                if (node != null)
                {
                    //format prints node data to string
                    System.out.print(String.format("%-8s", node.getData().toString()));

                    //adds child nodes to queue for next layer print
                    nodesQueue.add(node.getLeftChild());
                    nodesQueue.add(node.getRightChild());
                }
                else
                {
                    // if node is null prints blank
                    System.out.print("        ");
                    //adds null to queue
                    nodesQueue.add(null);
                    nodesQueue.add(null);
                }

                // prints spaces after nodes at current level have meen printed
                spacesHelper(spacesBetween);
            }

            //prints for new line
            System.out.println();
        }
    }

    /*
     * spacesHelper helper method to print spaces for formatting.
     * @param num The number of spaces to print.
     */
    private void spacesHelper(int num)
    {

        for (int i = 0; i < num; i++)
        {
            //prints spaces
            System.out.print("        ");
        }
    }
}
