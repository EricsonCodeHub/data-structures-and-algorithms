import java.util.Random;
/**
	The class LinkedBagTester test the functionality 
	of the LinkedBag add method 
*/
public class LinkedBagTester
{
    public static void main(String[] args)
    {

		//creates instance of LinkedBag
		BagInterface<String> aBag = new LinkedBag<>();

		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("The tester works in 4 parts");
		System.out.println("***part1***");
		System.out.println("random chars are generated and "+
		"parsed to strings");
		System.out.println("the strings are then added to the bag");
		System.out.println("the order should be reversed when compared to"+
		" the strings that were added to bag.");
		System.out.println("***part2***");
		System.out.println("random chars are generated and "+
		"parsed to strings");
		System.out.println("the strings are then added to the bag");
		System.out.println("the order should be in alphabetical order.");
		System.out.println("***part3***");
		System.out.println("random ints are generated and "+
		"parsed to strings");
		System.out.println("the strings are then added to the bag");
		System.out.println("the order should be reversed when compared to"+
		" the strings that were added to bag.");
		System.out.println("***part4***");
		System.out.println("random ints are generated and "+
		"parsed to strings");
		System.out.println("the strings are then added to the bag");
		System.out.println("the order should be from least to greatest");
		System.out.println();
		System.out.println();
		System.out.println();

		
		
		System.out.println("***part1***");
        // add strings to aBag at the start of the linked list.
        testAddFalse(aBag, createCharArray());
        System.out.println("add method still add to the start " + 
									"of linked list,test passed");
 

        aBag.clear();
        System.out.println();
		System.out.println("***part2***");
        // adds strings to abag, inserts them into the linked list in order.
        testAddTrue(aBag, createCharArray());
        System.out.println("add method adds to linked list in "+
								"sorted order, test passed");;

        aBag.clear();
        System.out.println();
		System.out.println("***part3***");
        // add strings to aBag at the start of the linked list.
        testAddFalse(aBag, createIntArray());
        System.out.println("add method still add to the start of "+
								"linked list, test passed");
 
        aBag.clear();
        System.out.println();
		System.out.println("***part4***");
        // adds strings to abag, inserts them into the linked list in order.
        testAddTrue(aBag, createIntArray());
        System.out.println("add method adds to linked list "+
							"in sorted order, test passed");

    }


    /*
		createCharArray() creates a string array.
        an int from 65-90 is type cast to a char.
        the char is parsed to a string.
        The string will contain one random letter.
    */

	private static String[] createCharArray()
    {
        //creates String array of size 100
        String[] charArray = new String[100];
        
        //creates a random object 
        Random rand = new Random();

        // holds int to be type cast to char
        int randChar;

        //loops and adds a String to the array
        for(int i = 0;i<charArray.length;i++)
        {   
            //random int from 1-26 is generated
			randChar = rand.nextInt(26)+65; //65-90

            //char is parsed to string and added to array
            charArray[i] = Character.toString((char)randChar);
        }

        return charArray;
    }
	
    /*
        createIntArray() creates a string array.
        a random int 0-9 is parsed to a string.
        the string is stored in the array.
    */ 
    private static String[] createIntArray()
    {
        //creates String array of size 100
        String[] intArray = new String[100];

        //creates a random object 
        Random rand = new Random();

        //loops and adds a String to the array
        for(int i = 0;i<intArray.length;i++)
        {   
            //parses int to string and adds to array 
            intArray[i] = Integer.toString(rand.nextInt(10));
        }

        return intArray;
    }

    //modified from LinkedBagDemo
    private static void testAddTrue(BagInterface<String> aBag,
											String[] content)
	{
		
        System.out.println("Adding to the bag: ");
		for (int index = 0; index < content.length; index++)
		{
			aBag.add(content[index],true);
			System.out.print(content[index] + ", ");
		} // end for

        //print statement indicates boolean true
		System.out.println();
        System.out.println("add method boolean True ");
		displayBag(aBag);
	} // end testAdd

    //modified from LinkedBagDemo
    private static void testAddFalse(BagInterface<String> aBag,
												String[] content)
	{
		
        System.out.println("Adding to the bag: ");;
		for (int index = 0; index < content.length; index++)
		{
			aBag.add(content[index],false);
			System.out.print(content[index] + ", ");
		} // end for

        //print statement indicates boolean false
		System.out.println();
        System.out.println("add method boolean false ");
		displayBag(aBag);
	} // end testAdd

    // Tests the method toArray while displaying the bag.
	private static void displayBag(BagInterface<String> aBag)
	{
		System.out.println("The bag contains " + aBag.getCurrentSize() +
		                   " string(s), as follows:");		
		Object[] bagArray = aBag.toArray();
		for (int index = 0; index < bagArray.length; index++)
		{
			System.out.print(bagArray[index] + ", ");
		} // end for
		
		System.out.println();
	} // end displayBag
}