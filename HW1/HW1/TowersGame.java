import java.util.Scanner;

/**
	TowersGame uses the tower class to play the ring stake game
*/
public class TowersGame
{

    public static void main(String[] args)
	{
		
		// variables used to manage game play
        Scanner scanner = new Scanner(System.in); // scanner 
		int startPeg; //start peg number-index
		int endPeg;   //end peg number-index
		int numRings; // number of rings
		String continueChoice; //string that represents players choice 
		Towers game; //Tower object
		
		/*
			this loop asks the player how many rings they would want to play
			with, the loop will break once there is a valid entry  
		*/
		for(;;)
		{
			System.out.print("Enter the number of rings  you "+
								"want to play with 1-64: ");
			numRings = scanner.nextInt();
			if(numRings<1 || numRings>64)
			{
				System.out.println("you entered an invalide number");
			}
			else
			{
				break;
			}
		}
        
		System.out.println("You must move all the rings from start position "+
						"to another ring to win the game");
		
		//Towers game is initialized  
        game = new Towers(numRings);

		//this loop runs the game
        for(;;)
		{
			// player is asked if they want to continue the game 
            game.displayTowers();
            System.out.println("Do you want to continue? (y/n): ");
            continueChoice = scanner.next();
			
			// if player enters n the game ends
            if (!continueChoice.equals("y"))
			{
                System.out.println("Game Over!");
                break;
            }
			
			
			/*
				this look asks the player to enter the startPeg and endPeg,
				the loop breaks when valid pegs have been entered,
				validMove() check if the pegs are valid or not.
			*/
			for(;;)
			{
				System.out.print("Enter start peg (1, 2, or 3): ");
				startPeg = scanner.nextInt();
				
				System.out.print("Enter end peg (1, 2, or 3): ");
				endPeg = scanner.nextInt();
				
				//if pegs are valid loop breaks
				if(validMove(game, startPeg, endPeg))
				{
					break;
				}
				
				
			}
			
			//moves ring
            game.move(startPeg, endPeg);  
			
			// message if player wins game
			if( game.countRings(2) == numRings ||
					game.countRings(3) == numRings)
			{
				game.displayTowers();
				System.out.println("you win");
				break;
			}
			
        } 
        scanner.close();
    }

	/**
		Validates the move based on game rules.
		
		Precondition: game is a reference to the Towers object; startPeg 
			contains the peg number that the ring is being moved from; 
			endPeg contains the peg number that the ring is being moved to.
						
		Postcondition: It has been determined whether or not the appropriate
			game rules have been violated. If any of them have, an appropriate 
			error message is displayed and false has been returned. If no rules 
			have been violated, true has been returned

		
		@param game The Towers game object.
		@param startPeg The number of the peg 1, 2, or 3.
		@param endPeg The number of the peg 1, 2, or 3.
		@return True if the move is valid.
	*/
	public static boolean validMove(Towers game, int startPeg, int endPeg)
	{
		/*
			sets return boolean to true, if any if statements fail 
			boolean is set to false
		*/
		boolean returnVar = true;//variable to be returned 
		boolean check = true; //used to prevent program crash
		
		
		//checks to see if startPeg and end peg are 1,2,or 3 
		if (startPeg<1 || startPeg>3 || endPeg<1 || endPeg>3)
		{
			System.out.println("please enter a valid peg numbers");
			check = false;
			returnVar = false;
		}
		
		/*
			check if there is a ring on start peg
			this check is needed so the program doesn't crash
			due to an out of bounds index error.
		*/
		if(check) 
		{
			if(game.countRings(startPeg) == 0)
			{
				System.out.println("please enter a valid start peg");
				returnVar = false;
			}
			
			// check  that the start peg ring is smaller than the end pag.
			if( game.countRings(endPeg) != 0 &&
					game.getTopDiameter(startPeg)>game.getTopDiameter(endPeg))
			{
				System.out.println("you can't place a big " + 
									"ring on a small ring");
				returnVar = false;
			}	
		}

		
		//checks to make sure the startPeg and endPegare not the same
		if( startPeg == endPeg)
		{
			System.out.println("make sure startPeg and endPeg are different");
			returnVar = false;
		}
		return returnVar;
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	


	