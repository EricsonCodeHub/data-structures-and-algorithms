// imports for program
import java.util.Random;
import java.util.Scanner;
/**
 * This program demonstrates finding intervals in a sorted array 
 * based on target values provided by the user.
 */
public class Driver
{
    /**
     * start point of program
     */
    public static void main(String[] args)
    {
        // assigns array a sorted  array with 15 random ints 
        int[] array = createArray();
        // int array for the target values
        int[] targets;
        // boolean valuse for while loop
        boolean bool = true;
        
        while(bool)
        {
            // gets target values from user
            targets = getTargetIntervals();

            if(targets.length > 1)
                IntervalFinder(array, targets);
            else
                bool = false;

        }
    }

    /*
     * Creates a random array of integers and sorts it using insertionSort 
	 * method.
     *
     * @return The sorted random array of integers.
     */
    private static int[] createArray()
    {
        // variables for createArray method
        Random rand = new Random();
        int[] array = new int[15];

        // add random ints to array
        for( int i = 0 ; i < array.length; i++ )
        {
            array[i] = rand.nextInt(99) + 1;
        }

        // calls insertionSort and sorts array
        insertionSort(array); 

        // prints array to user
        for(int i = 0 ; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }

        // newline print
        System.out.println("");

        // returns sorted array 
        return (array);
    }

    /*
     * Reads target intervals from the user input.
     *
     * @return An array containing the target interval values.
     */
    private static int[] getTargetIntervals()
    {
        //variables for this getTargetIntervals method
        Scanner keyboard = new Scanner(System.in);
        String stringInput;
        int[] targetValues;
        String[] stringArray;

        //prints statement to user
        System.out.println("Enter the list of integer values " + 
				"(all on the line), or just press enter if you are done");
        // scans for users input
        stringInput = keyboard.nextLine();

        // exicutes body id user presses enter
        if(stringInput.equals(""))
        {
            // prints to user
            System.out.println("Goodbye");

            return targetValues = new int[0];
        }

        // splits string to array
        stringArray = stringInput.split(" ");
        
        //assigns int array to targetValues
        targetValues = new int[stringArray.length];
        
        for(int i = 0; i<  stringArray.length; i++)
        {
            //parses string to int
            targetValues[i] = Integer.parseInt(stringArray[i]);
        }
        
		// solves problem for inputs such as >" 67 23 67 98 2"
        insertionSort(targetValues);

        //returns array of targetValues
        return  targetValues;
    }


    /*
     * Finds the indexs of the intervals containing the target values 
	 * in the random array.
     *
     * @param randomArray   The sorted array of random integers.
     * @param targetNumbers The array containing the target interval values.
     */
    private static void IntervalFinder(int[] randomArray, int[] targetNumbers)
    {
        // output is assinged the defult interval bounds
        int[] output = {-1, 15};

        // finds the interval bound closest to the index -1
        for (int i = 0; i < randomArray.length; i++) 
        {
            if (randomArray[i] == targetNumbers[0])
            {
                output[0]++;
            } 
            else if (randomArray[i] < targetNumbers[0])
            {
                output[0]++;
            }
            else
            {
                // breaks loop so less comparisons need to be made
                break; 
            }

        }

        // finds the interval bound closest to the index 15
        for (int i = randomArray.length - 1; i >= 0; i--)
        {
            if (randomArray[i] == targetNumbers[targetNumbers.length -1])
            {
                output[1]--;
            } 
            else if (randomArray[i] > targetNumbers[targetNumbers.length -1])
            {
                output[1]--;
            }
            else
            {
                // breaks loop so less comparisons need to be made
                break;
            }
        }

        // prints statement to the user
        System.out.println("The pair of indices the bound the interval"  + 
		" contain the given values is (" + output[0] + "," + output[1]+ ")");

    }

    /*
     * insertion sort algorithm to sort an array of integers.
     *
     * @param array The array to be sorted.
     */
    private static void insertionSort(int[] array)
    {
        int temp;

		// moved through the array forwards
        for(int i = 0; i < array.length; i++)
        {
			// moves theough the array backwards
            for (int j = i - 1; j >= 0; j--)
            {
                // checks if the indexs are out of order
                if(array[j] > array[j + 1])
                {	
					//swaps the indexs
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }   
            }
        }
    }
}