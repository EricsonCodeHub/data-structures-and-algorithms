import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;			
import java.util.Random;
/**     		
   A solitaire matching game in which you have a list of random
   integer values between 10 and 99. You remove from the list any
   pair of adjacent integers whose first or second digits match.
   If all values are removed, you win.

   @author Charles Hoot 
   @version 5.0
 */
public class SolitaireGame
{
	private static List<Integer> theNumbers;

	/** Initializes the list with 40 random 2 digit numbers. */
	public static void initializeList(List<Integer> theList)
	{
		//creates random object
		Random rand = new Random();
		//adds random ints to the ArrayList
		while(theList.size() < 40)
		{
			theList.add(rand.nextInt(90)+10);
		}
	} // end initializeList

	/** Sees whether two numbers are removable.
		 @param x  The first 2 digit integer value.
		 @param y  The second 2 digit integer value.
 		 @return  True if x and y match in the first or second digit. */
	public static boolean removable(Integer x, Integer y)
	{
		//creates two string variables
		String numX ,numY;
		// assigns string objects to string variables
		numX = x.toString();
		numY = y.toString();

		/*
			checks to see if the first or second charactor of the strings are the same
			and returns true if  the statement is true false if the statement is false
		*/ 
		return (numX.charAt(0)==numY.charAt(0) || numX.charAt(1)==numY.charAt(1));


	} // end removable

	/** Scans over the list and removes any pairs of values that are removable.
		 @param theList  The list of 2 digit integers to scan over.
		 @return  True if any pair of integers was removed.  */
	public static boolean scanAndRemovePairs(List<Integer> theList)
	{
		//creates a ListIterator assigned to listIterator variable
		ListIterator<Integer> listIterator = theList.listIterator();
		//creates a boolean variable and assigns it false
		boolean returnBoolean  = false;
		//creates two Integer variables
		Integer previous, current;
		previous = null;

		// loops while ArrayList.hasNext
		while(listIterator.hasNext())
		{
			//current gets assigned to next
			current = listIterator.next();

			/*
				continue id previous is not equal to null and 
				removable returns true
			*/
			if(previous != null && removable(previous,current))
			{
				//prints the numbers to be removed
				System.out.println("Removed :"+previous+" " +current);

				// removes the current from the ArrayList 
				listIterator.remove();

				// moves Iterator to previouse index 
				listIterator.previous();

				//removes the previous from the ArrayList
				listIterator.remove();

				// sets boolean to true
				returnBoolean = true;
				//sets current and previous to null becuase they were removed
				previous = null;
				current = null;
			}
			else
			{
				//previous gets set to current for next loop
				previous = current;
			}
		}
		return returnBoolean;

	} // end scanAndRemovePairs

	public static void main(String args[])
	{
	     theNumbers = new ArrayList<>();
		 initializeList(theNumbers);
		 System.out.println("The list is originally: " + theNumbers);

		while (scanAndRemovePairs(theNumbers))
			System.out.println("The list is now: " + theNumbers);
		System.out.println("No more pairs to remove.");
	} // end main
}  //  end SolitaireGame

/*
The list is originally: [81, 50, 11, 61, 42, 74, 16, 65, 49, 49, 11, 19, 67, 79, 33, 95, 85, 52, 59, 67, 46, 81, 62, 30, 60, 66, 80, 96, 30, 81, 37, 30, 34, 30, 15, 80, 11, 61, 55, 46]
   Removed: 11  61
   Removed: 49  49
   Removed: 11  19
   Removed: 95  85
   Removed: 52  59
   Removed: 30  60
   Removed: 37  30
   Removed: 34  30
   Removed: 11  61
The list is now: [81, 50, 42, 74, 16, 65, 67, 79, 33, 67, 46, 81, 62, 66, 80, 96, 30, 81, 15, 80, 55, 46]
   Removed: 65  67
   Removed: 62  66
The list is now: [81, 50, 42, 74, 16, 79, 33, 67, 46, 81, 80, 96, 30, 81, 15, 80, 55, 46]
   Removed: 81  80
The list is now: [81, 50, 42, 74, 16, 79, 33, 67, 46, 96, 30, 81, 15, 80, 55, 46]
   Removed: 46  96
The list is now: [81, 50, 42, 74, 16, 79, 33, 67, 30, 81, 15, 80, 55, 46]
No more pairs to remove.

 */
