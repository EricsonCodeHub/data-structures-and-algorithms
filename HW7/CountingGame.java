import java.io.*;
import java.util.*;

/**
 * CountingGame is a program that will simulate a children's counting game used 
 * to select someone.
 * 
 * @author Charles Hoot
 * @version 4.0
 */
    
public class CountingGame
{
    public static void main(String args[])
    {
        ListInterface<Integer> players = null;
        ListInterface<String> rhyme = null;
        
        int max;
        int position = 1;       // always start with the first player
        
        System.out.println("Please enter the number of players.");
        max = getInt("   It should be an integer value greater than "+
													"or equal to 2.");
        System.out.println("Constructing list of players");
        
        // ADD CODE HERE TO CREATE THE LIST OF PLAYERS

        //step2
        //assigns AList to players
        players = new AList<Integer>(max);

        // step3
        //adds the players(ints) to the palyer list
        for (int i = 1; i <= max; i++)
        {
            players.add(i);
        }

        //prints out the players list
        System.out.println("The players list is " + players.toString());
        
        //calls the getRhyme and assigns the return type to rhyme
        rhyme = getRhyme();

        // ADD CODE HERE TO PLAY THE GAME
        // step 7
        // this is the loop that runs the game
        while(players.getLength() > 1)
        {
            // step 5
            //calls the doRhyme method and assigns position the return type
            position = doRhyme(players, rhyme, position);
            
            // step 6
            //prints the player still remaining after the round
            System.out.println("The players list after the round is " + 	
													players.toString());
        }

        //prints the winner of the game
        System.out.println("The winner is " + players.getEntry(1));
    }
    
    
    /**
     * Do the rhyme with the players in the list and remove the selected
     * player.
     *
     * @param  players   A list holding the players.
     * @param  rhyme   A list holding the words of the rhyme.
     * @param  startAt A position to start the rhyme at.
     * 
     * @return The position of the player eliminated.
     */
    public static int doRhyme(ListInterface<Integer> players, 
							  ListInterface<String> rhyme, 
							  int startAt)
    {
        // COMPLETE THIS METHOD
        // step 4
        // current word index
        int currentWord;

        /*
            lops through the rhyme list while also looping through the 
			player list
        */
        for(currentWord = 1;currentWord<rhyme.getLength(); currentWord++)
        {
            //prints to user the current state of loop/game
            System.out.println("Player "+players.getEntry(startAt) + 
                        " says " +rhyme.getEntry(currentWord));
            //increments player index
            startAt++;
            //if player idex goes out of bounds start at gets set to 1
            if(startAt>players.getLength())
            {
                startAt =1;
            }
        }

        //prints to user the current state of game
        System.out.println("Player "+players.getEntry(startAt) + 
                        " says " +rhyme.getEntry(currentWord));
        
        //print to user player that is to be removed
        System.out.println("Player " + players.getEntry(startAt) +
												" is getting removed");
        //removes plaver from game
        players.remove(startAt);

        //if player idex goes out of bounds start at gets set to 1
        if(startAt>players.getLength())
        {
            startAt =1;
        }
        //returns currnent player possition
        return startAt;

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
    

    /**
     * getRhyme - Get the rhyme.
     *
     * @return    A list of words that is the rhyme.
     */
    private static ListInterface<String> getRhyme()
    {
        Scanner input = new Scanner( System.in );
        //Scanner rhymeWords = new Scanner(inString); error
        String inString = "";
        ListInterface<String> rhyme = new AList<String>();

        try
        {
            System.out.println("\nPlease enter a rhyme.");
            inString = input.nextLine().trim();
            // splits string to an array
            String [] rhymeWords = inString.split(" ");
            //adds array elements the rythem list
            for(int i=0; i<rhymeWords.length; i++)
            {
                rhyme.add(rhymeWords[i]);
            }
        }
        catch(Exception e)
        {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use a rhyme of size one");
        }
        // Make sure there is at least one word in the rhyme
        if(rhyme.getLength() < 1)
            rhyme.add("Default");

        return (ListInterface<String>)rhyme;
    }
}
