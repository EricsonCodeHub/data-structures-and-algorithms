import java.io.*;
import java.util.*;

/**
 * Primes is a program that will compute prime numbers using the 
 * sieve of Eratosthenes.
 * 
 * @author Charles Hoot
  * @version 4.0
 */
public class Primes
{
    public static void main(String args[])
    {
        int max;
        ListInterface<Integer> candidates;
        ListInterface<Integer> primes;
        ListInterface<Integer> composites;
        int primeNumber;
        
        System.out.println("Please enter the maximum value to "
												+"test for primality");
        max = getInt("   It should be an integer value greater "
												+"than or equal to 2.");

        // COMPLETE THE MAIN
        //step 2
        //assigns candidates with AList
        candidates = new AList<>(max);
        //loop that adds the candidates to candidates list
        for(int i = 2;i<=max; i++)
        {
            candidates.add(i);
        }
        //step 3
        //prints out candidates list
        System.out.println("Candidates list " + candidates.toString());

        //step 4
        //assigns AList to primes
        primes = new AList<>(max);
        //assigns AList to composites
        composites = new AList<>(max);
        //step 11 
        //loops while candidates in not empty
        while(!candidates.isEmpty())
        {
            //step 5
            /*
				removes the first index in candidates and 
				assigns it to primeNumber
			*/ 
            primeNumber = candidates.remove(1);
            //step 6
            //prints the prime number to user
            System.out.println(" prime that was discovered is "+primeNumber);
            //step 7
            //adds prime to prime number list
            primes.add(primeNumber);
            //step 10
            //calls  getComposite method
            getComposites(candidates,composites,primeNumber);
            //step 8
            //print out current state of all lists
            System.out.println("candidates list  "+ candidates.toString());
            System.out.println("primes list  "+ primes.toString());
            System.out.println("composites list  "+ composites.toString());
        } 
    }
    
    
    /**
     * getComposites - Remove the composite values from possibles list and
     * put them in the composites list.
     *
     * @param  candidates   A list of integers holding the possible values.
     * @param  composites   A list of integers holding the composite values.
     * @param  prime   An Integer that is prime.
     */
    public static void getComposites(ListInterface<Integer> candidates, 
									 ListInterface<Integer> composites, 
									 Integer prime)
    {
        // COMPLETE THIS METHOD

        //step 9
        //loops through candidates list
        for(int i = 1;i<=candidates.getLength();i++)
        {
            //gets the entry at index i in candidates list
            int current = candidates.getEntry(i);

            //check to see if the current entry is not prime
            if(current % prime == 0)
            {
                //adds current to composite list
                composites.add(current);
                //removes current from candidates list
                candidates.remove(i);

                i--; //index was removes list is now smaller
            }
        }
    }
    
    
    /**
     * Get an integer value.
     *
     * @return     An integer. 
     */
    private static int getInt(String rangePrompt)
    {
        Scanner input;
        int result = 10;        //Default value is 10
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
