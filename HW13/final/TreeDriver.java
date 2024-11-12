/**
 * Tree driver demonstartes Binary search tree
 */
public class TreeDriver
{
    /*
        main method
    */
    public static void main(String[] args)
    {
        // Sorted String array array
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M","N","-"};

        // Create BinarySearchTree
        BinarySearchTree<String> bst = new BinarySearchTree<>(alphabet);

        // Prints the tree
        bst.print();
    }
}
