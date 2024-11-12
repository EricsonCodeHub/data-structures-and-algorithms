
/**
	The Towers class represents a set of three pegs,
	It manages the movement of rings between pegs
*/

import java.util.ArrayList;
public class Towers
{
	//pegList stores ped objects
	private ArrayList<Peg> pegList = new ArrayList<Peg>(); 
	
	
	/**
		constructs a Towers object with three pegs,
		initializing the first peg with the specified number of rings,
		the other two towers start with 0 rings.
		
		Precondition: 1 <= numRings <= 64.
		
		Postcondition: The towers have been initialized with numRings on the
			first peg and no rings on the other two pegs. The diameters
			of the first peg's rings are from one inch (on the top) to 
			numRings inches (on the bottom).

		
		@param numRings The number of rings to initialize the first peg.
	*/
	public Towers(int numRings)
	{
		//creates 3 peg objects
		Peg peg1 = new Peg(numRings);
		Peg peg2 = new Peg(0);
		Peg peg3 = new Peg(0);
		
		// stores peg objects in pedList
		pegList.add(peg1);
		pegList.add(peg2);
		pegList.add(peg3);
	}
	
	/**
		Returns the number of rings on the specified peg.
		
		Precondition: pegNumber is 1, 2, or 3
		
		Postcondition: The return value is the number of rings on the
			specified peg.
		
		@param pegNumber The number of the peg 1 2 or 3..
		@return The number of rings on the specified peg.
	*/
	public int countRings(int pegNumber)
	{	
		/*
			line first gets the peg object and then from there
			getNumRings is used to return the amount of rings
			on the peg.
		*/
		
		return pegList.get(pegNumber-1).getNumRings();
		
		
	}
	

	/**
		Returns the diameter of the top ring on the specified peg.
		
		Precondition: pegNumber is 1, 2, or 3
		
		Postcondition: If countRings(pegNumber) > 0, then the return value is 
			the diameter of the top ring on the specified peg; otherwise,
			the return value is zero.
		
		@param pegNumber The number of the peg 1 2 or 3.
		@return The diameter of the top ring on the specified peg.
	*/
	public int getTopDiameter(int pegNumber)
	{
		
		/*
			line first gets the peg object and then from there
			getTopRingDiameterd is used to get top ring diameter 
		*/
		
		return pegList.get(pegNumber -1).getTopRingDiameter();
		
	}
	
	/**
		Moves the top ring from the start peg to the end peg.
		
		Precondition: stargPeg is a pegnumber (1, 2, or 3), and 
			countRings(startPeg) > 0; endPeg is a different peg number 
			(not equal to startPeg), and if endPeg has at least 
			one ring, then getTopDiameter(startPeg) is less than 
			getTopDiameter(endPeg).
			
		Postcondition: The top ring has been moved from startPeg to endPeg.

		
		@param startPeg The number of the peg 1, 2, or 3.
		@param endPeg   The number of the peg 1, 2, or 3.
	*/
	public void move(int startPeg, int endPeg)
	{
		/*
			we first add the top ring from stargPeg to the endPeg
			after we remove the top ring from startPeg.
		*/
		pegList.get(endPeg-1).addRing( pegList.get(startPeg-1).removeRing());
		
	}

	
	/**
		Displays the current state of the towers to the user.
		
		Precondition: the game has started
		
		Postcondition: a display indicating each peg and the stack of rings on 
			each peg has been displayed with the smallest ring shown on top
	*/
	public void displayTowers()
	{
		
		//variables that will be used for this method 
		int num1,num2,num3,largest;
		
		/*
			num1, num2, num3 will be assigned the ring amount 
			according to the peg number.
		*/
		num1 = pegList.get(0).getNumRings();
		num2 = pegList.get(1).getNumRings();
		num3 = pegList.get(2).getNumRings();
		
		
		// the code bellow gets sets largest to the highest value num
		largest = num1;
		if(num2>largest)
		{
			largest = num2;
		}
		if(num3>largest)
		{
			largest = num3;
		}
		
		//label for peg print out 
		System.out.println("peg1 peg2 peg3 ");
		
		// the loop bellow print the value of each ring per peg
		for (int i = largest -1; i >=0; i--)
		{
			
			/*
				the if statements check to see if (i) is still within
				the peg arrayList index
				
				if true a ring is printed
				if false a blank space is printed
			*/
			
			if (i < num1) 
			{
				System.out.print(" "+pegList.get(0).getListIndex(i) + "   ");
			} 
			else 
			{
				System.out.print("     ");
			}

			if (i < num2) 
			{
				System.out.print(" "+pegList.get(1).getListIndex(i) + "   ");
			} 
			else 
			{
				System.out.print("     ");
			}

			if (i < num3) 
			{
				System.out.print(" "+pegList.get(2).getListIndex(i) + "   ");  
			} 
			else 
			{
				System.out.print("     ");
			}

			System.out.println();	
		}
	}
	
}