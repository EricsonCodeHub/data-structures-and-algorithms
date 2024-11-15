import java.util.*;
import java.io.*;

/**
 * A class for generating statistical information about the basis sort performance.
 * 
 * @author Charles Hoot
 * @version 4.0
 */

        
public class SortDriver
{
    
    
    
    public static void main(String args[])
    {
        int arraySize;
        int trials;
        Integer data[];

        //CREATE THE INSTANCE OF THE INSTRUMENTED SORT CLASS HERE
        //step6>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        SortArrayInstrumented sai = new SortArrayInstrumented();
        
        
        
        System.out.println("What size arrays should be used?");
        arraySize = 
		getInt("   It should be an integer value greater than or equal to 1.");

        // step 12 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        trials = 
		getInt("   enter a number for the amount of trials you want to run");

        // step 14>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        for(int i = 0; i< trials; i++)
        {
            // MODIFY THE FOLLOWING TO GET THE NUMBER OF TRIALS AND LOOP      
                data = generateRandomArray(arraySize);
                

                // step 22 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                //System.out.println("The array is: " + getString(data));

                //step7>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                sai. shellSort(data, arraySize);

                //step 11 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

                ///System.out.println(" comparison made: "+
												//sai.getComparisons());
                
            
                //System.out.println("The sorted array is: " + 
														//getString(data));

            // ADD CODE TO REPORT THE NUMBER OF COMPARISONS
        }
        
        // step 21 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        System.out.println("total number of comparisons " + 
									sai.getTotalComparisons());
        System.out.println("minimum number of comparisons " + 
										sai.getMinComparisons());
        System.out.println("maximum number of comparisons " + 
											sai.getMaxComparisons());

        // step 22 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        System.out.println("the average number of comparisons "+
			"made over the trials " + (sai.getTotalComparisons() / trials));
    }



    /**
     * Generate an array of random integer values.  
	 * The values will be between 0 and size.
     *
     * @param   size    The size of the array to generate.
     * @return  The array of integers. 
     */
    private static Integer[] generateRandomArray(int size)
    {
        Integer result[] = new Integer[size];
        Random generator = new Random();
        
        for(int i = 0; i< size; i++)
        {
            int value = generator.nextInt(size);
            result[i] = new Integer(value);
        }
        
        return result;
    }


    /**
     *  A displayable representation of an array of Objects where toString is 
     *  applied on each object in the array
     *
     * @param   data    The array to display.
     * @return  A printable string.
     */
    private static String getString(Object [] data)
    {
        String result = new String("[ ");
        
        for(int i = 0; i< data.length; i++)
        {
            result = result + data[i].toString() + " ";
        }
        
        result = result + "]";
        
        return result;
    }
    
    
    /**
     * Get an integer value
     *
     * @return     An integer. 
     */
    private static int getInt(String rangePrompt)
    {
        Scanner input;
        int result = 10;        //default value is 10
        try
        {
            input = new Scanner(System.in);
            System.out.println(rangePrompt);
            result = input.nextInt();
            
        }
        catch(NumberFormatException e)
        {
            System.out.println("Could not convert input to an integer");
			System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }        
        catch(Exception e)
        {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }
        return result;
                                    
    }
}
