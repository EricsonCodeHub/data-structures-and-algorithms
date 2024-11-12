/** A demonstration of the class ArrayBag
    @author Frank M. Carrano
    @author Timothy M. Henry
    @version 4.0
*/
public class ArrayBagDemo
{
	public static void main(String[] args) 
	{
		String[] contentsOfBag = {"A", "B", "C", "D", "E", "F"};

		// Tests on an empty bag
		BagInterface<String> aBag = new ArrayBag<>(contentsOfBag.length);

		testAdd(aBag, contentsOfBag);
		displayBag(aBag);
	} // end main
   
	// Tests the method add.
	private static void testAdd(BagInterface<String> aBag, String[] content)
	{
		System.out.print("Adding ");
		for (int index = 0; index < content.length; index++)
		{
			aBag.add(content[index]);
			System.out.print(content[index] + " ");
		} // end for
		System.out.println();
      
		displayBag(aBag);
	} // end testAdd

	// Tests the method toArray while displaying the bag.
	private static void displayBag(BagInterface<String> aBag)
	{
		System.out.println("The bag contains " + aBag.getCurrentSize() +
                           " string(s), as follows:");     
		Object[] bagArray = aBag.toArray();
		/*
			for (int index = 0; index < bagArray.length; index++)
			{
				System.out.print(bagArray[index] + " ");
			} // end for
		*/

		//makes the recursive call
		reverseArray( bagArray, 0);
      
		System.out.println();
	} // end displayBag

	// recursively prints elements of an array
	private static void reverseArray(Object[] bagArray, int index)
	{
		/*
			when index == bagArray.length recursion stops
			and the print statements are executed, the method 
			call that stopped the recursion will not print
		*/
		if(index == bagArray.length)
		{
			// this stops the final recursion method
			return;
		}

		// recursive call
		reverseArray(bagArray, index +1);
		// print statement for when recursion stops
		System.out.print(bagArray[index]+" ");
	}
} // end ArrayBagDemo
