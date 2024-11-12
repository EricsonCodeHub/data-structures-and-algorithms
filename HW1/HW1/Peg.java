
/**
	The peg class represents a peg in a ring stacking game.
	It manages an Arraylist of rings
*/

import java.util.ArrayList;
public class Peg
{
	//ring list holds the the values for the rings
	private ArrayList<Integer> ringList = new ArrayList<Integer>();
	
	/**
		Constructs a Peg object with the specific number of rings.
		
		@param numRings The number of rings to initialize the peg.
	*/
	public Peg(int numRings)
	{
		// rings will be added to the list as long as numRings > 0
		for(;numRings>0;numRings--)
		{
			ringList.add(numRings);
		}
	}
	
	
	/**
		Returns the number of rings on the peg.
		
		@return The number of rings on the peg.
	*/
	public int getNumRings()
	{
		// returns the size of the list 
		return ringList.size();
	}
	
	
	/**
		Returns the diameter of the top ring on the peg
		
		@return The diameter of the top ring on the peg
	*/
	public int getTopRingDiameter()
	{
		// returns the size of the top ring
		return ringList.get(ringList.size() -1);
	}
	
	/**
		Adds a new ring to the top of the peg
		
		@param newRing The diameter of the new ring to be added
	*/
	public void addRing( int newRing )
	{
		// adds a ring to ringList
		ringList.add(newRing);
	}
	
	/**
		Removes the top ring from the peg
		
		@return The diameter of the removed top ring
	*/
	public int removeRing()
	{
		// holds ring after it gets removed
		int topRing; 
		
		// sets topRing
		topRing = getTopRingDiameter(); 
		
		//removes topRing from ringList
		ringList.remove(ringList.size() -1);
		
		//returns topRing
		return topRing;
	}
	
	/**
		Returns the ring diameter at the specified index in the ring list.
		
		@param index The index of the ring in the ring list
		@return The diameter of the ring at the specified index
	*/
	public int getListIndex(int index)
	{
		//returns int so that there is no access to change values
		return ringList.get(index);
	}
}