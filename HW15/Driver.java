/**
   A driver that demonstrates the class BstMaxHeap.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
 */
public class Driver 
{
	public static void main(String[] args) 
	{
		String jared    = "Jared";
		String brittany = "Brittany";
		String brett    = "Brett";
		String doug     = "Doug";
		String megan    = "Megan";
		String jim      = "Jim";
		String whitney  = "Whitney";
		String matt     = "Matt";
		String regis    = "Regis";
		
		MaxHeapInterface<String> aHeap = new BstMaxHeap<>();
		aHeap.add(jared);		
		aHeap.add(brittany);
		aHeap.add(brett);
		aHeap.add(doug);		
		aHeap.add(megan);
		aHeap.add(jim);
		aHeap.add(whitney);		
		aHeap.add(matt);
		aHeap.add(regis);
		
		if (aHeap.isEmpty())
			System.out.println("The heap is empty - INCORRECT");
		else
			System.out.println("The heap is not empty; it contains " +
			                   aHeap.getSize() + " entries.");
		
		System.out.println("The largest entry is " + aHeap.getMax());
		
		System.out.println("\n\nRemoving entries in descending order:");
		while (!aHeap.isEmpty())
			System.out.println("Removing " + aHeap.removeMax());

			
		System.out.println("\n\nTesting constructor with array parameter:\n");
		String[] nameArray = {jared, brittany, brett, doug, megan, 
		                      jim, whitney, matt, regis};
		MaxHeapInterface<String> anotherHeap = new BstMaxHeap<>(nameArray);
		
		if (anotherHeap.isEmpty())
			System.out.println("The heap is empty - INCORRECT");
		else
			System.out.println("The heap is not empty; it contains " +
			                   anotherHeap.getSize() + " entries.");
		
		System.out.println("The largest entry is " + anotherHeap.getMax());
		
		System.out.println("\n\nRemoving entries in descending order:");
		while (!anotherHeap.isEmpty())
			System.out.println("Removing " + anotherHeap.removeMax());
	}  // end main
}  // end Driver

/*
 The heap is not empty; it contains 9 entries.
 The largest entry is Whitney
 
 
 Removing entries in descending order:
 Removing Whitney
 Removing Regis
 Removing Megan
 Removing Matt
 Removing Jim
 Removing Jared
 Removing Doug
 Removing Brittany
 Removing Brett
 
 
 Testing constructor with array parameter:
 
 The heap is not empty; it contains 9 entries.
 The largest entry is Whitney
 
 
 Removing entries in descending order:
 Removing Whitney
 Removing Regis
 Removing Megan
 Removing Matt
 Removing Jim
 Removing Jared
 Removing Doug
 Removing Brittany
*/
